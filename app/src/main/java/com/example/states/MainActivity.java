package com.example.states;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.*;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.*;


public class MainActivity extends AppCompatActivity {
    MaterialButton bStartMain, bHighscores;
    Animation fadeIn;
    TextView tvHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bStartMain = (MaterialButton) findViewById(R.id.bStart);
        bHighscores = (MaterialButton) findViewById(R.id.bHighscores);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        tvHeader = (TextView) findViewById(R.id.tvHeader);

        bStartMain.startAnimation(fadeIn);
        tvHeader.startAnimation(fadeIn);
        bHighscores.startAnimation(fadeIn);


        View.OnClickListener oclBStart= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                startActivity(intent);
            }
        };

        View.OnClickListener oclBHighscores= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, HighscoresActivity.class);
                startActivity(intent1);
            }
        };

        bStartMain.setOnClickListener(oclBStart);
        bHighscores.setOnClickListener(oclBHighscores);

    }

}