package com.example.states;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

public class GameOverActivity extends AppCompatActivity {
    MaterialButton bRestart, bMainMenu;
    TextView tvImSorry, tvGameOver, tvAnswer;
    String playerName, answer;
    Animation fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gameover_activity);

        playerName = getIntent().getStringExtra("name");
        answer = getIntent().getStringExtra("answer");


        bRestart = (MaterialButton) findViewById(R.id.bRestart);
        bMainMenu = (MaterialButton) findViewById(R.id.bMainMenu);
        tvImSorry = (TextView) findViewById(R.id.tvImSorry);
        tvGameOver = (TextView) findViewById(R.id.tvGameOver);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        tvGameOver.startAnimation(fadeIn);
        tvImSorry.startAnimation(fadeIn);
        bMainMenu.startAnimation(fadeIn);
        bRestart.startAnimation(fadeIn);

        tvImSorry.setText("I'M SORRY, "+playerName+" :(");
        tvAnswer.setText("ANSWER: "+answer);

        View.OnClickListener oclBRestart= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOverActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        };

        View.OnClickListener oclBMainMenu= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        };

        bRestart.setOnClickListener(oclBRestart);
        bMainMenu.setOnClickListener(oclBMainMenu);

    }

}