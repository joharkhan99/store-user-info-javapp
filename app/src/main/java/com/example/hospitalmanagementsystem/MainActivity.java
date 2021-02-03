package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  protected static SQLiteDatabase hospDB;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    hospDB = this.openOrCreateDatabase("Hospital_Management", MODE_PRIVATE, null);
    hospDB.execSQL("CREATE TABLE IF NOT EXISTS " +
      "records(id INTEGER PRIMARY KEY, " +
      "name VARCHAR(20), age INTEGER(3), " +
      "job VARCHAR, address TEXT, " +
      "email VARCHAR(50), " +
      "phone VARCHAR(20))");
  }

  public void addNewPerson(View view) {
    Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
    startActivity(intent);
  }

  public void viewExistingPersons(View view) {
    Intent intent = new Intent(MainActivity.this, ExistingPersonActivity.class);
    startActivity(intent);
  }



}