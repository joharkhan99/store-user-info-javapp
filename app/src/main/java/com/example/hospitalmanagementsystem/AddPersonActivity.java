package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AddPersonActivity extends AppCompatActivity {
  EditText nameTextView;
  EditText ageTextView;
  EditText jobTextView;
  EditText addressTextView;
  EditText emailTextView;
  EditText phoneTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_person);

    nameTextView = (EditText) findViewById(R.id.name);
    ageTextView = (EditText) findViewById(R.id.age);
    jobTextView = (EditText) findViewById(R.id.job);
    addressTextView = (EditText) findViewById(R.id.address);
    emailTextView = (EditText) findViewById(R.id.email);
    phoneTextView = (EditText) findViewById(R.id.phone);
  }

  public void savePerson(View view) {
    String name = nameTextView.getText().toString();
    String age = ageTextView.getText().toString();
    String job = jobTextView.getText().toString();
    String address = addressTextView.getText().toString();
    String email = emailTextView.getText().toString();
    String phone = phoneTextView.getText().toString();

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]{1,6}+\\.+[a-z]+";

    if (checkEmpty(name) || checkEmpty(age) || checkEmpty(job) || checkEmpty(address) || checkEmpty(email) || checkEmpty(phone))
      showMessage("Please Fill all Fields");
    else if (!email.matches(emailPattern))
      showMessage("Invalid Email Format");
    else if (emailExists(email))
      showMessage("Email already taken");
    else {
      saveToDatabase(name, Integer.parseInt(age), job, address, email, phone);
      showMessage("SAVED!!");

      //move to main activity after 2 sec
      new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
          startActivity(intent);
        }
      }, 2000);

    }
  }

  public void showMessage(String message) {
    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
  }

  public boolean checkEmpty(String field) {
    if (field.isEmpty() || field == "") {
      return true;
    }
    return false;
  }

  public void saveToDatabase(String name, int age, String job, String address, String email, String phone) {
    MainActivity.hospDB.execSQL("INSERT INTO records(name,age,job,address,email,phone) VALUES('" +
      name + "','" +
      age + "','" +
      job + "','" +
      address + "','" +
      email + "','" +
      phone + "')");
  }

  public boolean emailExists(String email) {
    Cursor c = MainActivity.hospDB.rawQuery("SELECT email FROM records WHERE email='" + email + "'", null);

    if (c.getCount() > 0) {
      return true;
    }
    return false;
  }


}
