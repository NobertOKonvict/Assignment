package com.example.lulu.assignment;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lulu.assignment.R;

public class Upload extends AppCompatActivity {

        private static final int CAMERA=123;
        Button button;
        ImageView imageView;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.upload);

            imageView=findViewById(R.id.image1);
            button=findViewById(R.id.image2);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,CAMERA);
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==CAMERA&&resultCode== Activity.RESULT_OK){
                Bitmap photo= (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
            }
        }
    }


