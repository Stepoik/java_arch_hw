from websockets.sync.client import connect
from threading import Thread

def message_handler(websocket):
    while True:
        message = websocket.recv()
        print(message)

def message_producer(websocket):
    while True:
        inp = input()
        websocket.send(inp)

def hello():
    with connect("ws://localhost:8080/chat") as websocket:
        handler_thread = Thread(target=message_handler, args=[websocket])
        producer_thread = Thread(target=message_producer, args=[websocket])
        handler_thread.start()
        producer_thread.start()
        handler_thread.join()
        producer_thread.join()

hello()