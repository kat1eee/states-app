package com.example.states;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {

    EditText etName;
    MaterialButton bStart2;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        etName = (EditText) findViewById(R.id.etNameInput);
        bStart2 = (MaterialButton) findViewById(R.id.bStart2);

        View.OnClickListener oclBStart2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerName = etName.getText().toString().toUpperCase();

                Intent intent = new Intent(NameActivity.this, QuizActivity.class);
                intent.putExtra("name", playerName);
                startActivity(intent);
            }
        };
bStart2.setOnClickListener(oclBStart2);

    }}

