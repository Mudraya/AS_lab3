package com.example.dbapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

//        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app5.db", MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS answers (faculty TEXT, _year TEXT, _time TEXT)");
    }

    public void showSelection(View v) {
        TextView selection = (TextView) findViewById(R.id.selection);
        RadioGroup radGrpF = (RadioGroup)findViewById(R.id.radioFaculties);
        RadioGroup radGrpY = (RadioGroup)findViewById(R.id.radioYear);
        int selectedIdF = radGrpF.getCheckedRadioButtonId();
        int selectedIdY = radGrpY.getCheckedRadioButtonId();
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app5.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS answers (faculty TEXT, _year TEXT, _time TEXT)");

        if (selectedIdF != -1 && selectedIdY != -1 ) {
            RadioButton radioBtnF = (RadioButton) findViewById(selectedIdF);
            RadioButton radioBtnY = (RadioButton) findViewById(selectedIdY);
            String curDate = new Date().toString();
            selection.setText("You are " + radioBtnY.getText().toString() + "-year student, who study in " + radioBtnF.getText().toString());
            db.execSQL("INSERT INTO answers VALUES ('"+ radioBtnF.getText().toString() +"', '" + radioBtnY.getText().toString()+ "', '" + curDate + "');");
            Toast.makeText(this, "Your answer added successfully", Toast.LENGTH_SHORT).show();
        }
        else { selection.setText("Select something from both lists"); }

    }

    public void cancel(View v) {
        RadioGroup radGrpF = (RadioGroup)findViewById(R.id.radioFaculties);
        RadioGroup radGrpY = (RadioGroup)findViewById(R.id.radioYear);
        radGrpF.clearCheck();
        radGrpY.clearCheck();
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
        finish();
    }
}