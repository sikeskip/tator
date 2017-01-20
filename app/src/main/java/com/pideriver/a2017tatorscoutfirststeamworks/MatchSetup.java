package com.pideriver.a2017tatorscoutfirststeamworks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MatchSetup extends AppCompatActivity {

    private RadioButton radStartPos1, RadStartPos2, RadStartPos3, RadStartPos4;

    private Button btnToAuto;

    private Spinner spnTeamSpinner;

    private ImageView imgFieldAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_setup);

        radStartPos1 = (RadioButton)findViewById(R.id.radStartPos1);
        RadStartPos2 = (RadioButton)findViewById(R.id.radStartPos2);
        RadStartPos3 = (RadioButton)findViewById(R.id.radStartPos3);
        RadStartPos4 = (RadioButton)findViewById(R.id.radStartPos4);
        btnToAuto = (Button)findViewById(R.id.btnToAuto);
        spnTeamSpinner = (Spinner)findViewById(R.id.spnTeamSpinner);
        imgFieldAuto = (ImageView)findViewById(R.id.)

    }
}
