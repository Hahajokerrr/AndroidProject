package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
public class MainActivity extends Activity implements AdapterView. OnItemClickListener {
    TextView selection;
    String[] items = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7",
            "Max OS X"};
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        selection = (TextView) findViewById(R.id.selection);
        GridView gv = (GridView) findViewById(R.id.grid);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items );
        gv.setAdapter(aa);
        gv.setOnItemClickListener(this);    }
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        selection.setText(items[position]);    }
}// class




