package com.example.lulu.assignment;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DisplayUsers extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_users);

        mListView = (ListView) findViewById(R.id.displayUsers);
        loadData();
    }

    private void loadData(){
        try {

            List<String> list = new ArrayList<>();
            Cursor c = managedQuery(Providers.CONTENT_URI, null, null, null, "name");

            if (c.moveToFirst()) {
                do {
                    // Log.e("NAME:", "name"+c.getString(c.getColumnIndex(Providers.NAME))+" \t"+c.getString(c.getColumnIndex(Providers.ASSIGNMENTS)));
                    list.add(c.getString(c.getColumnIndex(Providers.NAME ))+" " +
                            ": "+c.getString(c.getColumnIndex(Providers.ASSIGNMENTS))
                            +
                            ": "+c.getString(c.getColumnIndex(Providers.MIDSEM))
                            +
                            ": "+c.getString(c.getColumnIndex(Providers.EXAM))
                    );
                }
                while (c.moveToNext());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            mListView.setAdapter(adapter);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

