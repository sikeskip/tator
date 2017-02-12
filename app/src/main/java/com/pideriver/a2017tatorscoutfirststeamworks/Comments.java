package com.pideriver.a2017tatorscoutfirststeamworks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

                    editor.putString("comments", comments.getText().toString());
                    editor.putBoolean("defended", defended.isChecked());
                    editor.putInt("defense power", defendBar.getProgress());
                    editor.putBoolean("scaled", scaled.isChecked());
                    editor.putBoolean("scale fail", scaledFailed.isChecked());
                    editor.putBoolean("stuck on ball", stuckOnBall.isChecked());
                    editor.putBoolean("tipped over", tippedOver.isChecked());
                    editor.putBoolean("dead", dead.isChecked());
                    editor.putBoolean("intermittent", intermittent.isChecked());
                    editor.putInt("match", preferences.getInt("match", 0) + 1);
                    editor.commit();
                    SharedPreferences.Editor editorThree = preferences.edit();
                    String oldFileName = "";

                    if(preferences.getInt("match end",1) <=1) {
                        oldFileName = preferences.getString("filename", "ERROR");
                    }
                    editorThree.putString("filename", preferences.getString("group", "GROUP") + " Matches " + preferences.getInt("match start", 0) + " - " + preferences.getInt("match end", 0) + ".csv");
                    editorThree.commit();


                    File file = new File(Environment.getExternalStorageDirectory() + "/" + preferences.getString("filename", "BROKEN"));
                    if(preferences.getInt("match end",0) <=1) {
                        try {
                            File oldfile = new File(Environment.getExternalStorageDirectory() + "/" + oldFileName);
                            oldfile.renameTo(file);
                            file = new File(Environment.getExternalStorageDirectory() + "/" + preferences.getString("filename", "BROKEN"));
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("CREATION", "no old file");
                        }
                    }
                    try{
                        FileWriter writer;
                        writer = new FileWriter(file,true);
                        writer.append(preferences.getString("team","TEAM"));
                        writer.append(",");
                        writer.append(preferences.getString("match","MATCH"));
                        writer.append(",");
                        writer.append(preferences.getString("scoutName","SCOUT"));
                        writer.append(",");
                        writer.append(preferences.getString("group","GROUP"));
                        writer.append(",");
                        writer.append(preferences.getString("startPos","STARTING POSITION"));
                        writer.append(",");
                        writer.append(preferences.getString("allianceColor","COLOR"));
                        writer.append(",");
                        writer.append(preferences.getString("team","TEAM"));
                        writer.append(",");
                        writer.append("Auto");
                        writer.append(",");
                        writer.append(preferences.getInt("gearPlacement",0)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("hopper1",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("hopper2",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("hopper3",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("hopper4",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("noGear",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("gearFail",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("crossedLine",false)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("lowGearCycles",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("highGearCycles",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("accuracy",0)+"");
                        writer.append(",");
                        writer.append("TELEOP");
                        writer.append(",");
                        writer.append(preferences.getBoolean("DumpedHopper1",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("DumpedHopper2",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("DumpedHopper3",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("DumpedHopper4",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("DumpedHopper5",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("TooQuickToCount",false)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("LowGoalDumps",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("HighGoalCount",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("GearFails",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("GearsPlaced1",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("GearsPlaced2",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("GearsPlaced3",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("accuracyTeleop",0)+"");
                        writer.append(",");
                        writer.append("COMMENTS");
                        writer.append(",");
                        writer.append(preferences.getBoolean("defended",false)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("defense power",0)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("scaled",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("scale fail",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("stuck on ball",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("tipped over",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("dead",false)+"");
                        writer.append(",");
                        writer.append(preferences.getBoolean("intermittent",false)+"");
                        writer.append(",");
                        writer.append(preferences.getString("comments","COMMENTS"));
                        writer.append("\n");
                        writer.flush();
                        writer.close();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                        String stack = "";
                        for(StackTraceElement x:e.getStackTrace()){
                            stack = stack + x.toString() + "\n";
                        }
                        Log.d("CREATION", stack);
                        Toast.makeText(context, "File Writer Failed", Toast.LENGTH_SHORT).show();
                    }
                    SharedPreferences.Editor editorTwo = preferences.edit();
                    editorTwo.putInt("match end",1);
                    editorTwo.commit();

                    Intent intent  = new Intent(context,MatchSetup.class);
                    startActivity(intent);

                    break;

            }
        }
    };

}
