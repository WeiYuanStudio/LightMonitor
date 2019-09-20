from urllib import request, parse
import json
import time
import configparser

serverLocation = 'http://94.191.113.229:90'

registerSubdomain = '/register'

heartbeatSubdomain = '/heartbeart'

def register(clientname):
    param = {
        'clientname': clientname
    }
    urlParam = parse.urlencode(param)
    req = request.Request(url=serverLocation +
                          registerSubdomain + '?' + urlParam, method='GET')
    responseStream = request.urlopen(req)
    responseText = responseStream.read().decode('utf-8')
    try:
        responseJSON = json.loads(responseText)
    except json.JSONDecodeError:
        print("Can't not decode response JSON, Please check you serverLocation settings")
        exit(0)
    return responseJSON


def sendHeartbeat(usersession):
    param = {
        'usersession': usersession
    }
    urlParam = parse.urlencode(param)
    req = request.Request(url=serverLocation +
                          heartbeatSubdomain + '?' + urlParam, method='GET')
    responseStream = request.urlopen(req)
    responseText = responseStream.read().decode('utf-8')

    print('Send Heartbeat at time ' + time.strftime("%Y-%m-%d %H:%M:%S",
                                                    time.localtime()) + ' Response ' + responseText)
    return responseText
