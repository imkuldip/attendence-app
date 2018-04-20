package com.example.kuldip.attendance.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuldip on 1/29/2018.
 */

public class Student {
   public String id, name, batch;
   public boolean isPresent;

   public Student(JSONObject obj){
        this.id = obj.optString("std_id");
        this.name = obj.optString("std_name");
        this.batch = obj.optString("batch");
        this.isPresent = false;  //no need to return?
    }

    public static List<Student> getListOfStudent(JSONArray array){
        List<Student> studentList = new ArrayList<>();
        for (int i = 0 ; i < array.length() ; i++){
            try {
                studentList.add(new Student(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  studentList;
    }

    @Override
    public String toString() {
        return "name = "+ name + " Id = " + id + " isPresent = "  + isPresent;
    }
}
