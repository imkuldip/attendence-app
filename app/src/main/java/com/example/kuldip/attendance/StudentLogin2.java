package com.example.kuldip.attendance;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class StudentLogin2 extends AppCompatActivity {

    //ImageView studentback;
    Spinner studentsubject, studentmonth;
    ArrayAdapter subjectAdapter,monthAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login2);

       // getSupportActionBar().setHomeButtonEnabled(true);//actionBar.setHomeButtonEnabled(true) will just make the icon clickable, with the color at the background of the icon as a feedback of the click.

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// actionBar.setDisplayHomeAsUpEnabled(true) will make the icon clickable and add the < at the left of the icon.

        studentsubject = findViewById(R.id.studentsubject);
        studentmonth = findViewById(R.id.studentmonth);
       // studentback = findViewById(R.id.studentback);

        subjectAdapter =  ArrayAdapter.createFromResource(this,R.array.BIM_1st,android.R.layout.simple_spinner_item);//layout for each item in spinner
        monthAdapter =  ArrayAdapter.createFromResource(this,R.array.Month,android.R.layout.simple_spinner_item);//layout for each item in spinner

        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//layout for each dropdown list for the spinner
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//layout for each dropdown list for the spinner

        studentsubject.setAdapter(subjectAdapter);
        studentmonth.setAdapter(monthAdapter);

//        studentback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(StudentLogin2.this, StudentLogin1.class));
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;

            case R.id.shareapp:
                break;

            case R.id.rateus:
                break;

            case R.id.disclaimer:
                showDisclaimer();
                break;

            //iditem.getItemId() == android.R.id.home.
        }
        return super.onOptionsItemSelected(item);
    }
    public void showDisclaimer(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Disclaimer");
        View view = LayoutInflater.from(this).inflate(R.layout.menu_disclaimer,null);
        view.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }

        });
        dialog.setContentView(view);
        dialog.show();
        dialog.setCancelable(false);
    }
}
