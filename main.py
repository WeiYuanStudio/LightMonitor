import ClientAPI
import json
import time

name = 'WeiYuan'

registerResponseJSON  = ClientAPI.register(name)

session = registerResponseJSON['usersession']

print(session)

while True:
    ClientAPI.sendHeartbeart(session)
    time.sleep(30)
    print('Hello')