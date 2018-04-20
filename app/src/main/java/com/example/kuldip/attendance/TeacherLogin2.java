package com.example.kuldip.attendance;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.kuldip.attendance.adapter.AdapterStudent;
import com.example.kuldip.attendance.model.Student;
import com.example.kuldip.attendance.utils.Constants;
//import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class TeacherLogin2 extends AppCompatActivity {


    String fetchstudenturl = Constants.BASE_URL +"fetchstudent.php";
    String addattendance= Constants.BASE_URL+"addattendance.php";

    AQuery aquery;
    TextView tname, subname, sec, date;
    Button submit;

    SharedPreferences preferences;
    String subject, section, semester, title, program, username, batch,tid,std_name, std_batch,std_id;

    RecyclerView rvAttendance;
    List<Student> list;
    AdapterStudent adapterStudent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_teacher_login2);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        aquery = new AQuery(this);

        list = new ArrayList<>();

        submit = findViewById(R.id.submit);
        rvAttendance = findViewById(R.id.rv_attendance);
        rvAttendance.setLayoutManager(new LinearLayoutManager(this));
        rvAttendance.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        batch = getIntent().getStringExtra("batch");
        semester = getIntent().getStringExtra("semester");
        subject = getIntent().getStringExtra("subject");
        section = getIntent().getStringExtra("section");
        program = getIntent().getStringExtra("program");

        title = program + " " + semester;
        setTitle(title);

        preferences = getSharedPreferences("Userinfo", 0);
        username = preferences.getString("username", "");
        tid = preferences.getString("tid","");

        tname = findViewById(R.id.tname);
        subname = findViewById(R.id.subname);
        sec = findViewById(R.id.sec);
        date = findViewById(R.id.date);

        tname.setText(username);
        subname.setText(subject);
        sec.setText(section);
        String date1 = new SimpleDateFormat("yyy-MM-dd", Locale.getDefault()).format(new Date());
        date.setText(date1);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        //fetch student info
        fetchStudentInfo();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addattendance(getAttendance());
          // addattendance();
          // getAttendance();
            }
        });
    }

    public void fetchStudentInfo() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("batch","2014");
        param.put("section", section);
        param.put("program", program);
        aquery.ajax(fetchstudenturl, param, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray array, AjaxStatus status) {
                adapterStudent = new AdapterStudent(TeacherLogin2.this,  Student.getListOfStudent(array));
                rvAttendance.setAdapter(adapterStudent);
            }
        });
    }

    //public JSONArray getAttendance() {
//    public void getAttendance() {
//           if (adapterStudent != null) {
//               //JSONArray array = new JSONArray();
//               List<Student> students = adapterStudent.getList();
//              // Toast.makeText(this, "students:"+students, Toast.LENGTH_SHORT).show();
//
//               JsonElement a = new Gson().toJsonTree(students,
//                       new TypeToken<List<Student>>() {
//                       }.getType());
//               Toast.makeText(this, "array value:"+a, Toast.LENGTH_SHORT).show();

//               for (int i = 0; i < students.size(); i++) {
//                   JSONObject ob = new JSONObject();

//                   try {
//                       if (list.get(i).isPresent) {
//                           ob.put("is_present", true);
//                            ob.put("std_id", list.get(i).id);
//                            ob.put("std_name",list.get(i).name);
//                       }else{
//                       ob.put("is_Present", false);
//                       ob.put("std_id", list.get(i).id);
//                       ob.put("std_name",list.get(i).name);
//                   }
//                       array.put(ob);
//                   } catch (JSONException e) {
//                       e.printStackTrace();
//                   }

//               }

               //Toast.makeText(this, "Data="+array, Toast.LENGTH_SHORT).show();
               //Log.d("ATEST", "getAttendance:"+array);
//           }
               //return array;
    //}

    public  JsonElement getAttendance() {
        JsonElement a=null;
        if (adapterStudent != null) {
            List<Student> students = adapterStudent.getList();
             a = new Gson().toJsonTree(students,
                    new TypeToken<List<Student>>() {}.getType());
            //Toast.makeText(this, "array value:"+a, Toast.LENGTH_SHORT).show();
        }
        return a;
    }

    public void addattendance(JsonElement jsonElement){
        Toast.makeText(this, "array value:"+jsonElement, Toast.LENGTH_SHORT).show();
    //public void addattendance(JSONArray array){
           HashMap<String, Object> param = new HashMap<String, Object>();
//           param.put("batch","2014");
//           param.put("tid",tid);
//           param.put("semester", semester);
//           param.put("section", section);
//           param.put("subject", subject);
//           param.put("program",program);
           param.put("data",String.valueOf(jsonElement));

           aquery.ajax(addattendance,param,String.class, new AjaxCallback<String>(){
         //  aquery.ajax(addattendance, param, JSONObject.class, new AjaxCallback<JSONObject>() {
               @Override
              public void callback(String url,String s, AjaxStatus status) {
              //public void callback(String url, JSONObject object, AjaxStatus status) {
                //{"status":false}
                   Toast.makeText(TeacherLogin2.this, "Respoisne " +s , Toast.LENGTH_LONG).show();
                   try {
                       JSONObject object = new JSONObject(s);
                       String result = object.getString("status");
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
                   if(result.equals("false")){
                      submit.setEnabled(false);
                   }
               }
           });
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
