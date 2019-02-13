package com.example.monthtwoproject.login.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.monthtwoproject.R;

public class MainActivity extends AppCompatActivity implements ILoginView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void getData(Object o) {

    }
}
