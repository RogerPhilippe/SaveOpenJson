package com.example.rpereira.saveopenjson;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText medtName;

    private EditText medtLastname;

    private EditText medtAge;

    private String json = null;

    private Gson gson = null;

    public static final String PREFS_NAME = "PersonJsonSave";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medtName = findViewById(R.id.idedtName);

        medtLastname = findViewById(R.id.idedtLastname);

        medtAge = findViewById(R.id.idedtAge);

        Button mbtnSave = findViewById(R.id.idbtnSave);

        Button mbtnOpen = findViewById(R.id.idbtnOpen);

        mbtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!medtName.getText().toString().isEmpty() && !medtLastname.getText().toString().isEmpty() &&
                        !medtAge.getText().toString().isEmpty()) {

                    @SuppressLint("SimpleDateFormat")
                    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date date = new Date();
                    Long dateTime = Long.parseLong(dateFormat.format(date));
                    String name = medtName.getText().toString();
                    String lastName = medtLastname.getText().toString();
                    int age = Integer.parseInt(medtAge.getText().toString());

                    Person person = new Person.Builder()
                            .setId(dateTime)
                            .setName(name)
                            .setLastname(lastName)
                            .setAge(age)
                            .build();

                    gson = new Gson();
                    json = gson.toJson(person);

                    if (!json.isEmpty()) {

                        Log.i("JSON", "In Json: "+json);

                        Toast.makeText(MainActivity.this, "Json salvo.", Toast.LENGTH_SHORT).show();

                        // Save Prefs
                        // We need an Editor object to make preference changes.
                        // All objects are from android.context.Context
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("person", json);

                        // Commit the edits.
                        editor.apply();

                    } else {
                        Toast.makeText(MainActivity.this, "Json está vazio!", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mbtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Restore preferences
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                String jsonRecovered = settings.getString("person", "");

                gson = new Gson();

                Person person = gson.fromJson(jsonRecovered, Person.class);

                if(person != null) {

                    medtName.setText(person.getmName());
                    medtLastname.setText(person.getmLastname());
                    medtAge.setText(String.valueOf(person.getmAge()));

                }

            }
        });

    }
}
