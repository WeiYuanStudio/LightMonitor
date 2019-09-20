from urllib import request, parse
import json
import time
import configparser


class Client:
    def __init__(self):
        self.configPath = 'client.cfg'

        self.registerSubdomain = '/register'

        self.heartbeatSubdomain = '/heartbeart'  # FIXME:Fix spelling mistake

        self.clientname = ''

        self.serverLocation = ''

        self.usersession = ''

    def readConfig(self):
        # Read config file
        print('Reading config file...')
        config = configparser.ConfigParser()
        config.read(self.configPath)

        # Decode clientname
        self.clientname = config['DEFAULT']['clientname']
        print('Set clientname: ' + self.clientname)

        # Decode serverLocation
        self.serverLocation = config['DEFAULT']['serverLocation']
        print('Set serverLocaiton: ' + self.serverLocation)

        try:
            # Try to decode usersession
            self.usersession = config['DEFAULT']['usersession']
        except KeyError:
            pass

        if self.usersession != '':
            print('Set usersession: ' + self.usersession)
        else:
            print('Config file did not have usersession, try to get one later')

        print('Config file read success, no except occured')

    def register(self):
        if(self.usersession == ''):
            print('usersession null, register now')

            param = {
                'clientname': self.clientname
            }
            urlParam = parse.urlencode(param)
            req = request.Request(url=self.serverLocation +
                                  self.registerSubdomain + '?' + urlParam, method='GET')
            responseStream = request.urlopen(req)

            responseText = responseStream.read().decode('utf-8')
            try:
                responseJSON = json.loads(responseText)
                print('Get usersession: ' + responseJSON['usersession'])
            except json.JSONDecodeError:
                print(
                    "Can't not decode response JSON, Please check you serverLocation settings")
                exit(0)

            # Set usersesion
            self.usersession = responseJSON['usersession']

            # Save usersession to file
            config = configparser.ConfigParser()
            config.read(self.configPath)
            config.set('DEFAULT', 'usersession', self.usersession)
            config.write(open(self.configPath, 'w'))

    def sendHeartbeat(self):  # TODO: Detect usersession legality
        param = {
            'usersession': self.usersession
        }
        urlParam = parse.urlencode(param)
        req = request.Request(url=self.serverLocation +
                              self.heartbeatSubdomain + '?' + urlParam, method='GET')
        responseStream = request.urlopen(req)
        responseText = responseStream.read().decode('utf-8')

        print('Send Heartbeat at time ' + time.strftime("%Y-%m-%d %H:%M:%S",
                                                        time.localtime()) + ' Response ' + responseText)
        return responseText
