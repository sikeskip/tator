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
    CheckBox blockedByBall;
    CheckBox tippedOver;
    CheckBox dead;
    CheckBox intermittent;
    CheckBox goodPick;
    CheckBox gearStuckInRobot;
    CheckBox avoidedChokePoint;

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
        blockedByBall=(CheckBox) findViewById(R.id.ckBxBlockedByBall);
        tippedOver=(CheckBox) findViewById(R.id.ckBxTippedOver);
        dead=(CheckBox) findViewById(R.id.ckBxDead);
        intermittent=(CheckBox) findViewById(R.id.ckBxIntermittent);
        goodPick=(CheckBox) findViewById(R.id.ckBxSecondPick);
        gearStuckInRobot=(CheckBox)findViewById(R.id.ckBxStuckGear);
        avoidedChokePoint=(CheckBox)findViewById(R.id.ckBxChokePoint);

        scaled.setOnClickListener(listen);
        scaledFailed.setOnClickListener(listen);
    }

    private View.OnClickListener listen = new View.OnClickListener(){
        public void onClick(View v){
            switch(v.getId()) {
                case R.id.ckBxScaled:
                    scaledFailed.setChecked(false);
                    break;
                case R.id.ckBxScaleFail:
                    scaled.setChecked(false);
                    break;
                case R.id.btnToNextMatch:

                    if(preferences.getBoolean("didAppend",false)){
                        System.out.println("had appended");
                        Intent intent  = new Intent(context,MatchSetup.class);
                        startActivity(intent);
                        break;
                    }
                    System.out.println("appending");

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("comments", comments.getText().toString().replaceAll(",", "").replaceAll("\n"," "));
                    editor.putBoolean("defended", defended.isChecked());
                    editor.putBoolean("good pick", defended.isChecked());
                    editor.putInt("defense power", defendBar.getProgress());
                    editor.putBoolean("scaled", scaled.isChecked());
                    editor.putBoolean("scale fail", scaledFailed.isChecked());
                    editor.putBoolean("blocked by ball", blockedByBall.isChecked());
                    editor.putBoolean("tipped over", tippedOver.isChecked());
                    editor.putBoolean("dead", dead.isChecked());
                    editor.putBoolean("intermittent", intermittent.isChecked());
                    editor.putBoolean("gearStuckInRobot", gearStuckInRobot.isChecked());
                    editor.putBoolean("avoidedChokePoint", avoidedChokePoint.isChecked());

                    editor.commit();
                    SharedPreferences.Editor editorThree = preferences.edit();
                    String oldFileName = "";

                    if(preferences.getInt("match end",1) >1 && !preferences.getBoolean("nuke old file",false)) {
                        oldFileName = preferences.getString("filename", "ERROR");
                    }
                    editorThree.putString("filename", preferences.getString("group", "GROUP") + " Matches " + preferences.getInt("match start", 0) + " - " + preferences.getInt("match end", 0) + ".csv");
                    editorThree.commit();


                    File file = new File(Environment.getExternalStorageDirectory() + "/" + preferences.getString("filename", "BROKEN"));

                    if(preferences.getInt("match end",0) >1 && !preferences.getBoolean("nuke old file",false)) {
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
                        writer.append(preferences.getInt("match",0)+"");
                        writer.append(",");
                        writer.append(preferences.getString("scoutName","SCOUT"));
                        writer.append(",");
                        writer.append(preferences.getString("group","GROUP"));
                        writer.append(",");
                        writer.append(preferences.getString("startPos","STARTING POSITION"));
                        writer.append(",");
                        writer.append(preferences.getString("allianceColor","COLOR"));
                        writer.append(",");
                        writer.append(preferences.getInt("gearPlacement",0)+"");
                        writer.append(",");
                        if(preferences.getBoolean("tooFastAuto", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("hopper1", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("hopper2", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("hopper3", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("hopper4", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("noGear", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("gearFail", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("crossedLine", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        writer.append(preferences.getInt("lowGoalCycles",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("highGoalCycles",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("accuracy",0)+"");
                        writer.append(",");
                        if(preferences.getBoolean("DumpedHopper1", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("DumpedHopper2", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("DumpedHopper3", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("DumpedHopper4", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("DumpedHopper5", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("TooQuickToCount", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        writer.append(preferences.getInt("LowGoalDumps",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("HighGoalCount",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("high goal cycles tele",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("GearFails",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("GearsPlaced1",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("GearsPlaced2",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("GearsPlaced3",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("accuracyTele",0)+"");
                        writer.append(",");
                        if(preferences.getBoolean("defended", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        writer.append(preferences.getInt("defense power",0)+"");
                        writer.append(",");
                        if(preferences.getBoolean("scaled", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("scale fail", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("blocked by ball", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("tipped over", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("dead", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("intermittent", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("good pick", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getString("comments","COMMENTS").equals("") || preferences.getString("comments","COMMENTS").equals("COMMENTS")){
                            writer.append("NO COMMENT");
                        }
                        else {
                            writer.append(preferences.getString("comments", "COMMENTS"));
                        }
                        writer.append(",");
                        writer.append(preferences.getInt("rzClearedGear",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("rzDroppedGear",0)+"");
                        writer.append(",");
                        writer.append(preferences.getInt("rzFouls",0)+"");
                        writer.append(",");
                        if(preferences.getBoolean("gearStuckInRobot", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("avoidedChokePoint", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
                        writer.append(",");
                        if(preferences.getBoolean("groundPickGears", false)){
                            writer.append("1");
                        }
                        else{
                            writer.append("0");
                        }
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
                    editorTwo.putBoolean("nuke old file",false);
                    editorTwo.putInt("match end",preferences.getInt("match end",1)+1);
                    editorTwo.putInt("match", preferences.getInt("match", 0) + 1);
                    editorTwo.putBoolean("didAppend",true);
                    editorTwo.commit();

                    Intent intent  = new Intent(context,MatchSetup.class);
                    startActivity(intent);

                    break;

            }
        }
    };

}
