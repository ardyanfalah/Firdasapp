package com.example.firdasapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static MainActivity inst;
    private TextView alarmTextView;

    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void OnToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            Log.d("MyActivity", "Alarm On");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        } else {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("MyActivity", "Alarm Off");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                try {
                    {
                        String cameraId = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId,false);
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.cancel();
    }

    public void turnOnFlash(){
////        Button btn = findViewById(R.id.btn_flash);
////        btn.setOnClickListener(new View.OnClickListener() {
////            @RequiresApi(api = Build.VERSION_CODES.M)
////            @Override
////            public void onClick(View view) {
//                CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
////
////                try {
//                    String cameraId = cameraManager.getCameraIdList()[0];
//                    cameraManager.setTorchMode(cameraId, true);
////                } catch (CameraAccessException e) {
////                }
////            }
////        });
    }

    public void turnOFfFlash(){
//        Button btnOff = findViewById(R.id.btn_flash_off);
//        btnOff.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//                try {
//                    {
//                        String cameraId = cameraManager.getCameraIdList()[0];
//                        cameraManager.setTorchMode(cameraId,false);
//                    }
//                } catch (CameraAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}
