package com.example.kuldip.attendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.kuldip.attendance.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    String usernameValue, passwordValue;
    DatabaseHelper databaseHelper;
    Spinner userType;
    ArrayAdapter userTypeAdapter;
    String userSelected;
    SharedPreferences preferences;//sharedpreference is class file that saves data in xml file

    AQuery aquery;

    String loginurl= Constants.BASE_URL + "login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        preferences = getSharedPreferences("Userinfo", 0);


        aquery = new AQuery(this);
        username=findViewById(R.id.username);
        password =findViewById(R.id.password);

        userType = (Spinner) findViewById(R.id.usertype);

        userTypeAdapter = ArrayAdapter.createFromResource(this,R.array.usertype,android.R.layout.simple_spinner_item);
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//layout for each dropdown list for the spinner

        userType.setAdapter(userTypeAdapter);

        userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // userSelected= (String) userType.getSelectedItem();
                userSelected=  userType.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



//
//       findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               verifyUser();
//           }
//       });
//       findViewById(R.id.login).setOnClickListener(view ->{
//           verifyUser();
//
//       });

        //Login Verification
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameValue = username.getText().toString();
                passwordValue = password.getText().toString();
                verifyUser();
            }
            public void verifyUser(){

                HashMap<String,Object> param =  new HashMap<String,Object>();
                param.put("username","harendra Subedi");
                param.put("password","SogC9nVF");
                param.put("usertype",userSelected);

                //aquery.ajax(loginurl,param,String.class, new AjaxCallback<String>(){
                aquery.ajax(loginurl,param,JSONObject.class, new AjaxCallback<JSONObject>(){
                    // aquery.ajax(loginurl,param,JSONArray.class, new AjaxCallback<JSONArray>(){

                    @Override
                    //public void callback(String url,String s, AjaxStatus status) {
                    public void callback(String url,JSONObject object, AjaxStatus status) {
                        Toast.makeText(LoginActivity.this, object + "", Toast.LENGTH_LONG).show();

                        //{"status":false,"message":"Invalid Username or Password!"}
                        //{"teacher":{"teacher_id":"4","teacher_name":"Indra PC"},"status":true,"user":{"id":"17","username":"Indra PC","usertype":"teacher","password":"nFkr0lqY","email":"thesane@gmail.com"},"message":"Login Sucessfull !!"}
                        //{"student":{"std_id":"3","batch":"2014","program":"BIM","section":"B"},"status":true,"user":{"id":"45","username":"Kuldip Bhochhibhoya","usertype":"student","password":"937be2v1yg","email":"k6857053@gmail.com"},"message":"Login Sucessfull !!"}
                        //{"admin":{"username":"admin"},"status":true,"user":{"id":"50","username":"admin","usertype":"admin","password":"kcmit_admin","email":""},"message":"Login Sucessfull !!"}

                        UserInfo  userInfo = new UserInfo();
                        try {
                            SharedPreferences.Editor editor = preferences.edit();

                            //JSONObject object = new JSONObject(s);
                            String responseStatus = object.getString("status");
                            // Toast.makeText(LoginActivity.this, responseStatus + "", Toast.LENGTH_LONG).show();
                            String message = object.getString("message");


                            if(responseStatus.equals("true")){
                                JSONObject userObj = object.getJSONObject("user");
                                userInfo.uid= userObj.getString("id");
                                userInfo.username= userObj.getString("username");
                                userInfo.usertype=userObj.getString("usertype");
                                userInfo.email=userObj.getString("email");
                                editor.putString("userid",userInfo.uid);
                                editor.putString("username",userInfo.username);
                                editor.putString("usertype",userInfo.usertype);

                                if(userSelected.equals("Student")) {
                                    JSONObject stdObj = object.getJSONObject("student");
                                    userInfo.sid = stdObj.getString("std_id");
                                    userInfo.batch = stdObj.getString("batch");
                                    userInfo.program = stdObj.getString("program");
                                    userInfo.section = stdObj.getString("section");
                                    editor.putString("stdid", userInfo.sid);
                                    editor.putString("batch", userInfo.batch);
                                    editor.putString("program", userInfo.program);
                                    editor.putString("section", userInfo.section);
                                }

                                if(userSelected.equals("Teacher")) {
                                    JSONObject tObj = object.getJSONObject("teacher");
                                    userInfo.tid = tObj.getString("teacher_id");
                                    userInfo.tname = tObj.getString("teacher_name");
                                    editor.putString("tid", userInfo.tid);
                                    editor.putString("tname", userInfo.tname);
                                }
                                if(userSelected.equals("Admin")) {
                                    JSONObject tObj = object.getJSONObject("admin");
                                    userInfo.aname = tObj.getString("username");
                                    editor.putString("aname", userInfo.aname);
                                }
                                editor.apply();
                            }
                            if(responseStatus.equals("true")){
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                                if (userSelected.equals("Admin")) {
                                    Intent intent = new Intent(LoginActivity.this, AdminLogin1.class);
                                    startActivity(intent);
                                } else if (userSelected.equals("Teacher")) {
                                    Intent intent = new Intent(LoginActivity.this, TeacherLogin.class);
                                    startActivity(intent);
                                } else if (userSelected.equals("Student")) {
                                    Intent intent = new Intent(LoginActivity.this, StudentLogin1.class);
                                    startActivity(intent);
                                }
                            }
                            else{
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
