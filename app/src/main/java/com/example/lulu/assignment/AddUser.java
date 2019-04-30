package com.example.lulu.assignment;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }

    @Override
    public void onClick(View v) {

    }

    public void onClickAddName (View view){
        // adding a student record
        ContentValues values = new ContentValues();
        values.put(Providers.NAME, ((EditText) findViewById(R.id.editText2)).getText().toString());
        values.put(Providers.ASSIGNMENTS, ((EditText) findViewById(R.id.editText)).getText().toString());
        values.put(Providers.MIDSEM, ((EditText) findViewById(R.id.editText3)).getText().toString());
        values.put(Providers.EXAM, ((EditText) findViewById(R.id.editText4)).getText().toString());
        Uri uri = getContentResolver().insert(Providers.CONTENT_URI, values);

        if (uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void onClickRetrieveStudents (View view){
        //retrieving students records
        Intent intent = new Intent(AddUser.this, DisplayUsers.class);
        startActivity(intent);

    }
}
