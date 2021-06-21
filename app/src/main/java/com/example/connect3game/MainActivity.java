package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2,2,2,2,2,2,2,2,2};   // 0:circle , 1:red  , 2:empty

    int[] [] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int coinType =0;  // 0:circle , 1:cross

    boolean gameActive = true;




    @SuppressLint("SetTextI18n")
    public void dropin(View view){

        ImageView counter = (ImageView)  view ;
        TextView tv = (TextView) findViewById(R.id.tv);



        //counter.getTag();    // to get specific place
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive) {


            gameState[tappedCounter] = coinType;

            counter.setTranslationY(-1500);

            if (coinType == 0) {
                counter.setImageResource(R.drawable.yellow);
                coinType = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                coinType = 0;
            }


            counter.animate().translationYBy(1500).setDuration(200);

            for (int[] x : winningPositions) {

                if (gameState[x[0]] == gameState[x[1]] && gameState[x[1]] == gameState[x[2]] && gameState[x[0]] != 2) {

                    gameActive=false;

                    Toast.makeText(this, "Hurrray!!!!!", Toast.LENGTH_SHORT).show();
                    tv.setVisibility(View.VISIBLE);

                    if (gameState[x[0]] == 1) {
                        tv.setText("Cross Won!");
                    } else if (gameState[x[0]] == 0) {
                        //circle
                        tv.setText("Circle Won!");
                    }

                    Button bt = (Button) findViewById(R.id.play);
                    bt.setVisibility(View.VISIBLE);



                }

            }

        }



    }
    public void play(View view){
        Button bt = (Button) findViewById(R.id.play);
        bt.setVisibility(View.INVISIBLE);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setVisibility(View.INVISIBLE);
        
        GridLayout gd=(GridLayout) findViewById(R.id.gridLayout);

        Log.i("playBtn","pressed");




        for(int i = 0; i < gd.getChildCount(); i++) {
            ImageView childImageView = (ImageView) gd.getChildAt(i);
            childImageView.setImageDrawable(null);
        }


        Arrays.fill(gameState, 2);

             coinType =0;
             gameActive = true;

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}