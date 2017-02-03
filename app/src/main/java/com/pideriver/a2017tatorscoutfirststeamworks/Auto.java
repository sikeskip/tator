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
import android.widget.SeekBar;

import java.util.prefs.Preferences;

public class Auto extends AppCompatActivity {

    Context context = this;

    SharedPreferences preferences;

    ImageView field;

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

    SeekBar accuracy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        field =(ImageView)findViewById(R.id.imgField);

        preferences = this.getSharedPreferences("preferences",Context.MODE_PRIVATE);

        if (preferences.getString("allianceColor","").equals("blue"))
        {
            field.setImageResource(R.drawable.edit_blue_side);
            gearPlacement3=(CheckBox)findViewById(R.id.ckBxGearPeg1);
            gearPlacement2=(CheckBox)findViewById(R.id.ckBxGearPeg2);
            gearPlacement1=(CheckBox)findViewById(R.id.ckBxGearPeg3);
            hopperPlace3=(CheckBox)findViewById(R.id.ckBxDumpedHopper1);
            hopperPlace4=(CheckBox)findViewById(R.id.ckBxDumpedHopper2);
            hopperPlace1=(CheckBox)findViewById(R.id.ckBxDumpedHopper3);
            hopperPlace2=(CheckBox)findViewById(R.id.ckBxDumpedHopper4);
        }
        else{
            gearPlacement1=(CheckBox)findViewById(R.id.ckBxGearPeg1);
            gearPlacement2=(CheckBox)findViewById(R.id.ckBxGearPeg2);
            gearPlacement3=(CheckBox)findViewById(R.id.ckBxGearPeg3);
            hopperPlace1=(CheckBox)findViewById(R.id.ckBxDumpedHopper1);
            hopperPlace2=(CheckBox)findViewById(R.id.ckBxDumpedHopper2);
            hopperPlace3=(CheckBox)findViewById(R.id.ckBxDumpedHopper3);
            hopperPlace4=(CheckBox)findViewById(R.id.ckBxDumpedHopper4);
        }
        //next activity button
        toTeleop = (Button)findViewById(R.id.btnToTeleop);
        //setting checkboxes, counters and seekbar

        noGear=(CheckBox)findViewById(R.id.ckBxNoGear);
        gearFail=(CheckBox)findViewById(R.id.ckBxGearFail);
        crossedLine=(CheckBox)findViewById(R.id.ckBxCrossedLine);
        lowGoalCycles=(Counter)findViewById(R.id.ctLowGoalCycles);
        highGoalCycles=(Counter)findViewById(R.id.ctHighGoalCycles);

        accuracy = (SeekBar) findViewById(R.id.sliderAccuracy);

        //setting onClickListeners
        toTeleop.setOnClickListener(listen);
        gearPlacement1.setOnClickListener(listen);
        gearPlacement2.setOnClickListener(listen);
        gearPlacement3.setOnClickListener(listen);
        noGear.setOnClickListener(listen);
        gearFail.setOnClickListener(listen);

    }

    private View.OnClickListener listen = new View.OnClickListener(){
        public void onClick(View v){
            switch(v.getId()) {
                case R.id.btnToTeleop:

                    SharedPreferences.Editor editor = preferences.edit();
                    CheckBox[] ary;

                    //gear placement
                    if(gearPlacement1.isChecked()) {
                        editor.putInt("gearPlacement", 1);
                    }
                    else if(gearPlacement2.isChecked()){
                        editor.putInt("gearPlacement", 2);
                    }
                    else if(gearPlacement3.isChecked()){
                        editor.putInt("gearPlacement", 3);
                    }
                    //hoppers
                    editor.putBoolean("hopper1",hopperPlace1.isChecked());
                    editor.putBoolean("hopper2",hopperPlace2.isChecked());
                    editor.putBoolean("hopper3",hopperPlace3.isChecked());
                    editor.putBoolean("hopper4",hopperPlace4.isChecked());
                    //no gear, failed to place gear, and crossed line
                    editor.putBoolean("noGear",noGear.isChecked());
                    editor.putBoolean("gearFail",gearFail.isChecked());
                    editor.putBoolean("crossedLine",crossedLine.isChecked());
                    //cycles for goals
                    editor.putInt("lowGearCycles",lowGoalCycles.getCount());
                    editor.putInt("highGearCycles",highGoalCycles.getCount());
                    //accuracy slider
                    editor.putInt("accuracy",accuracy.getProgress());

                    editor.commit();
                    Intent intent  = new Intent(context,Teleop.class);
                    startActivity(intent);

                    break;
                case R.id.ckBxNoGear:
                    ary =new CheckBox[]{gearFail,gearPlacement1,gearPlacement2,gearPlacement3};
                    UncheckBoxes(ary);
                    break;
                case R.id.ckBxGearFail:
                    ary =new CheckBox[]{noGear};
                    UncheckBoxes(ary);
                    break;
                case R.id.ckBxGearPeg1:
                    if(preferences.getString("allianceColor","").equals("red")){
                    ary =new CheckBox[]{noGear,gearPlacement2,gearPlacement3};}
                    else{
                        ary =new CheckBox[]{noGear,gearPlacement2,gearPlacement1};}
                    UncheckBoxes(ary);
                    break;
                case R.id.ckBxGearPeg2:
                    ary =new CheckBox[]{noGear,gearPlacement1,gearPlacement3};
                    UncheckBoxes(ary);
                    break;
                case R.id.ckBxGearPeg3:
                    if(preferences.getString("allianceColor","").equals("red")){
                    ary =new CheckBox[]{noGear,gearPlacement1,gearPlacement2};}
                    else{
                        ary =new CheckBox[]{noGear,gearPlacement3,gearPlacement2};
                    }
                    UncheckBoxes(ary);
                    break;
            }
        }
    };

    private void UncheckBoxes(CheckBox[] ary)
    {
        for(CheckBox x:ary){
            x.setChecked(false);
        }
    }
}
