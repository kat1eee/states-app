package com.example.states;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class QuizActivity extends AppCompatActivity {
MaterialButton bChoice1, bChoice2, bChoice3, bChoice4;
ImageView ivState, ivHeart1, ivHeart2, ivHeart3;
TextView tvStateName;
String st, playerName;
Animation fadeOut, fadeIn;
int score = 3;

    public static boolean find(String[] randomCapitals, String answer)
    {
        boolean b = false;
        for (int i = 0; i<randomCapitals.length; i++)
        {
            if (randomCapitals[i].equals(answer))
            {b = true; break;}
        }
        return b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        playerName = getIntent().getStringExtra("name");

        bChoice1 = (MaterialButton) findViewById(R.id.bChoice1);
        bChoice2 = (MaterialButton) findViewById(R.id.bChoice2);
        bChoice3 = (MaterialButton) findViewById(R.id.bChoice3);
        bChoice4 = (MaterialButton) findViewById(R.id.bChoice4);
        ivState = (ImageView) findViewById(R.id.ivState);
        ivHeart1 = (ImageView) findViewById(R.id.ivHeart1);
        ivHeart2 = (ImageView) findViewById(R.id.ivHeart2);
        ivHeart3 = (ImageView) findViewById(R.id.ivHeart3);
        tvStateName = (TextView) findViewById(R.id.tvStateName);
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);


        MaterialButton[] bArray = new MaterialButton[4];
        bArray[0] = bChoice1;
        bArray[1] = bChoice2;
        bArray[2] = bChoice3;
        bArray[3] = bChoice4;

        HashMap<String, String> statesMap = new HashMap<String, String>();

        InputStream is = getResources().openRawResource(R.raw.states);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((st = br.readLine()) != null)
            {
                statesMap.put(st.split(" ")[0].replace('_', ' '), st.split(" ")[1].replace('_', ' '));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Random generator = new Random();
        Vector <String> capitalsVect = new Vector();
        Vector <String> statesVect = new Vector();
        String[] randomCapitals = new String[4];
        Object[] statesArr =  statesMap.entrySet().toArray();
        Object[] capitalsArr = statesMap.values().toArray();
        final String[] randState = new String[1];
        final String[] answer = new String[1];

        for (int i = 0; i<statesArr.length; i++)
        {
            statesArr[i] = statesArr[i].toString().split("=")[0];
            statesVect.add(statesArr[i].toString());
        }
        for (int i = 0; i<capitalsArr.length; i++){
            capitalsVect.add(capitalsArr[i].toString());
        }
        randState[0] = statesVect.get(generator.nextInt(statesVect.size()));
        tvStateName.setText(randState[0]);
        int resID = getResources().getIdentifier(tvStateName.getText().toString().toLowerCase().replaceAll(" ", ""), "drawable", getPackageName());
        //System.out.println(tvStateName.getText().toString().toLowerCase().replaceAll(" ", ""));
        ivState.setImageResource(resID);
        ivState.startAnimation(fadeIn);
        answer[0] = statesMap.get(randState[0]).toString();

        List<String> copy = new ArrayList<String>(capitalsVect);
        Collections.shuffle(copy);
        for (int i = 0; i<4; i++)
        {
            randomCapitals[i] = copy.get(i);
        }
        if (!find(randomCapitals, answer[0]))
            randomCapitals[generator.nextInt(4)] = answer[0];

        View.OnClickListener oclBOption = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialButton b = (MaterialButton) view;
                if (b.getText().equals(answer[0]))
                {
                    b.setBackgroundColor(Color.GREEN);
                    statesVect.remove(randState[0]);

                        if (statesVect.size() != 0)
                        randState[0] = statesVect.get(generator.nextInt(statesVect.size()));
                        else
                        {
                            Intent intent3 = new Intent(QuizActivity.this, YouWonActivity.class);
                            intent3.putExtra("name", playerName);
                            startActivity(intent3);
                        }

                    answer[0] = statesMap.get(randState[0]);

                    tvStateName.startAnimation(fadeIn);
                    tvStateName.setText(randState[0]);

                    Collections.shuffle(copy);
                    for (int i = 0; i<4; i++)
                    {
                        randomCapitals[i] = copy.get(i);
                        bArray[i].setClickable(true);
                    }
                    if (!find(randomCapitals, answer[0]))
                        randomCapitals[generator.nextInt(4)] = answer[0];


                    int resID = getResources().getIdentifier(tvStateName.getText().toString().toLowerCase().replaceAll(" ", ""), "drawable", getPackageName());
                    //System.out.println(tvStateName.getText().toString().toLowerCase().replaceAll(" ", ""));
                    ivState.setImageResource(resID);
                    ivState.startAnimation(fadeIn);
                    for (int i = 0; i<4; i++)
                    {
                        bArray[i].setBackgroundColor(Color.parseColor("#2196F3"));
                            bArray[i].setText(randomCapitals[i]);
                    }

                }
                else
                {
                    b.setClickable(false);
                    b.setBackgroundColor(Color.RED);
                    switch (score){
                        case 3:
                            ivHeart3.startAnimation(fadeOut);
                            break;
                        case 2:
                            ivHeart3.setVisibility(View.INVISIBLE);
                            ivHeart3.clearAnimation();
                            ivHeart2.startAnimation(fadeOut);
                            break;
                        case 1:
                            ivHeart3.setVisibility(View.INVISIBLE);
                            ivHeart3.clearAnimation();
                            ivHeart2.setVisibility(View.INVISIBLE);
                            ivHeart2.clearAnimation();
                            ivHeart1.startAnimation(fadeOut);

                            break;
                    }
                    score--;

                    if (score==0)
                    {
                        Intent intent = new Intent(QuizActivity.this, GameOverActivity.class);
                        intent.putExtra("name", playerName);
                        intent.putExtra("answer", answer[0]);
                        startActivity(intent);
                    }
                }
            }
        };
            for (int i = 0; i<4; i++)
            {
                bArray[i].setOnClickListener(oclBOption);
                bArray[i].setText(randomCapitals[i]);
            }
    }
}

