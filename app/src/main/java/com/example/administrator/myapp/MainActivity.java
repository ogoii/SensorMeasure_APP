package com.example.administrator.myapp;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private TextView gX,gY,gZ,aX,aY,aZ,gyroX,gyroY,gyroZ;
    private TextView mX,mY,mZ;
    private TextView pressure,light;
    private SensorManager sm;                           //宣告SensorManager 和  Sensor
    private Sensor g,a,gyro,m,p,l;
    private float gravity[]=new float[3];
    private float accelerometer[]=new float[3];
    private float gyroscope[]=new float[3];
    private float megnetometer[]=new float[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {            //執行
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gX=(TextView)findViewById(R.id.textView2);
        gY=(TextView)findViewById(R.id.textView3);
        gZ=(TextView)findViewById(R.id.textView4);
        aX=(TextView)findViewById(R.id.textView6);
        aY=(TextView)findViewById(R.id.textView7);
        aZ=(TextView)findViewById(R.id.textView8);
        gyroX=(TextView)findViewById(R.id.textView10);
        gyroY=(TextView)findViewById(R.id.textView11);
        gyroZ=(TextView)findViewById(R.id.textView12);
        mX=(TextView)findViewById(R.id.textView14);
        mY=(TextView)findViewById(R.id.textView15);
        mZ=(TextView)findViewById(R.id.textView16);
        pressure=(TextView)findViewById(R.id.textView17);
        light=(TextView)findViewById(R.id.textView18);

        sm=(SensorManager)getSystemService(SENSOR_SERVICE);                //取得感應器服務

        g=sm.getDefaultSensor(Sensor.TYPE_GRAVITY);                        //取得感應器類型
        a=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyro=sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        m=sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        p=sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        l=sm.getDefaultSensor(Sensor.TYPE_LIGHT);

    }
    @Override
    protected void onResume() {                        //(app再度打開時會呼叫)打開監聽事件
        super.onResume();
        sm.registerListener(this,g,sm.SENSOR_DELAY_NORMAL);             //Sensor更新速度
        sm.registerListener(this,a,sm.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,gyro,sm.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,m,sm.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,p,sm.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,l,sm.SENSOR_DELAY_NORMAL);
        //需要與感測器互動時，必須先注冊藉此監視與感測器相關的活動，方法為SensorManger類別中的registerListener
        //利用SensorEventListener監聽Sensor之兩件事，
        // (a) onSensorChanged(SensorEvent event)當感測器的值有所改變。
        // (b)onAccuracyChanged(Sensor sensor, int accuracy)感測器精準度改變時。
    }


    protected void onPause(){                             //(app暫停時會呼叫)關閉感測器監聽事件
        super.onPause();                                 //因為不需要無時無刻都打開用不到的感測器，要不然會造成電池耗電量的增加。
        sm.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}   // (b)onAccuracyChanged(Sensor sensor, int accuracy)感測器精準度改變時。
    @Override
    public void onSensorChanged(SensorEvent event) {               // (a) onSensorChanged(SensorEvent event)當感測器的值有所改變。
        if(event.sensor.equals(g)){
            gravity[0] = event.values[0];
            gravity[1] = event.values[1];
            gravity[2] = event.values[2];
            gX.setText("X = "+gravity[0]);
            gY.setText("Y = "+gravity[1]);
            gZ.setText("Z  = "+gravity[2]);
        }
        if(event.sensor.equals(a)) {
            accelerometer[0] = event.values[0];
            accelerometer[1] = event.values[1];
            accelerometer[2] = event.values[2];
            aX.setText("X = " + accelerometer[0]);
            aY.setText("Y = " + accelerometer[1]);
            aZ.setText("Z = " + accelerometer[2]);
        }
        if(event.sensor.equals(gyro)) {
            gyroscope[0] = event.values[0];
            gyroscope[1] = event.values[1];
            gyroscope[2] = event.values[2];
            gyroX.setText("X = " + gyroscope[0]);
            gyroY.setText("Y = " + gyroscope[1]);
            gyroZ.setText("Z = " + gyroscope[2]);
        }
        if(event.sensor.equals(m)) {
            megnetometer[0] = event.values[0];
            megnetometer[1] = event.values[1];
            megnetometer[2] = event.values[2];
            mX.setText("X = " + megnetometer[0]);
            mY.setText("Y = " + megnetometer[1]);
            mZ.setText("Z = " + megnetometer[2]);
        }
        if(event.sensor.equals(p)) {
            pressure.setText("大氣壓力= " + event.values[0]+" hPa");
        }
        if(event.sensor.equals(l)) {
            light.setText("光線強度= " + event.values[0]+" lx");
        }
    }


}
