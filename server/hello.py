
from flask import Flask
app = Flask(__name__)

@app.route('/')
def hello_world():
        return 'Hello, World!'

"""
To run it simply type:
    export FLASK_APP=hello.py
    flask run


    --host=0.0.0.0 --port=8080
"""
