package com.example.gamebooster;

import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {

    TextView device, ram;
    Button boost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        device = findViewById(R.id.device);
        ram = findViewById(R.id.ram);
        boost = findViewById(R.id.boost);

        device.setText("Thiết bị: " + Build.MODEL);

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        am.getMemoryInfo(mi);

        long total = mi.totalMem / (1024 * 1024);
        long free = mi.availMem / (1024 * 1024);

        ram.setText("RAM: " + free + "MB / " + total + "MB");

        boost.setOnClickListener(v -> {
            List<ActivityManager.RunningAppProcessInfo> processes = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo process : processes) {
                am.killBackgroundProcesses(process.processName);
            }
            Toast.makeText(this, "Đã boost!", Toast.LENGTH_SHORT).show();
        });
    }
}
