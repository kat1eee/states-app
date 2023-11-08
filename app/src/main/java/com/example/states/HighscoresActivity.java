package com.example.states;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.*;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class HighscoresActivity extends AppCompatActivity {

    KonfettiView konfetti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        konfetti = (KonfettiView) findViewById(R.id.konfettiView);
        Shape.DrawableShape drawableShape = null;

        final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.heart);
        drawableShape = new Shape.DrawableShape(drawable, true, true);
        EmitterConfig enConf = new Emitter(7L, TimeUnit.SECONDS).perSecond(50);
        Party party = new PartyFactory(enConf)
                .angle(270)
                .spread(90)
                .setSpeedBetween(1f, 5f)
                .timeToLive(3900L)
                .shapes(drawableShape)
                .sizes(new Size(35, 5f, 2f))
                .position(0.0, 0.0, 1.0, 0.0)
                .build();

        konfetti.start(party);


        View.OnClickListener oclBStart2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };





    }}

