import ClientAPI
import json
import time
import urllib


def main():
    client = ClientAPI.Client()

    # Read config file
    try:
        client.readConfig()
    except KeyError:
        print('Config file found error, Please check your config file')
        exit(0)

    # Register to server TODO: Merge in sendHeartbeat
    try:
        client.register()
    except urllib.error.URLError:
        print('Can not get response from server, Please check you serverLocation settings')
        exit(0)

    # Unless loop end Heartbeat
    while True:
        try:
            client.sendHeartbeat()
        except urllib.error.URLError:
            print('Network Error, Will continue resend Heartbeat')
        time.sleep(10)


if __name__ == '__main__':
    main()
