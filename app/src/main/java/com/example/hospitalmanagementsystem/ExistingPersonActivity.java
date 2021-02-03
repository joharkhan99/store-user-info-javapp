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

public class ExistingPersonActivity extends AppCompatActivity {

  ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_existing_person);

    Cursor c = MainActivity.hospDB.rawQuery("SELECT * FROM records", null);

    int idIndex = c.getColumnIndex("id");
    int nameIndex = c.getColumnIndex("name");
    int jobIndex = c.getColumnIndex("job");
    int emailIndex = c.getColumnIndex("email");
    int addressIndex = c.getColumnIndex("address");
    int phoneIndex = c.getColumnIndex("phone");

    final ArrayList<String> emails = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();

    if (c.moveToFirst()) {
      do {
        emails.add(c.getString(emailIndex));
        names.add(c.getString(nameIndex));
      } while (c.moveToNext());
    }

    listView = (ListView) findViewById(R.id.listView);
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
    listView.setAdapter(arrayAdapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), SinglePersonActivity.class);
        intent.putExtra("email", emails.get(position));
        startActivity(intent);
      }
    });

  }
}