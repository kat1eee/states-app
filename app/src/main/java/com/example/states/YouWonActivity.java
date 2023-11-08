package com.example.states;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.TimeUnit;
import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class YouWonActivity extends AppCompatActivity {

    TextView tvCongratulations, tvYouWon;
    KonfettiView konfetti;
    Animation fadeIn;
    String playerName;

    MaterialButton bMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_youwon);

        playerName = getIntent().getStringExtra("name");

        bMainMenu = (MaterialButton) findViewById(R.id.bMainMenu);
        tvYouWon = (TextView) findViewById(R.id.tvYouWon);
        tvCongratulations = (TextView) findViewById(R.id.tvCongratulations);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        konfetti = (KonfettiView) findViewById(R.id.konfettiView);

        tvCongratulations.startAnimation(fadeIn);
        tvYouWon.startAnimation(fadeIn);
        bMainMenu.startAnimation(fadeIn);
        tvYouWon.setText("YOU WON! I'M PROUD OF YOU, "+playerName);

        View.OnClickListener oclBMainMenu= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(YouWonActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        };

        bMainMenu.setOnClickListener(oclBMainMenu);

        EmitterConfig enConf = new Emitter(5L, TimeUnit.SECONDS).perSecond(50);
        Party party = new PartyFactory(enConf)
                .angle(270)
                .spread(90)
                .setSpeedBetween(1f, 5f)
                .timeToLive(2000L)
                .shapes(new Shape.Rectangle(0.2f))
                .sizes(new Size(12, 5f, 0.2f))
                .position(0.0, 0.0, 1.0, 0.0)
                .build();

        konfetti.start(party);

    }

}
