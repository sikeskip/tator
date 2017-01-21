package com.pideriver.a2017tatorscoutfirststeamworks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.prefs.Preferences;

public class Auto extends AppCompatActivity {

    Context context = this;

    SharedPreferences preferences;

    ImageView field;

    Drawable fieldBlue;

    Button toTeleop;

    CheckBox gearPlacement1;
    CheckBox gearPlacement2;
    CheckBox gearPlacement3;

    CheckBox hopperPlace1;
    CheckBox hopperPlace2;
    CheckBox hopperPlace3;
    CheckBox hopperPlace4;

    CheckBox noGear;
    CheckBox gearFail;
    CheckBox crossedLine;

    Counter lowGoalCycles;
    Counter highGoalCycles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        field =(ImageView)findViewById(R.id.imgField);

        preferences = this.getSharedPreferences("preferences",Context.MODE_PRIVATE);

        fieldBlue.createFromPath("edit_blue_side");

        if (preferences.getString("fieldColor","")=="blue")
        {
            field.setImageDrawable(fieldBlue);
        }
        //next activity button
        toTeleop = (Button)findViewById(R.id.btnToTeleop);
        //setting checkboxes and counters
        gearPlacement1=(CheckBox)findViewById(R.id.ckBxGearPeg1);
        gearPlacement2=(CheckBox)findViewById(R.id.ckBxGearPeg2);
        gearPlacement3=(CheckBox)findViewById(R.id.ckBxGearPeg3);

        hopperPlace1=(CheckBox)findViewById(R.id.ckBxDumpedHopper1);
        hopperPlace2=(CheckBox)findViewById(R.id.ckBxDumpedHopper2);
        hopperPlace3=(CheckBox)findViewById(R.id.ckBxDumpedHopper3);
        hopperPlace4=(CheckBox)findViewById(R.id.ckBxDumpedHopper4);

        noGear=(CheckBox)findViewById(R.id.ckBxNoGear);
        gearFail=(CheckBox)findViewById(R.id.ckBxGearFail);
        crossedLine=(CheckBox)findViewById(R.id.ckBxCrossedLine);
        lowGoalCycles=(Counter)findViewById(R.id.ctLowGoalCycles);
        highGoalCycles=(Counter)findViewById(R.id.ctHighGoalCycles);
    }

    private View.OnClickListener listen = new View.OnClickListener(){
        public void onClick(View v){
            switch(v.getId()) {
                case R.id.btnToTeleop:

                    SharedPreferences.Editor editor = preferences.edit();

                    //gear placement
                    if(gearPlacement1.isChecked()) {
                        editor.putInt("gearPlacement", 1);
                    }
                    else if(gearPlacement2.isChecked()){
                        editor.putInt("gearPlacement", 2);
                    }
                    else{
                        editor.putInt("gearPlacement", 3);
                    }
                    //hoppers
                    editor.putBoolean("hopper1",hopperPlace1.isChecked());
                    editor.putBoolean("hopper2",hopperPlace2.isChecked());
                    editor.putBoolean("hopper3",hopperPlace3.isChecked());
                    editor.putBoolean("hopper4",hopperPlace4.isChecked());
                    //no gear, failed to place gear, or crossed line
                    editor.putBoolean("noGear",noGear.isChecked());
                    editor.putBoolean("gearFail",gearFail.isChecked());
                    editor.putBoolean("crossedLine",crossedLine.isChecked());
                    //cycles for goals
                    editor.putInt("lowGearCycles",lowGoalCycles.getCount());
                    editor.putInt("highGearCycles",highGoalCycles.getCount());

                    Intent intent  = new Intent(context,Teleop.class);
                    startActivity(intent);

                    break;
            }
        }
    };
}
