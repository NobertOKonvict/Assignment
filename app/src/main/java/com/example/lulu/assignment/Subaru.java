package com.example.lulu.assignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class Subaru extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subaru_layout);

        handler = new Handler();

        TextView textView1 = (TextView) findViewById(R.id.imprezzaText);
        textView1.setText(
                Html.fromHtml(
                        "Subaru imprezza, starting price 20m for more details, click " +
                                "<a href=\"https:www.subaru.com\">here</a>"
                )
        );
        textView1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textView2 = (TextView) findViewById(R.id.outbacktext);
        textView2.setText(
                Html.fromHtml(
                        "Subaru outback, starting price 50m for more details, click " +
                                "<a href=\"https:www.subaru.com\">here</a>"
                )
        );
        textView2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textView3 = (TextView) findViewById(R.id.forestertext);
        textView3.setText(
                Html.fromHtml(
                        "Subaru forester, starting price 40m for more details, click " +
                                "<a href=\"https:www.subaru.com\">here</a>"
                )
        );
        textView3.setMovementMethod(LinkMovementMethod.getInstance());

        Button button = (Button) findViewById(R.id.internet_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread myThread = new MyThread();
                myThread.start();
            }
        });
    }

    private class MyThread extends Thread{
        @Override
        public void run() {
            String url = "https://www.subaru.com/";
            final Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            //startActivity(intent);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            });
        }
    }
}
