from urllib import request, parse
import json

serverLocation = 'http://localhost:8080/LightMonitor_war_exploded'

registerSubdomain = '/register'

heartbeartSubdomain = '/heartbeart'


def register(clientname):
    param = {
        'clientname': clientname
    }
    urlParam = parse.urlencode(param)
    req = request.Request(url=serverLocation +
                          registerSubdomain + '?' + urlParam, method='GET')
    responseStream = request.urlopen(req)
    responseText = responseStream.read().decode('utf-8')
    responseJSON = json.loads(responseText)
    return responseJSON


def sendHeartbeart(usersession):
    param = {
        'usersession': usersession
    }
    urlParam = parse.urlencode(param)
    req = request.Request(url=serverLocation +
                          heartbeartSubdomain + '?' + urlParam, method='GET')
    responseStream = request.urlopen(req)
    responseText = responseStream.read().decode('utf-8')
    return responseText
