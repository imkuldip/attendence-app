package com.example.kuldip.attendance;

/**
 * Created by Kuldip on 1/9/2018.
 */

public interface OnDataReceived {
    void success(String data);
    void error(String message);
}
