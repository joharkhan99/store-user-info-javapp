package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class SinglePersonActivity extends AppCompatActivity {
  ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_single_person);

    Intent intent = getIntent();
    String email = intent.getStringExtra("email");

    Cursor c = MainActivity.hospDB.rawQuery("SELECT * FROM records WHERE email='" + email + "'", null);

    ArrayList<String> infoValues = new ArrayList<>();

    int nameIndex = c.getColumnIndex("name");
    int jobIndex = c.getColumnIndex("job");
    int emailIndex = c.getColumnIndex("email");
    int addressIndex = c.getColumnIndex("address");
    int phoneIndex = c.getColumnIndex("phone");

    if (c.moveToFirst()) {
      do {
        infoValues.add("Name:\t\t"+c.getString(nameIndex));
        infoValues.add("Job:\t\t"+c.getString(jobIndex));
        infoValues.add("Email:\t\t"+c.getString(emailIndex));
        infoValues.add("Address:\t\t"+c.getString(addressIndex));
        infoValues.add("Phone:\t\t"+c.getString(phoneIndex));
      } while (c.moveToNext());
    }

    listView = (ListView) findViewById(R.id.personDetailList);
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infoValues);
    listView.setAdapter(arrayAdapter);

  }
}
