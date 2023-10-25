package com.example.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class BluetoothActivity extends AppCompatActivity {
    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;


    private BluetoothAdapter bluetoothAdapter;
    private Button enableBluetoothButton, disableBluetoothButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        enableBluetoothButton = findViewById(R.id.enableBluetoothButton);
        disableBluetoothButton = findViewById(R.id.disableBluetoothButton);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        enableBluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableBluetooth();
            }
        });

        disableBluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableBluetooth();
            }
        });
    }

    private void enableBluetooth() {
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter.isEnabled()) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.BLUETOOTH_ADMIN}, REQUEST_BLUETOOTH_PERMISSION);
                } else {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, 1);
                }
            } else {
                showToast("Bluetooth đã được bật.");
            }
        } else {
            showToast("Lỗi");
        }
    }

    private void disableBluetooth() {
        if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled()) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.BLUETOOTH_ADMIN}, REQUEST_BLUETOOTH_PERMISSION);
                } else {
                    bluetoothAdapter.disable();
                    showToast("Bluetooth đã tắt.");
                }
            } else {
                showToast("Bluetooth đã tắt.");
            }
        } else {
            showToast("Lỗi");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, perform Bluetooth operation (enable or disable)
                if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
                    enableBluetooth(); // Retry enabling Bluetooth after permission is granted
                }
            } else {
                showToast("Không có quyền truy cập Bluetooth.");
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
