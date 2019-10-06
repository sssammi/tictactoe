package io.github.sssammi.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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
//        editor.putString("billAmountString", billAmountString);
//        editor.putFloat("tipPercent", tipPercent);
//        editor.putInt("rounding", rounding);
//        editor.putInt("split", split);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        // get the instance variables
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
        nTurnCount++;
        Integer viewId = v.getId();
        String turn = "";
        while(nTurnCount < 10){}

        //check whose turn it is now
        if (nTurnCount % 2 == 1)
        {
            turn="X";
        }
        else if (nTurnCount % 2 == 0) {
            turn="O";
        }
        this.textDisplayMsg.setText("Player " + turn + "\'s turn");

        switch(v.getId())
        {
            case R.id.btnNewGame:
                // clear all the buttons and set turn count back to zero
                nTurnCount = 0;
                aButtons[0][0].setText("");
                aButtons[0][1].setText("");
                aButtons[0][2].setText("");
                aButtons[1][0].setText("");
                aButtons[1][1].setText("");
                aButtons[1][2].setText("");
                aButtons[2][0].setText("");
                aButtons[2][1].setText("");
                aButtons[2][2].setText("");
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
        checkWin();
        //Toast.makeText(this, turn + " Turn count: " + nTurnCount, Toast.LENGTH_LONG).show();

    }

    private void checkWin() {
        //check to see if win condition is true
        //check turn count, if 9, this is the last turn (evaluate for tie)
        Boolean win = false;
        //while(win == false) {
            String row1 = aButtons[0][0].getText().toString() + aButtons[0][1].getText().toString() + aButtons[0][2].getText().toString();
            Toast.makeText(this, row1, Toast.LENGTH_LONG).show();

        //}

    }
}
