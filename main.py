import ClientAPI
import json
import time
import urllib

name = 'WeiYuanPython'

try:
    registerResponseJSON = ClientAPI.register(name)
except urllib.error.URLError:
    print('Can not get response from server, Please check you serverLocation settings')
    exit(0)

session = registerResponseJSON['usersession']

print(session)

while True:
    try:
        ClientAPI.sendHeartbeat(session)
    except urllib.error.URLError:
        print('Network Error, Will continue resend Heartbeat')
    time.sleep(10)
