package com.example.kuldip.attendance;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class StudentLogin1 extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login1);



        setTitle("Check Attendance");

        findViewById(R.id.checkattendance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(StudentLogin1.this,StudentLogin2.class));
            }
        });

       /* MenuOption menuOption =  new MenuOption();
        menuOption.onCreateOptionsMenu( Menu menu);
        menuOption.onOptionsItemSelected(MenuItem item);*/


       // openFragment(new NoticeFragment(), "Notice");

    }

  /*  public void openFragment(Fragment f ,  String title){
        setTitle(title);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, f).commit();
    }*/



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

  /*  public interface OnDataReceived {
        void success(String data);
        void error(String message);
    }*/



}
