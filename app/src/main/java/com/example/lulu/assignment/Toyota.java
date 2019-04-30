package com.example.lulu.assignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Toyota extends AppCompatActivity {

    Handler handler;

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toyota_layout);

        handler = new Handler();



        TextView textView1 = (TextView) findViewById(R.id.rav4Text);
        textView1.setText(
                Html.fromHtml(
                        "Toyota Rav4, starting price 20m for more details, click " +
                                "<a href=\"https:www.toyota.com\">here</a>"
                )
        );
        textView1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textView2 = (TextView) findViewById(R.id.landcruisertext);
        textView2.setText(
                Html.fromHtml(
                        "Toyota Land Cruiser, starting price 50m for more details, click " +
                                "<a href=\"https:www.toyota.com\">here</a>"
                )
        );
        textView2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textView3 = (TextView) findViewById(R.id.pradotext);
        textView3.setText(
                Html.fromHtml(
                        "Toyota Prado, starting price 40m for more details, click " +
                                "<a href=\"https:www.toyota.com\">here</a>"
                )
        );
        textView3.setMovementMethod(LinkMovementMethod.getInstance());

        mButton = (Button) findViewById(R.id.toyota_internet_connection);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread myThread = new MyThread();
                myThread.start();
            }
        });


    }
    class MyThread extends Thread{
        @Override
        public void run() {
            String url = "https://www.toyota.com/";
            final Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));

            handler.post(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            });
        }
    }

}
