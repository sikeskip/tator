package com.pideriver.a2017tatorscoutfirststeamworks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class Comments extends AppCompatActivity {

    SharedPreferences preferences;
    Context context = this;

    Button toNextMatch;
    AutoCompleteTextView comments;

    CheckBox defended;
    SeekBar defendBar;

    CheckBox scaled;
    CheckBox scaledFailed;
    CheckBox stuckOnBall;
    CheckBox tippedOver;
    CheckBox dead;
    CheckBox intermittent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        preferences = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);

        toNextMatch = (Button)findViewById(R.id.btnToNextMatch);
        toNextMatch.setOnClickListener(listen);

        comments = (AutoCompleteTextView)findViewById(R.id.acTvComments);
        defended=(CheckBox) findViewById(R.id.ckBxDefended);
        defendBar=(SeekBar) findViewById(R.id.skBrDefendedSlider);
        scaled=(CheckBox) findViewById(R.id.ckBxScaled);
        scaledFailed=(CheckBox) findViewById(R.id.ckBxScaleFail);
        stuckOnBall=(CheckBox) findViewById(R.id.ckBxStuckOnBall);
        tippedOver=(CheckBox) findViewById(R.id.ckBxTippedOver);
        dead=(CheckBox) findViewById(R.id.ckBxDead);
        intermittent=(CheckBox) findViewById(R.id.ckBxIntermittent);
    }

    private View.OnClickListener listen = new View.OnClickListener(){
        public void onClick(View v){
            switch(v.getId()) {
                case R.id.btnToNextMatch:

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("comments",comments.getText().toString());
                    editor.putBoolean("defended",defended.isChecked());
                    editor.putInt("defense power",defendBar.getProgress());
                    editor.putBoolean("scaled",scaled.isChecked());
                    editor.putBoolean("scale fail",scaledFailed.isChecked());
                    editor.putBoolean("stuck on ball",stuckOnBall.isChecked());
                    editor.putBoolean("tipped over",tippedOver.isChecked());
                    editor.putBoolean("dead",dead.isChecked());
                    editor.putBoolean("intermittent",intermittent.isChecked());

                    editor.commit();
                    //for file writer:name =Group_Match #-#.csv;file order = team number, match number, MATCH SETUP, AUTO, TELEOP, COMMENTS;
                    File file = new File(Environment.getExternalStorageDirectory()+"/"+preferences.getString("filename","BROKEN"));
                    try{
                        FileWriter writer = new FileWriter(file);
                        writer.write(preferences.getString("scoutname","SCOUT")+"MATCH SETUP");
                        writer.write(",");
                        writer.write(preferences.getString("startPos","STARTING POSITION"));
                        writer.write(",");
                        writer.write(preferences.getString("allianceColor","COLOR"));
                        writer.write(",");
                        writer.write(preferences.getString("team","TEAM"));
                        writer.write(",");
                        writer.write("Auto");
                        writer.write(",");
                        writer.write(preferences.getInt("gearPlacement",0));
                        writer.write(",");
                        writer.write(preferences.getBoolean("hopper1",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("hopper2",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("hopper3",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("hopper4",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("noGear",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("gearFail",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("crossedLine",false)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("lowGearCycles",0)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("highGearCycles",0)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("accuracy",0)+"");
                        writer.write(",");
                        writer.write("TELEOP");
                        writer.write(",");
                        writer.write(preferences.getBoolean("DumpedHopper1",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("DumpedHopper2",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("DumpedHopper3",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("DumpedHopper4",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("DumpedHopper5",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("TooQuickToCount",false)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("LowGoalDumps",0)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("HighGoalCount",0)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("GearFails",0)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("GearsPlaced1",0)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("GearsPlaced2",0)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("GearsPlaced3",0)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("accuracyTeleop",0)+"");
                        writer.write(",");
                        writer.write("COMMENTS");
                        writer.write(",");
                        writer.write(preferences.getBoolean("defended",false)+"");
                        writer.write(",");
                        writer.write(preferences.getInt("defense power",0)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("scaled",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("scale fail",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("stuck on ball",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("tipped over",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("dead",false)+"");
                        writer.write(",");
                        writer.write(preferences.getBoolean("intermittent",false)+"");
                        writer.write(",");
                        writer.write(preferences.getString("comments","COMMENTS"));
                        writer.write(System.lineSeparator());
                        writer.close();
                    }
                    catch(Exception e){
                        Toast.makeText(context, "File Writer Failed", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent  = new Intent(context,MatchSetup.class);
                    startActivity(intent);

                    break;

            }
        }
    };

}
