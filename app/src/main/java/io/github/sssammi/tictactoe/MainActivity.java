package io.github.sssammi.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements TextView.OnEditorActionListener, View.OnClickListener{

    Button aButtons[][] = new Button[3][3];
    TextView textDisplayMsg;
    Button btnNewGame;
    Integer nTurnCount = 0;
    String turn = "";

    // define the SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize buttons
        aButtons[0][0] = findViewById(R.id.button00);
        aButtons[0][1] = findViewById(R.id.button01);
        aButtons[0][2] = findViewById(R.id.button02);
        aButtons[1][0] = findViewById(R.id.button10);
        aButtons[1][1] = findViewById(R.id.button11);
        aButtons[1][2] = findViewById(R.id.button12);
        aButtons[2][0] = findViewById(R.id.button20);
        aButtons[2][1] = findViewById(R.id.button21);
        aButtons[2][2] = findViewById(R.id.button22);

        // initialize attributes
        this.textDisplayMsg = findViewById(R.id.textDisplayMsg);
        this.btnNewGame = findViewById(R.id.btnNewGame);

        // wire widgets to the events
        this.btnNewGame.setOnClickListener(this);
        for(int j = 0; j < 3; j++){
            for(int i = 0; i < 3; i++){
                this.aButtons[i][j].setOnClickListener(this);
            }
        }

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause() {
        // save the instance variables
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putInt("nTurnCount", nTurnCount);
        editor.putString("turn", turn);
        editor.commit();

        super.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();

        // get the instance variables
        nTurnCount = savedValues.getInt("nTurnCount", nTurnCount);
        turn = savedValues.getString("turn", turn);
//        billAmountString = savedValues.getString("billAmountString", "");
//        tipPercent = savedValues.getFloat("tipPercent", 0.15f);
//        rounding = savedValues.getInt("rounding", ROUND_NONE);
//        split = savedValues.getInt("split", 1);
    }

    @Override
    public boolean onEditorAction(TextView textViewV, int nId, KeyEvent keyEventEvt){

        return false;
    }

    @Override
    public void onClick(View v){
        Integer viewId = v.getId();

        //check whose turn it is now
        if (nTurnCount % 2 == 1)
        {
            turn="O";
        }
        else if (nTurnCount % 2 == 0) {
            turn="X";
        }

        switch(v.getId())
        {
            case R.id.btnNewGame:
                // clear all the buttons and set turn count back to zero
                nTurnCount = 0;
                for(int j = 0; j < 3; j++){
                    for(int i = 0; i < 3; i++){
                        this.aButtons[i][j].setText("");
                    }
                }
                this.textDisplayMsg.setText("Player X's turn");
                break;
            case R.id.button00:
                //do something
                aButtons[0][0].setText(turn);
                break;
            case R.id.button01:
                //do something
                aButtons[0][1].setText(turn);
                break;
            case R.id.button02:
                //do something
                aButtons[0][2].setText(turn);
                break;
            case R.id.button10:
                //do something
                aButtons[1][0].setText(turn);
                break;
            case R.id.button11:
                //do something
                aButtons[1][1].setText(turn);
                break;
            case R.id.button12:
                //do something
                aButtons[1][2].setText(turn);
                break;
            case R.id.button20:
                //do something
                aButtons[2][0].setText(turn);
                break;
            case R.id.button21:
                //do something
                aButtons[2][1].setText(turn);
                break;
            case R.id.button22:
                aButtons[2][2].setText(turn);
                break;
        }

        // call to check for a win, and if this is the 9th turn, we will declare a draw
        if(checkWin()){
            for(int j = 0; j < 3; j++){
                for(int i = 0; i < 3; i++){
                    this.aButtons[i][j].setText("");
                }
            }
            nTurnCount = 0;
            this.textDisplayMsg.setText("Player " + turn + " wins!");
        } else if (nTurnCount == 9){
            this.textDisplayMsg.setText("It's a draw! Please play again.");
        } else {
            nTurnCount++;
            if (nTurnCount % 2 == 1)
            {
                turn="O";
            }
            else if (nTurnCount % 2 == 0) {
                turn="X";
            }
            this.textDisplayMsg.setText("Player " + turn + "\'s turn");
        }
    }

    private boolean checkWin() {
        //check to see if win condition is true
        String[][] aFields = new String[3][3];

        //turn all the buttons' values into strings so we can compare them
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                aFields[i][j] = aButtons[i][j].getText().toString();
            }
        }

        //checking for a 'row win'
        for (int i = 0; i < 3; i++){
            if(aFields[i][0].equals(aFields[i][1])
                    && aFields[i][0].equals(aFields[i][2]) && !aFields[i][0].equals("")){
                return true;
            }
        }

        //checking for 'column win'
        for (int i = 0; i < 3; i++){
            if(aFields[0][i].equals(aFields[1][i])
                    && aFields[0][i].equals(aFields[2][i]) && !aFields[0][i].equals("")){
                return true;
            }
        }

        //checking for both diagonal wins
        //top left to bottom right
        if(aFields[0][0].equals(aFields[1][1])
                && aFields[1][1].equals(aFields[2][2]) && !aFields[0][0].equals("")){
            return true;
        }
        //top right to bottom left
        if(aFields[0][2].equals(aFields[1][1])
                && aFields[1][1].equals(aFields[2][0]) && !aFields[0][2].equals("")){
            return true;
        }
        return false;
    }
}
