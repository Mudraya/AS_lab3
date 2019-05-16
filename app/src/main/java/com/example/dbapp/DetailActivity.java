package com.example.dbapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.activity_detail);


        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app5.db", MODE_PRIVATE, null);
        Cursor query = db.rawQuery("SELECT * FROM answers;", null);
        TextView textView = (TextView) findViewById(R.id.data);
        if(query.moveToFirst()){
            do{
                String faculty = query.getString(0);
                String year = query.getString(1);
                String time = query.getString(2);
                textView.append("Faculty: " + faculty + " Year: " + year + "Time: " + time + "\n");
            }
            while(query.moveToNext());
        }
        else { textView.append("No data"); }
        query.close();
        db.close();
    }


    public void sendMessage2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}