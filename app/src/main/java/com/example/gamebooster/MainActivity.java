package com.example.gamebooster;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Build;
import android.widget.*;
import java.util.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView device, ram;
    Button boost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        device = findViewById(R.id.device);
        ram = findViewById(R.id.ram);
        boost = findViewById(R.id.boost);

        // Tên máy
        device.setText("Thiết bị: " + Build.MODEL);

        try {
            // RAM
            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            am.getMemoryInfo(mi);

            long total = mi.totalMem / (1024 * 1024);
            long free = mi.availMem / (1024 * 1024);

            ram.setText("RAM: " + free + "MB / " + total + "MB");

            // Boost
            boost.setOnClickListener(v -> {
                try {
                    List<ActivityManager.RunningAppProcessInfo> processes = am.getRunningAppProcesses();
                    for (ActivityManager.RunningAppProcessInfo process : processes) {
                        am.killBackgroundProcesses(process.processName);
                    }
                    Toast.makeText(this, "Đã boost!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, "Lỗi boost", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
