import subprocess
from datetime import datetime

from flask import Flask, render_template, request

app = Flask(__name__)


@app.route("/", methods=['POST', 'GET'])
def search():
    def import_java_file(departure_arrival_string, start_date_string, end_date_string,
                         company_string, state_string, city_string, airport_string, days_string):
        return subprocess.check_output(
            ["java", "-classpath", "/Users/dvir tayeb/eclipse-workspace/Airplane/bin", "airplane.Program",
             "html", departure_arrival_string, start_date_string, end_date_string,
             company_string, state_string, city_string, airport_string, days_string], universal_newlines=True)
    if request.method == 'POST':
        departure_arrival = request.form.get("Departure-Arrival")
        company = request.form.get('Company')
        state = request.form.get('state')
        city = request.form.get('city')
        airport = request.form.get('airport')
        start_date = request.form.get('start_date')
        end_date = request.form.get('end_date')
        day = request.form.get('day')
        return render_template('Flight.html',
                               departure_arrival=departure_arrival,
                               flight=import_java_file(departure_arrival, start_date, end_date,
                                                       company, state, city, airport, day)
                               )

    return render_template('SearchFlight-form.html')


@app.route("/AirPlane-form", methods=['POST', 'GET'])
def airplane():
    if request.method == 'POST':
        company = request.form.get('Company')
        departure_arrival = request.form.get("Departure-Arrival")
        start_date = request.form.get('start_date')
        end_date = request.form.get('end_date')
        time = request.form.get('time')
        num_flight = request.form.get('numFlight')
        terminal = request.form.get('Terminal')
        return "Submitted"
    return render_template('AirPlane-form.html')


@app.route("/departures")
def dep():
    return subprocess.check_output(
        ["java", "-classpath", "/Users/dvir tayeb/eclipse-workspace/Airplane/bin", "airplane.Program",
         request.args.get('outformat'), "departures",
         request.args.get('airline'), request.args.get('country'),
         request.args.get('city'), request.args.get('airport'),
         request.args.get('day1'), request.args.get('month1'),
         request.args.get('year1'), request.args.get('day2'),
         request.args.get('month2'), request.args.get('year2'),
         request.args.get('sunday'), request.args.get('monday'),
         request.args.get('tuesday'), request.args.get('wednesday'),
         request.args.get('thursday'), request.args.get('friday'),
         request.args.get('saturday')])


@app.route("/arrivals")
def arr():
    return subprocess.check_output(
        ["java", "-classpath","/Users/dvir tayeb/eclipse-workspace/Airplane/bin", "airplane.Program",
         request.args.get('outformat'), "arrivals",
         request.args.get('airline'), request.args.get('country'),
         request.args.get('city'), request.args.get('airport'),
         request.args.get('day1'), request.args.get('month1'),
         request.args.get('year1'), request.args.get('day2'),
         request.args.get('month2'), request.args.get('year2'),
         request.args.get('sunday'), request.args.get('monday'),
         request.args.get('tuesday'), request.args.get('wednesday'),
         request.args.get('thursday'), request.args.get('friday'),
         request.args.get('saturday')])


# reset the app:
if __name__ == '__main__':
    app.run(debug=True)
