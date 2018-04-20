package com.example.kuldip.attendance;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TeacherLogin extends AppCompatActivity {
    int selectedProgramIndex = -1;
    //int selectedSemsterIndex = -1;

    Spinner programSpinner, semesterSpinner, sectionSpinner, subjectSpinner;
    ArrayAdapter programAdapter, semesterAdapter, sectionAdapter, subjectAdapter;
    String semester,program,section,subject,batchValue;
    EditText batch;

    Button teachernext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_login1);
        setTitle("Attendance");

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      /*  StudentLogin1.OnDataReceived a = new StudentLogin1.OnDataReceived() {
            @Override
            public void success(String data) {

            }
            @Override
            public void error(String message) {

            }
        }*/
        teachernext = findViewById(R.id.teachernext);
        teachernext.setEnabled(false);

        programSpinner = (Spinner) findViewById(R.id.teacherprogram);
        semesterSpinner = (Spinner) findViewById(R.id.teachersemester);
        sectionSpinner = (Spinner) findViewById(R.id.teachersection);
        subjectSpinner = (Spinner) findViewById(R.id.teachersubject);
        batch = (EditText)findViewById(R.id.batch);


        programAdapter = ArrayAdapter.createFromResource(this, R.array.program, android.R.layout.simple_spinner_item);//layout for each item in spinner
        semesterAdapter = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);//layout for each item in spinner
        sectionAdapter = ArrayAdapter.createFromResource(this, R.array.section, android.R.layout.simple_spinner_item);//layout for each item in spinner
       // subjectAdapter = ArrayAdapter.createFromResource(this, R.array.BIM_1ST, android.R.layout.simple_spinner_item);//layout for each item in spinner

        subjectSpinner.setEnabled(false);
         /*programSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProgramIndex = programSpinner.getSelectedItemPosition();

                if (selectedProgramIndex >0) {
                    subjectSpinner.setEnabled(true);
                    subjectSpinner.setAdapter(subjectAdapter);
                    subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//layout for each dropdown list for the spinner
                }else{
                    subjectSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                subjectSpinner.setEnabled(false);
            }
        });*/


       // Toast.makeText(this, batchValue, Toast.LENGTH_SHORT).show();
         //selected program
         programSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 program = programSpinner.getSelectedItem().toString();
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });

         //selected semester
         semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 semester = semesterSpinner.getSelectedItem().toString();
                 selectsubject();
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });

         //selected section
        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                section=sectionSpinner.getSelectedItem().toString();
               // Toast.makeText(TeacherLogin.this, batchValue, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //selected subject
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subject=subjectSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//layout for each dropdown list for the spinner
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//layout for each dropdown list for the spinner
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//layout for each dropdown list for the spinner


        programSpinner.setAdapter(programAdapter);
        semesterSpinner.setAdapter(semesterAdapter);
        sectionSpinner.setAdapter(sectionAdapter);

        teachernext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                batchValue =  batch.getText().toString();
                Intent intent = new Intent(TeacherLogin.this,TeacherLogin2.class);

                intent.putExtra("subject",subject);
                intent.putExtra("section",section);
                intent.putExtra("semester",semester);
                intent.putExtra("program",program);
                intent.putExtra("batch",batchValue);

                startActivity(intent);
//                startActivity(new Intent(TeacherLogin.this,TeacherLogin2.class));
            }
        });


    }



    public void selectsubject(){
            subjectSpinner.setEnabled(true);
        if (program.equals("BIM") && semester.equals("1st")){
            subjectAdapter = ArrayAdapter.createFromResource(TeacherLogin.this, R.array.BIM_1st, android.R.layout.simple_spinner_item);
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subjectSpinner.setAdapter(subjectAdapter);
            teachernext.setEnabled(true);
        }
        else if(program.equals("BBA")&& semester.equals("1st")){
            subjectSpinner.setEnabled(true);
            subjectAdapter = ArrayAdapter.createFromResource(TeacherLogin.this, R.array.BBA_1st, android.R.layout.simple_spinner_item);
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subjectSpinner.setAdapter(subjectAdapter);
            teachernext.setEnabled(true);
        }
        else if(program.equals("BBA")&& semester.equals("2nd")){
            subjectSpinner.setEnabled(true);
            subjectAdapter = ArrayAdapter.createFromResource(TeacherLogin.this, R.array.BBA_2nd, android.R.layout.simple_spinner_item);
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subjectSpinner.setAdapter(subjectAdapter);
            teachernext.setEnabled(true);
        }
        else if(program.equals("BBA")&& semester.equals("3rd")){
            subjectSpinner.setEnabled(true);
            subjectAdapter = ArrayAdapter.createFromResource(TeacherLogin.this, R.array.BBA_3rd, android.R.layout.simple_spinner_item);
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subjectSpinner.setAdapter(subjectAdapter);
            teachernext.setEnabled(true);
        }
        else if(program.equals("BBA")&& semester.equals("4th")){
            subjectSpinner.setEnabled(true);
            subjectAdapter = ArrayAdapter.createFromResource(TeacherLogin.this, R.array.BBA_4th, android.R.layout.simple_spinner_item);
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subjectSpinner.setAdapter(subjectAdapter);
            teachernext.setEnabled(true);
        }
        else if(program.equals("BBA")&& semester.equals("5th")){
            subjectSpinner.setEnabled(true);
            subjectAdapter = ArrayAdapter.createFromResource(TeacherLogin.this, R.array.BBA_5th, android.R.layout.simple_spinner_item);
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subjectSpinner.setAdapter(subjectAdapter);
            teachernext.setEnabled(true);
        }
        else if(program.equals("BBA")&& semester.equals("6th")){
            subjectSpinner.setEnabled(true);
            subjectAdapter = ArrayAdapter.createFromResource(TeacherLogin.this, R.array.BBA_6th, android.R.layout.simple_spinner_item);
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subjectSpinner.setAdapter(subjectAdapter);
            teachernext.setEnabled(true);
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

                break;

            case R.id.shareapp:
                break;

            case R.id.rateus:
                break;

            case R.id.disclaimer:
                showDisclaimer();
                break;
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
