package com.example.spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
    TextView selection;
    String[] items = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        selection = (TextView) findViewById(R.id.selection);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        // set a clickable right push-button comboBox to show items
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
        this, android.R.layout.simple_spinner_item, items);
        // provide a particular design for the drop-down lines
        aa.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // associate GUI spinner and adapter
        spin.setAdapter(aa);    }
    public void onItemSelected(
            AdapterView<?> parent, View v, int position, long id) {
        selection.setText(items[position]);    }
    public void onNothingSelected(AdapterView<?> parent) {
        selection.setText("");    }
}
