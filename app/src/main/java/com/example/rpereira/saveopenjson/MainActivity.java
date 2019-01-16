package com.example.rpereira.saveopenjson;

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

    private Button mbtnSave;

    private Button mbtnOpen;

    private String json = null;

    private Gson gson = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medtName = findViewById(R.id.idedtName);

        medtLastname = findViewById(R.id.idedtLastname);

        medtAge = findViewById(R.id.idedtAge);

        mbtnSave = findViewById(R.id.idbtnSave);

        mbtnOpen = findViewById(R.id.idbtnOpen);

        mbtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!medtName.getText().toString().isEmpty() && !medtLastname.getText().toString().isEmpty() &&
                        !medtAge.getText().toString().isEmpty()) {

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
                    Log.i("JSON", "In Json: "+json);

                } else {
                    Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
