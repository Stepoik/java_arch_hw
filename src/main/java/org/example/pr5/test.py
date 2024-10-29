import requests

def create_user():
    resp = requests.post("http://localhost:8080/user", json={
        "id": "12",
        "firstname": "Stepa",
        "lastname": "Gorokhov"
    })
    print(resp)
    print(resp.text)

def get_user():
    resp = requests.get("http://localhost:8080/user/12")
    print(resp)
    print(resp.text)
get_user()