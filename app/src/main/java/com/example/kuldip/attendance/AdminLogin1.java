package com.example.kuldip.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AdminLogin1 extends AppCompatActivity {

    Spinner programSpinner,semesterSpinner;
    ArrayAdapter programAdapter, semesterAdapter;
    Button nextbtn;
    String program,semester;
    int selectedProgramIndex, selectedSemesterIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login1);
        setTitle("KCMIT Attendence");

        programSpinner = (Spinner) findViewById(R.id.adminprogram);
        semesterSpinner = (Spinner) findViewById(R.id.adminsemester);
        nextbtn = (Button) findViewById(R.id.adminnext1);

        programAdapter =  ArrayAdapter.createFromResource(this,R.array.program,android.R.layout.simple_spinner_item);//layout for each item in spinner
        semesterAdapter =  ArrayAdapter.createFromResource(this,R.array.semester,android.R.layout.simple_spinner_item);//layout for each item in spinner

        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//layout for each dropdown list for the spinner
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//layout for each dropdown list for the spinner

        programSpinner.setAdapter(programAdapter);
        semesterSpinner.setAdapter(semesterAdapter);

        programSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                program = programSpinner.getSelectedItem().toString();
//                selectedProgramIndex = programSpinner.getSelectedItemPosition();
//                Toast.makeText(AdminLogin1.this, selectedProgramIndex, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                semester = semesterSpinner.getSelectedItem().toString();
//                selectedProgramIndex = programSpinner.getSelectedItemPosition();
//                Toast.makeText(AdminLogin1.this, selectedProgramIndex, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogin1.this,AdminLogin2.class);
                intent.putExtra("program",program);
                intent.putExtra("semester",semester);
                startActivity(intent);

            }
        });



    }
}
