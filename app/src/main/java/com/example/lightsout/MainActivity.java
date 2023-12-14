package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    LightModel model;
    LightView view;
    LightController controller;
    Button resetButton;

    /**
     * initialize model view controller and the appropriate objects from the android view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.LightView);
        resetButton = findViewById(R.id.resetButton);
        controller = new LightController(view);
        model = view.getModel();
        view.setOnTouchListener(controller);
        resetButton.setOnClickListener(controller);
    }
}