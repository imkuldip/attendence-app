package com.example.kuldip.attendance;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kuldip on 1/27/2018.
 */

public class GeneralUtil {


   public static String getDate(long timeStamp) {

        try {
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//MM/dd/yyyy
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        } catch (Exception ex) {
            return "xx";
        }
    }
}
