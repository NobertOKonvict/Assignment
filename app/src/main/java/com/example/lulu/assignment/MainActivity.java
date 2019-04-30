package com.example.lulu.assignment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button toyota;
    private Button subaru;
    private Button mercedes;
    private Button upload;
    private Button playMusic;
    private Button addUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create and intent filter object that matches battery change action
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //Register broadcast receiver to receive a sticky intent
        Intent intent = this.registerReceiver(null, filter);
        //get battery status from the intent
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

        if(status == BatteryManager.BATTERY_STATUS_CHARGING){
            Toast.makeText(this, "Battery is charging", Toast.LENGTH_LONG).show();
        }else if(status == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
            Toast.makeText(this, "Battery is not charging", Toast.LENGTH_LONG).show();
        }else if(status == BatteryManager.BATTERY_STATUS_FULL){
            Toast.makeText(this, "Battery is full", Toast.LENGTH_LONG).show();
        }

        //Initialize what actually gets the extra values from the intent
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("user");

        //This is the toast that displays the extra values carried by the explicit intent
        Toast.makeText(getApplicationContext(), "Signed in as: " + user, Toast.LENGTH_LONG).show();

        toyota = (Button) findViewById(R.id.toyotaBtn);
        toyota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Toyota Cars", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Toyota.class);
                startActivity(intent);
            }
        });
        subaru = (Button) findViewById(R.id.subaruBtn);
        subaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Subaru Cars", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Subaru.class);
                startActivity(intent);
            }
        });
        mercedes = (Button) findViewById(R.id.mercedesBtn);
        mercedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Mercedes Cars", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Mercedes.class);
                startActivity(intent);
                
            }
        });
        upload = (Button) findViewById(R.id.uploadBtn);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Mercedes Cars", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Upload.class);
                startActivity(intent);

            }
        });
        playMusic = (Button) findViewById(R.id.playmusicBtn);
        playMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), PlaySongs.class));
            }
        });

        addUser = (Button) findViewById(R.id.addUser);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AddUser.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }
}
