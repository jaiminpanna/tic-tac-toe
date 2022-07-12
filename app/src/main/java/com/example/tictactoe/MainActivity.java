package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive= true;
    //Player representation
    //0-x
    //1-o
    int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //State meanings:
    //0 - x
    //1 - o
    //2 - Null
    int [][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                             {0,3,6},{1,4,7},{2,5,8},
                             {0,4,8},{2,4,6}};
    public void PlayerTap(View view){
        ImageView img= (ImageView)view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(view);
            return;
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x1);
                activePlayer = 1;
                TextView status = findViewById(R.id.Status);
                status.setText("o's Turn - Tap to play");
            }
            else {
                img.setImageResource(R.drawable.o1);
                activePlayer = 0;
                TextView status = findViewById(R.id.Status);
                status.setText("x's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(5);
        }
        //Check if any player has won
        for (int[] winPosition: winPositions){
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                gameState[winPosition[1]] == gameState[winPosition[2]] &&
                gameState[winPosition[0]]!=2) {
                //Somebody has won!Find out who!
                String winnerStr;
                if (gameState[winPosition[0]] ==0){
                    winnerStr="X has won";
                }
                else {
                    winnerStr="O has won";
                }
                //Update the status bar for winner announcement
                TextView status = findViewById(R.id.Status);
                status.setText(winnerStr);
                gameActive = false;
            }
        }
        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            String winnerStr;
            winnerStr = "No one won";
            TextView status = findViewById(R.id.Status);
            status.setText(winnerStr);
        }
    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for (int i=0; i<gameState.length; i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        Toast.makeText(getApplicationContext(),"Game Reset",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"Welcome To Yash's Game",Toast.LENGTH_SHORT).show();
    }
}