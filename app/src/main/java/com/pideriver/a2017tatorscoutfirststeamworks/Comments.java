package com.pideriver.a2017tatorscoutfirststeamworks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;

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
                    //for file writer: team number, scout name,...


                    Intent intent  = new Intent(context,MatchSetup.class);
                    startActivity(intent);

                    break;

            }
        }
    };

}
