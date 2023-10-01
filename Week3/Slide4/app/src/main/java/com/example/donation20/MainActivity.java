package com.example.donation20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.linear_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.donation);
        SearchView searchView = (SearchView) menuItem.getActionView();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.donation) {
            Intent intent = new Intent(MainActivity.this, DonationActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Donation view", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.food) {
            Intent intent = new Intent(MainActivity.this, FoodActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Food view", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}