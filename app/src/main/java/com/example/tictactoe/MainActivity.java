package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private ImageButton[][] buttons = new ImageButton[3][3];


    private boolean firstPlayer = true;

    private int roundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "mainactivity_btn" + i + j;
                int resId = getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }

        }
        Button buttonReset = findViewById(R.id.mainactivity_play_btn);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (((ImageButton) view).getBackground().equals(null)) {
            return;
        }

        if (firstPlayer) {
//            ((ImageButton) view).setText("x");
            ((ImageButton) view).setBackground(Drawable.createFromPath("drawable/x.png"));
        } else {
//            ((ImageButton) view).setText("o");
            ((ImageButton) view).setBackground(Drawable.createFromPath("drawable/o.png"));
        }

        roundCount++;

        if (checkFowWin()){
            if (firstPlayer){
                PlayerWins(1);
            } else {
                PlayerWins(2);
            }
        } else if(roundCount == 9) {
            PlayerWins(0);
        } else {
            firstPlayer = !firstPlayer;
        }
    }

    private void PlayerWins(int playerNum) {
        if (playerNum != 0) {
            Toast.makeText(this, "player" + playerNum + "WON!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "IT'S A DRAW", Toast.LENGTH_SHORT).show();
        }
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
//                buttons[i][j].setText("");
                buttons[i][j].setBackground(null);
            }
        }

        roundCount = 0;
        firstPlayer = true;
    }

    private boolean checkFowWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getBackground().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) &&
                    field[i][0].equals(field[i][2]) &&
                    !field[i][0].equals(null)) {
                // set win line
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) &&
                    field[0][i].equals(field[2][i]) &&
                    !field[0][i].equals(null)) {
                // set win line
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) &&
                field[0][0].equals(field[2][2]) &&
                !field[0][0].equals(null)) {
            // set win line
            return true;
        }

        if (field[0][2].equals(field[1][1]) &&
                field[0][0].equals(field[2][0]) &&
                !field[0][2].equals(null)) {
            // set win line
            return true;
        }

        return false;
    }
}