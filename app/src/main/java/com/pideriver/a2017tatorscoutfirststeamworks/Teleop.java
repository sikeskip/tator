package com.pideriver.a2017tatorscoutfirststeamworks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;

import org.w3c.dom.Comment;

public class Teleop extends AppCompatActivity {

    SharedPreferences preferences;
    Context context = this;

    Button toComments;

    CheckBox hopper1;
    CheckBox hopper2;
    CheckBox hopper3;
    CheckBox hopper4;
    CheckBox hopper5;
    CheckBox tooQuickToCount;
    CheckBox groundPickGears;

    Counter lowGoalDumps;
    Counter highGoals;
    Counter highGoalsCycles;
    Counter gearFails;
    Counter gearsPlaced1;
    Counter gearsPlaced2;
    Counter gearsPlaced3;
    Counter rzFouls;
    Counter rzClearedGear;
    Counter rzDropedGear;

    SeekBar accuracy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);

        preferences = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);

        //next activity button
        toComments = (Button)findViewById(R.id.btnToComments);
        toComments.setOnClickListener(listen);

        //counters and checkboxes
        hopper1 = (CheckBox) findViewById(R.id.ckBxDumpedHopper1);
        hopper2 = (CheckBox)findViewById(R.id.ckBxDumpedHopper2);
        hopper3 = (CheckBox)findViewById(R.id.ckBxDumpedHopper3);
        hopper4 = (CheckBox)findViewById(R.id.ckBxDumpedHopper4);
        hopper5 = (CheckBox)findViewById(R.id.ckBxDumpedHopper5);
        tooQuickToCount = (CheckBox)findViewById(R.id.ckBxTooQuickToCount);
        groundPickGears = (CheckBox)findViewById(R.id.ckGroundPickGears);

        lowGoalDumps = (Counter)findViewById(R.id.ctLowGoal);
        highGoals = (Counter)findViewById(R.id.ctHighGoal);
        gearFails = (Counter)findViewById(R.id.ctGearFail);
        gearsPlaced1 = (Counter)findViewById(R.id.ctGearPlace1);
        gearsPlaced2 = (Counter)findViewById(R.id.ctGearPlace2);
        gearsPlaced3 = (Counter)findViewById(R.id.ctGearPlace3);
        highGoalsCycles = (Counter)findViewById(R.id.ctHighGoalCyclesTele);
        rzClearedGear = (Counter)findViewById(R.id.ctRZClearedGear);
        rzDropedGear = (Counter)findViewById(R.id.ctRZDroppedGear);
        rzFouls = (Counter)findViewById(R.id.ctRZFouls);

        //seekbar
        accuracy = (SeekBar)findViewById(R.id.sliderAccuracy);
    }

    private View.OnClickListener listen = new View.OnClickListener(){
        public void onClick(View v){
            switch(v.getId()) {
                case R.id.btnToComments:
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("groundPickGears",groundPickGears.isChecked());
                    editor.putBoolean("DumpedHopper1",hopper1.isChecked());
                    editor.putBoolean("DumpedHopper2",hopper2.isChecked());
                    editor.putBoolean("DumpedHopper3",hopper3.isChecked());
                    editor.putBoolean("DumpedHopper4",hopper4.isChecked());
                    editor.putBoolean("DumpedHopper5",hopper5.isChecked());
                    editor.putBoolean("TooQuickToCount",tooQuickToCount.isChecked());
                    editor.putInt("LowGoalDumps",lowGoalDumps.getCount());
                    editor.putInt("HighGoalCount",highGoals.getCount());
                    editor.putInt("GearFails",gearFails.getCount());
                    editor.putInt("GearsPlaced1",gearsPlaced1.getCount());
                    editor.putInt("GearsPlaced2",gearsPlaced2.getCount());
                    editor.putInt("GearsPlaced3",gearsPlaced3.getCount());
                    editor.putInt("accuracyTele",accuracy.getProgress());
                    editor.putInt("high goal cycles tele",highGoalsCycles.getCount());
                    editor.putInt("rzClearedGear",rzClearedGear.getCount());
                    editor.putInt("rzDroppedGear",rzDropedGear.getCount());
                    editor.putInt("rzFouls",rzFouls.getCount());
                    editor.commit();
                    Intent intent  = new Intent(context,Comments.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
