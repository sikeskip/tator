package com.pideriver.a2017tatorscoutfirststeamworks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;

public class ScoutSignIn extends AppCompatActivity {
    Context context = this;

    Button toSetup;

    AutoCompleteTextView scoutName;

    Spinner groupSpinner;

    Toast toast;

    String[] spinnerAry;

    SharedPreferences preferences = this.getSharedPreferences("preferences",Context.MODE_PRIVATE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scout_sign_in);



        //seting up widgets
        toSetup = (Button)findViewById(R.id.btnToSetup);
        scoutName = (AutoCompleteTextView)findViewById(R.id.txtScoutName);
        groupSpinner = (Spinner)findViewById(R.id.spnGroupSpinner);

        //setting up spinner
        spinnerAry = new String[7];
        spinnerAry[0] = "Select Group";
        spinnerAry[1] = "Group 1";
        spinnerAry[2] = "Group 2";
        spinnerAry[3] = "Group 3";
        spinnerAry[4] = "Group 4";
        spinnerAry[5] = "Group 5";
        spinnerAry[6] = "Group 6";

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerAry);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(spinnerArrayAdapter);

        //Setting on click listeners
        toSetup.setOnClickListener(listen);


    }

    private View.OnClickListener listen = new View.OnClickListener(){
        public void onClick(View v){
            switch(v.getId()) {
                case R.id.btnToSetup:
                    if(checkEverything()){
                        //Log.d("CREATION",scoutName.getText().toString().length() + "");
                        //Log.d("CREATION",scoutName.getText().toString());
                        break;
                    }
                    else{
                        //Log.d("CREATION",scoutName.getText().toString().length() + "");
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("scoutName", scoutName.getText().toString());
                        editor.putString("group",groupSpinner.getSelectedItem().toString());
                        Intent intent  = new Intent(context,MatchSetup.class);
                        startActivity(intent);
                    }
                    break;
            }
        }
    };
    //method to check if the user has put in a name and has selected a group
    private boolean checkEverything(){
        boolean fail = false;
        if(scoutName.getText().toString().length() ==0){
            toast = Toast.makeText(context, "Please enter your name to continue!", Toast.LENGTH_SHORT);
            toast.show();
            fail = true;
        }
        if(groupSpinner.getSelectedItemPosition() == 0){
            toast = Toast.makeText(context, "Please select a group on the spinner!", Toast.LENGTH_SHORT);
            toast.show();
            fail = true;
        }
        return fail;
    }
}
