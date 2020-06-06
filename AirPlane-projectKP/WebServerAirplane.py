import subprocess
from flask import Flask, render_template

app = Flask(__name__)


@app.route("/AirPlane-form")
def airplane():
    return render_template('AirPlane-form.html')


@app.route("/")
def import_java_file():
    return subprocess.check_output(
        ["java", "-classpath", "/Users/dvir tayeb/eclipse-workspace/Airplane/bin", "airplane.Program"])


# reset the app:
if __name__ == '__main__':
    app.run(debug=True)
