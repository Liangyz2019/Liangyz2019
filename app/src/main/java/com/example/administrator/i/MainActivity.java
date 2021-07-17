package com.example.administrator.i;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.litesuits.bluetooth.LiteBluetooth;
import com.litesuits.bluetooth.scan.PeriodScanCallback;

public class MainActivity extends AppCompatActivity {


    private BluetoothAdapter mBluetoothAdapter;

    private TextView Textview1;
    private PointImageView mPointView;
    private Algorithm mtwoPointAlgorithm;
  //  private ArrayList<IbeaconDevice> mIbeaconDevice;
    private LiteBluetooth mBLE = null;
    private static int TIME_OUT_SCAN = 60000;
    private sma sma1;
    private PeriodScanCallback mScanCallback = new PeriodScanCallback(TIME_OUT_SCAN) {
        @Override//獲取藍牙裝置名稱，信號强度
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            logDevice(device, rssi, scanRecord);//下面有函數，是把onlescan獲取的參數進行邏輯運算
        }

        @Override//掃描超時顯示
        public void onScanTimeout() {
            mBLE.startLeScan(mScanCallback);//掃描，返回布爾值
            toast("扫描超时");//toast可以理解為彈出
        }
    };

    @Override//oncreate“您必须实现此回调，它会在系统创建您的 Activity 时触发。您的实现应该初始化 Activity 的基本组件"
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//saveinstancestate是包含activity先前保存狀態的bundle對象
        setContentView(R.layout.activity_main);//定义 Activity 界面的布局

        mBLE = new LiteBluetooth(this);//LiteBluetooth.class第183行，
        sma1=new sma();//平滑濾波后得到的距離
        Textview1 = findViewById(R.id.textview1);//初始化成员textview以便我们以后可以对其进行操作
        mPointView = findViewById(R.id.imageview);

        //画一个点
        findViewById(R.id.mDrawBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointView.drawPoint(new Point(30, 30));
            }
        });

        mtwoPointAlgorithm=new Algorithm(sma1);//迭代算法

        //开启蓝牙扫描
        findViewById(R.id.mStartBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mBLE.startLeScan(mScanCallback);
            }
        });

        //获取本机蓝牙
        getBlueToothDetail();
    }

    //获取蓝牙详情
    private void getBlueToothDetail() {
        if (mBLE == null) return;

        mBluetoothAdapter = mBLE.getBluetoothAdapter();//獲取藍牙適配器
        if (mBluetoothAdapter == null) return;

        Textview1.setText("我的蓝牙：" + mBluetoothAdapter.getName() + "\n地址：" + mBluetoothAdapter.getAddress());//輸出文本
    }


    @Override//onResume方法是系统会在 Activity 开始与用户互动之前调用此回调。此时，该 Activity 位于 Activity 堆栈的顶部，并会捕获所有用户输入。
    protected void onResume() {
        super.onResume();

        System.out.println("on resume====");//借用字符输出流打印

    }



    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private iBeacon ibeacon = null;

    private void logDevice(BluetoothDevice device, int rssi, byte[] scanRecord) {
      //  Textview1.append("\n"+device.getAddress());
      //  Textview1.append("getget");
        ibeacon = iBeaconClass.fromScanData(device, rssi, scanRecord,sma1);//先进行卡尔曼滤波
     //   Textview1.append("overover");
        if (ibeacon == null) return;

       // Textview1.append("\n蓝牙名称" + ibeacon.name);
        Textview1.setText("\n" +"蓝牙地址"+ibeacon.bluetoothAddress);
       // Textview1.append("\n" + "uuid" + ibeacon.proximityUuid);
      //  Textview1.append("\n" + "major" + ibeacon.major);
      // Textview1.append("\n" + "minor" + ibeacon.minor);
        Textview1.append("\n" + "rssi" + ibeacon.rssi);//append是在原來的内容後面加，前面的settext是替換原來的内容
        Textview1.append("\n" + "distance" + ibeacon.distance);
     //   Textview1.append("\n" + "power" + ibeacon.txPower);

        double t=mtwoPointAlgorithm.algorithm(ibeacon);
        Textview1.append("\nt=="+t);
        mPointView.drawPoint(new Point(mPointView.getWidth()/2,(int)((1-t/10)*mPointView.getHeight())));//點的移動

    }

    @Override//系统会在销毁 Activity 之前调用此回调。
    protected void onDestroy() {
        super.onDestroy();

        mBLE.disableBluetooth();//disable

        if (mBLE.isConnectingOrConnected()) {
            mBLE.closeBluetoothGatt();//close
        }

        if (mBLE.isInScanning()){
            mBLE.stopScan(mScanCallback);//stop
        }
    }

}




