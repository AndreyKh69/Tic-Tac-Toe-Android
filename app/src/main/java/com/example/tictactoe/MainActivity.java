package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private ImageButton[][] buttons = new ImageButton[3][3];


    private boolean firstPlayer;

    private int roundCount;

    // set listeners
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
        resetBoard();
        Button buttonReset = findViewById(R.id.mainactivity_play_btn);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("RESET");
                resetBoard();
            }
        });
    }


    @Override
    public void onClick(View view) {

        // if clicked on nonnull place
        if ((view).getTag() != "" )  {
            return;
        }

        // if clicked on initial, set Tag and Image
        if (firstPlayer) {
            (view).setTag("x");
            ((ImageButton) view).setImageResource(R.drawable.x);

        } else {
            (view).setTag("o");
            ((ImageButton) view).setImageResource(R.drawable.o);
        }

        roundCount++;

        // check if the move creates a win
        if (checkFowWin()){
            if (firstPlayer){
                PlayerWins(1);
            } else {
                PlayerWins(2);
            }
            // check for a draw
        } else if(roundCount == 9) {
            PlayerWins(0);
        } else {
            firstPlayer = !firstPlayer;
        }

    }

    // method to show a winning status
    private void PlayerWins(int playerNum) {
        ImageView imageView = findViewById(R.id.imageviewStatus);
        if (playerNum == 1) {
            imageView.setImageResource(R.drawable.xwin);
        } else if (playerNum == 2) {
            imageView.setImageResource(R.drawable.owin);
        } else {
//            Toast.makeText(this, "IT'S A DRAW", Toast.LENGTH_SHORT).show();
            imageView.setImageResource(R.drawable.nowin);
        }
//        resetBoard();
    }

    // method to reset the board
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setTag("");
                buttons[i][j].setImageResource(R.drawable.empty);
            }
        }
        ImageView imageView = findViewById(R.id.imageviewStatus);
        imageView.setImageResource(R.drawable.status);
        roundCount = 0;
        firstPlayer = true;
    }

    private boolean checkFowWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getTag().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0] == (field[i][1]) &&
                    field[i][0] == (field[i][2]) &&
                    field[i][0] != ("")) {
                // set win line
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == (field[1][i]) &&
                    field[0][i] == (field[2][i]) &&
                    field[0][i] != ("")) {
                // set win line
                return true;
            }
        }

        if (field[0][0] == (field[1][1]) &&
                field[0][0] == (field[2][2]) &&
                field[0][0] != ("")) {
            // set win line
            return true;
        }

        if (field[0][2] == (field[1][1]) &&
                field[0][0] == (field[2][0]) &&
                field[0][2] != ("")) {
            // set win line
            return true;
        }

        return false;
    }
}