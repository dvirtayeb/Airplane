import subprocess
from datetime import datetime

from flask import Flask, render_template, request

app = Flask(__name__)


def import_java_file(departure_arrival_string, start_date_string, end_date_string,
                     company_string, state_string, city_string, airport_string, sunday_string, monday_string,
                     tuesday_string, wednesday_string, thursday_string, friday_string, saturday_string):
    return subprocess.check_output(
        ["java", "-classpath", "/Users/dvir tayeb/eclipse-workspace/Airplane/bin", "program.Program",
         "html", departure_arrival_string, start_date_string, end_date_string,
         company_string, state_string, city_string, airport_string,sunday_string, monday_string,
         tuesday_string, wednesday_string, thursday_string, friday_string, saturday_string],
        universal_newlines=True)


@app.route("/", methods=['POST', 'GET'])
def search():
    if request.method == 'POST':
        departure_arrival = request.form.get("Departure-Arrival")
        company = request.form.get('Company')
        if company == '':
            company = "null"
        state = request.form.get('state')
        if state == '':
            state = "null"
        city = request.form.get('city')
        if city == '':
            city = "null"
        airport = request.form.get('airport')
        if airport == '':
            airport = "null"
        start_date = request.form.get('start_date')
        if start_date == '':
            start_date = '1-1-1'
        end_date = request.form.get('end_date')
        if end_date == '':
            end_date = '1-1-1'
        sunday = request.form.get('Sunday')
        if sunday == '':
            sunday = 'false'
        monday = request.form.get('Monday')
        if monday == '':
            monday = 'false'
        tuesday = request.form.get('Tuesday')
        if tuesday == '':
            tuesday = 'false'
        wednesday = request.form.get('Wednesday')
        if wednesday == '':
            wednesday = 'false'
        thursday = request.form.get('Thursday')
        if thursday == '':
            thursday = 'false'
        friday = request.form.get('Friday')
        if friday == '':
            friday = 'false'
        saturday = request.form.get('Saturday')
        if saturday == '':
            saturday = 'false'
        return render_template('Flight.html',
                               departure_arrival=departure_arrival,
                               flight=import_java_file(departure_arrival, start_date, end_date,
                                                       company, state, city, airport, sunday, monday, tuesday,
                                                       wednesday, thursday, friday, saturday)
                               )

    return render_template('SearchFlight-form.html')


@app.route("/AirPlane-form", methods=['POST', 'GET'])
def airplane():
    days =["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday,", "Friday", "Saturday"]
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
