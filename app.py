import os
from flask import Flask, render_template, request, redirect, session
import pymysql
import pywhatkit as kit
import random

app = Flask(__name__)
app.secret_key = 'your_secret_key'

try:
    print("Connecting to MySQL...")
    connection = pymysql.connect(
    host=os.environ.get("MYSQL_HOST", "localhost"),
    port=int(os.environ.get("MYSQL_PORT", 3306)),
    user=os.environ.get("MYSQL_USER", "root"),
    password=os.environ.get("MYSQL_PASSWORD", "root"),
    connect_timeout=5
)
    cursor = connection.cursor()
    print("Creating database if not exists...")
    cursor.execute("CREATE DATABASE IF NOT EXISTS login_details")
    connection.commit()
    print(" Database setup done.")
except Exception as e:
    print("Error connecting to MySQL:", e)







@app.route('/')
def home():
    return render_template('login.html')









import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

def send_email_otp(recipient_email, otp):
    sender_email = "harishjeeva71@gmail.com"
    sender_password = "cxbn omnm actf jwzl"

    subject = "Your OTP Verification Code"
    body = f"Your OTP is: {otp}"

    # Compose message
    msg = MIMEMultipart()
    msg["From"] = sender_email
    msg["To"] = recipient_email
    msg["Subject"] = subject

    msg.attach(MIMEText(body, "plain"))

    try:
        server = smtplib.SMTP("smtp.gmail.com", 587)
        server.starttls()
        server.login(sender_email, sender_password)
        server.sendmail(sender_email, recipient_email, msg.as_string())
        server.quit()
        return True
    except Exception as e:
        print("Email sending failed:", e)
        return False














@app.route('/signup', methods=['GET', 'POST'])
def signup():
    if request.method == 'POST':
        name = request.form['name']
        email = request.form['email']
        password = request.form['password']

        # Check if the username already exists BEFORE sending OTP
        try:
            db = pymysql.connect(
                host="localhost",
                port=3306,
                user="root",
                password="root",
                database="login_details"
            )
            cur = db.cursor()
            cur.execute("SELECT * FROM emp_details WHERE name = %s", (name,))
            existing_user = cur.fetchone()
            cur.close()
            db.close()

            if existing_user:
                return render_template('signup.html', message="Username already exists. Please choose another.", success=False)

        except Exception as e:
            return f"Database error while checking username: {e}"

        # Username is unique, proceed to generate and send OTP
        email = request.form['email']
        # ...
        otp = random.randint(1000, 9999)
        session['otp'] = otp
        session['name'] = name
        session['email'] = email
        session['password'] = password

        success = send_email_otp(email, otp)
        if success:
            return redirect('/otp')
        else:
            return "Failed to send OTP via email."

    return render_template('signup.html', error="Username already exists.")










@app.route('/otp', methods=['GET', 'POST'])
def otp():
    if request.method == 'POST':
        user_otp = request.form.get('otp', type=int)
        if user_otp == session.get('otp'):
            try:
                db = pymysql.connect(
                    host="localhost",
                    port=3306,
                    user="root",
                    password="root",
                    database="login_details"
                )
                cur = db.cursor()
                cur.execute("""
                    CREATE TABLE IF NOT EXISTS emp_details (
                        name VARCHAR(50) not null unique,
                        email VARCHAR(100) not null,
                        password VARCHAR(255)
                    )
                """)
                print(f"Inserting user: {session['name']}, {session['email']}")
                cur.execute("INSERT INTO emp_details (name, email, password) VALUES (%s, %s, %s)",
                            (session['name'], session['email'], session['password']))
                db.commit()
                print("User inserted successfully.")
                cur.close()
                db.close()
                session.clear()
                return render_template('login.html', message="Sign up successful!! You can now login", success=True)
            except Exception as e:
                print("Database error:", e)
                return f"Database error: {e}"
        else:
            return render_template('otp.html', message="Invalid OTP.", success=False)
    return render_template('otp.html')










@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        name = request.form['name']
        password = request.form['password']

        try:
            db = pymysql.connect(
                host="localhost",
                port=3306,
                user="root",
                password="root",
                database="login_details"
            )
            cur = db.cursor()
            cur.execute("SELECT name,email,password FROM emp_details WHERE name = %s", (name,))
            result = cur.fetchone()
            cur.close()
            db.close()

            if result:
                db_name, db_email, db_password = result
                # db_password = result[0]
                if password == db_password:
                    session['user'] = {'name': db_name, 'email': db_email}
                    return render_template('dashboard.html', user=session['user'])

                else:
                    return render_template('login.html', message="Incorrect password.", success=False)
            else:
                return render_template('login.html', message="Username not found.", success=False)


        except Exception as e:
            return f"Database error: {e}"

    return render_template('login.html')










@app.route('/reset-password', methods=['GET', 'POST'])
def reset_password():
    message = ''
    if request.method == 'POST':
        name = request.form['name']
        email = request.form['email']
        new_password = request.form['new_password']

        try:
            db = pymysql.connect(
                host="localhost",
                port=3306,
                user="root",
                password="root",
                database="login_details"
            )
            cur = db.cursor()
            cur.execute("SELECT * FROM emp_details WHERE name = %s AND email = %s", (name, email))
            result = cur.fetchone()

            if result:
                cur.execute("UPDATE emp_details SET password = %s WHERE name = %s AND email = %s",
                            (new_password, name, email))
                db.commit()
                message = 'Password reset successfully.'
            else:
                message = 'Invalid username or email.'
            cur.close()
            db.close()
        except Exception as e:
            message = f"Database error: {e}"

    return render_template('reset_password.html', message=message)









@app.route('/dashboard')
def dashboard():
    if 'user' in session:
        return render_template('dashboard.html', user=session['user'])
    return redirect('/')

@app.route('/profile')
def profile():
    if 'user' in session:
        return render_template('profile.html', user=session['user'])
    return redirect('/')










@app.route('/logout', methods=['POST'])
def logout():
    session.clear()
    return redirect('/')









import webbrowser
import threading

def open_browser():
    webbrowser.open_new("http://127.0.0.1:5000/")  # This opens your Flask app in default browser

if __name__ == '__main__':
    threading.Timer(1.0, open_browser).start()  # Open browser after 1 second
    print("🚀 Starting Flask server...")
    app.run(debug=True, use_reloader=False)

