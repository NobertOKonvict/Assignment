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

public class Mercedes extends AppCompatActivity {

    Handler handler1;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mercedes_layout);

        handler1 = new Handler();

        TextView textView1 = (TextView) findViewById(R.id.classbtext);
        textView1.setText(
                Html.fromHtml(
                        "Mercedec B-Class, starting price 20m for more details, click " +
                                "<a href=\"https:www.mercedes-benz.com\">here</a>"
                )
        );
        textView1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textView2 = (TextView) findViewById(R.id.classcText);
        textView2.setText(
                Html.fromHtml(
                        "Mercedec C-Class, starting price 50m for more details, click " +
                                "<a href=\"https:www.mercedes-benz.com\">here</a>"
                )
        );
        textView2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textView3 = (TextView) findViewById(R.id.classetext);
        textView3.setText(
                Html.fromHtml(
                        "Mercedec E-Class, starting price 40m for more details, click " +
                                "<a href=\"https:www.mercedes-benz.com\">here</a>"
                )
        );
        textView3.setMovementMethod(LinkMovementMethod.getInstance());

        mButton = (Button) findViewById(R.id.mercedes_connection);
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
            String url = "https://www.mercedes-benz.com/en/";
            final Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));

            handler1.post(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            });
        }
    }
}
