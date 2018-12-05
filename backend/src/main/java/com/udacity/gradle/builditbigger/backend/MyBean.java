package com.udacity.gradle.builditbigger.backend;

import com.example.android.jokelibrary.Jokes;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    @SuppressWarnings("UnusedReturnValue")
    public String getData() {
        myData = Jokes.tellJoke();
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}