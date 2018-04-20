package com.example.kuldip.attendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AdminLogin2 extends AppCompatActivity {

    String program,semester;
    Spinner subjectSpinner, sectionSpinner;
    ArrayAdapter subjectAdapter,sectionAdapter;
    TextView datetxt;
    Button datebtn,nextbtn;

    Calendar currentDate;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login2);
        setTitle("KCMIT Attendence");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        semester = getIntent().getStringExtra("semester");
        program = getIntent().getStringExtra("program");
        Toast.makeText(this,semester, Toast.LENGTH_SHORT).show();

        subjectSpinner = (Spinner) findViewById(R.id.adminsubject);
        sectionSpinner = (Spinner) findViewById(R.id.adminsection);
        datetxt = (TextView) findViewById(R.id.datetxt);
        datebtn =(Button) findViewById(R.id.datebtn);
        nextbtn =(Button) findViewById(R.id.adminnext2);

        nextbtn.setEnabled(false);

        // adminback = findViewById(R.id.adminback);



        sectionAdapter =  ArrayAdapter.createFromResource(this,R.array.section,android.R.layout.simple_spinner_item);//layout for each item in spinner
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionSpinner.setAdapter(sectionAdapter);

       /* adminback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (AdminLogin2.this,AdminLogin1.class));
            }
        });*/

       setSubject();


        currentDate = Calendar.getInstance();
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        month = currentDate.get(Calendar.MONTH);
        year = currentDate.get(Calendar.YEAR);

        month = month+1;

        datetxt.setText(year+"-"+month+"-"+day);

        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminLogin2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayofmonth) {
                        monthofyear= monthofyear+1;
                        datetxt.setText(year+"-"+monthofyear+"-"+dayofmonth);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


    }

    public void setSubject(){

            if (program.equals("BIM") && semester.equals("1st")){
                subjectAdapter = ArrayAdapter.createFromResource(AdminLogin2.this, R.array.BIM_1st, android.R.layout.simple_spinner_item);
                subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(subjectAdapter);
               nextbtn.setEnabled(true);
            }
            else if(program.equals("BBA")&& semester.equals("1st")){
                subjectSpinner.setEnabled(true);
                subjectAdapter = ArrayAdapter.createFromResource(AdminLogin2.this, R.array.BBA_1st, android.R.layout.simple_spinner_item);
                subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(subjectAdapter);
                nextbtn.setEnabled(true);
            }
            else if(program.equals("BBA")&& semester.equals("2nd")){
                subjectSpinner.setEnabled(true);
                subjectAdapter = ArrayAdapter.createFromResource(AdminLogin2.this, R.array.BBA_2nd, android.R.layout.simple_spinner_item);
                subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(subjectAdapter);
                nextbtn.setEnabled(true);
            }
            else if(program.equals("BBA")&& semester.equals("3rd")){
                subjectSpinner.setEnabled(true);
                subjectAdapter = ArrayAdapter.createFromResource(AdminLogin2.this, R.array.BBA_3rd, android.R.layout.simple_spinner_item);
                subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(subjectAdapter);
                nextbtn.setEnabled(true);
            }
            else if(program.equals("BBA")&& semester.equals("4th")){
                subjectSpinner.setEnabled(true);
                subjectAdapter = ArrayAdapter.createFromResource(AdminLogin2.this, R.array.BBA_4th, android.R.layout.simple_spinner_item);
                subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(subjectAdapter);
                nextbtn.setEnabled(true);
            }
            else if(program.equals("BBA")&& semester.equals("5th")){
                subjectSpinner.setEnabled(true);
                subjectAdapter = ArrayAdapter.createFromResource(AdminLogin2.this, R.array.BBA_5th, android.R.layout.simple_spinner_item);
                subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(subjectAdapter);
                nextbtn.setEnabled(true);
            }
            else if(program.equals("BBA")&& semester.equals("6th")){
                subjectSpinner.setEnabled(true);
                subjectAdapter = ArrayAdapter.createFromResource(AdminLogin2.this, R.array.BBA_6th, android.R.layout.simple_spinner_item);
                subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(subjectAdapter);
                nextbtn.setEnabled(true);
            }
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

