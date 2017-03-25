package com.pideriver.a2017tatorscoutfirststeamworks;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchSetup extends AppCompatActivity {
    //Declaring Views
    private RadioButton radStartPos1, radStartPos2, radStartPos3, radStartPos4, radRed, radBlue;

    private RadioGroup rgpRedOrBlue;

    private Button btnToAuto;

    private Button btnDataSent;

    private Spinner spnTeamSpinner;

    private ImageView imgFieldSetup;

    private SharedPreferences preferences;

    private String[] spinnerAry;

    private Context context;

    private Intent itToAuto;

    private LocationManager start, redOrBlue;

    private EditText matchNum;

    private EditText scoutName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Hello OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_setup);
        System.out.println("Hello OnCreate");
        verifyStoragePermissions(this);
        preferences = this.getSharedPreferences("preferences", MODE_PRIVATE);
        context = this;
        //Initializing Views
        radStartPos1 = (RadioButton) findViewById(R.id.radStartPos1);
        radStartPos2 = (RadioButton) findViewById(R.id.radStartPos2);
        radStartPos3 = (RadioButton) findViewById(R.id.radStartPos3);
        radStartPos4 = (RadioButton) findViewById(R.id.radStartPos4);
        radRed = (RadioButton) findViewById(R.id.radRed);
        radBlue = (RadioButton) findViewById(R.id.radBlue);
        btnToAuto = (Button) findViewById(R.id.btnToAuto);
        btnDataSent = (Button) findViewById(R.id.btnDataSent);
        spnTeamSpinner = (Spinner) findViewById(R.id.spnTeamSpinner);
        imgFieldSetup = (ImageView) findViewById(R.id.imgFieldSetup);
        matchNum = (EditText)findViewById(R.id.matchNum);
        scoutName = (EditText)findViewById(R.id.ETscoutName);
        //Setting Up Spinner
        spinnerAry = new String[7];
        //DummyData

        preferences = this.getSharedPreferences("preferences", MODE_PRIVATE);
        context = this;
        matchNum.setText(preferences.getInt("match", 1)+"");
        scoutName.setText(preferences.getString("scoutName",""));
        //Initializing Views
        radStartPos1 = (RadioButton) findViewById(R.id.radStartPos1);
        radStartPos2 = (RadioButton) findViewById(R.id.radStartPos2);
        radStartPos3 = (RadioButton) findViewById(R.id.radStartPos3);
        radStartPos4 = (RadioButton) findViewById(R.id.radStartPos4);
        radRed = (RadioButton) findViewById(R.id.radRed);
        radBlue = (RadioButton) findViewById(R.id.radBlue);
        btnToAuto = (Button) findViewById(R.id.btnToAuto);
        spnTeamSpinner = (Spinner) findViewById(R.id.spnTeamSpinner);
        rgpRedOrBlue = (RadioGroup) findViewById(R.id.rgpRedOrBlue);
        imgFieldSetup = (ImageView) findViewById(R.id.imgFieldSetup);

        //Setting Up Spinner
        spinnerAry = new String[7];
        //DummyData
        spinnerAry[0] = "Pick A Team";
        spinnerAry[1] = "254";
        spinnerAry[2] = "976";
        spinnerAry[3] = "987";
        spinnerAry[4] = "1114";
        spinnerAry[5] = "2056";
        spinnerAry[6] = "2122";
        //ActualData
        System.out.println("Hello FakeData");
        readFile();

        ArrayAdapter<String> teamSpnAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerAry);
        teamSpnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTeamSpinner.setAdapter(teamSpnAdapter);

        //Setting OnClickListeners
        btnToAuto.setOnClickListener(listener);
        btnDataSent.setOnClickListener(listener);
        radRed.setOnClickListener(listener);
        radBlue.setOnClickListener(listener);
        rgpRedOrBlue.setOnCheckedChangeListener(checkListener);

        setupRadioButtons(context);
    }

    private void setupRadioButtons(Context context) {
        start = new LocationManager(context);
        start.add(radStartPos1, "Inside Key");
        start.add(radStartPos2, "Next to Key");
        start.add(radStartPos3, "Middle of Airship");
        start.add(radStartPos4, "Next to Loading Zone");


        //LocationManager redOrBlue
        redOrBlue = new LocationManager(context);
        redOrBlue.add(radRed, "Red");
        redOrBlue.add(radBlue, "Blue");
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("Hello ReachedOCL");
            switch (v.getId()) {

                case R.id.btnToAuto:
                    if (checkEverything()) {
                        saveData();
                        itToAuto = new Intent(context, Auto.class);
                        startActivity(itToAuto);
                    }
                    break;
                case R.id.btnDataSent:
                    new AlertDialog.Builder(context)
                            .setTitle("Data Sent")
                            .setMessage("Are you sure Stewart has received the data?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putInt("match start",preferences.getInt("match end",0));
                                    editor.putInt("match end",preferences.getInt("match end",0)+1);
                                    editor.putBoolean("nuke old file",true);
                                    editor.commit();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    break;
            }
        }
    };

    private boolean checkEverything() {
        if (start.getCheckedButtonName().equals("none")) {
            Toast t = Toast.makeText(context, "Please select a starting position", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (redOrBlue.getCheckedButtonName().equals("none")) {
            Toast t = Toast.makeText(context, "Please select red or blue", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (spnTeamSpinner.getSelectedItem().toString().equals("Pick A Team")) {
            Toast t = Toast.makeText(context, "Please select a Team", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        return true;
    }

    private void saveData() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("startPos", start.getCheckedButtonName());
        editor.putString("allianceColor", redOrBlue.getCheckedButtonName());
        editor.putInt("match", Integer.parseInt(matchNum.getText().toString()));
        editor.putInt("match end", Integer.parseInt(matchNum.getText().toString()));
        editor.putString("scoutName", scoutName.getText().toString());
        editor.putString("team", spnTeamSpinner.getSelectedItem().toString().replaceAll(",.*", ""));
        editor.putBoolean("didAppend",false);
        editor.commit();
    }

    private void readFile() {
        try {
            System.out.println("hello ReachedReadFile");
            FileReader fr = new FileReader(Environment.getExternalStorageDirectory() + "/team_names.csv");
            System.out.println("hello newFileReader");
            Scanner scanner = new Scanner(fr);
            System.out.println("hello newScanner");
            scanner.useDelimiter("\n");
            System.out.println("hello useDelimeter");
            ArrayList<String> teams = new ArrayList<>();
            System.out.println("hello newArraylist");
            teams.add("Pick A Team");
            System.out.println("hello Pickateam");
            while (scanner.hasNext()){
                String team = scanner.next().trim();
                System.out.println("hello Scanner.next");
                teams.add(team);
                System.out.println("hello teams.add");
            }
            scanner.close();
            System.out.println("hello ScannerClose");

            spinnerAry = teams.toArray(new String[teams.size()]);
            System.out.println("hello ToArray");
        }
        catch (FileNotFoundException e){
            Toast t = Toast.makeText(context,
                    "File not found!", Toast.LENGTH_LONG);
            t.show();
        }
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        //Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        System.out.println("Hello ReachedVSP");
        if (permission != PackageManager.PERMISSION_GRANTED){
            System.out.println("Hello No Permission");
            //We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


    RadioGroup.OnCheckedChangeListener checkListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.radRed:
                    imgFieldSetup.setImageResource(R.drawable.edit_red_side_flip);
                    break;
                case R.id.radBlue:
                    imgFieldSetup.setImageResource(R.drawable.blue_flipped);
                    break;
            }

        }
    };
}
