package com.example.administrator.i;


public class IbeaconDevice {


        public int major;
        public int minor;
        public String proximityUuid;
        public String bluetoothAddress;
        int x;
        int y;

        public  IbeaconDevice(String bluetoothAddress,int x,int y)
        {
              this.bluetoothAddress=bluetoothAddress;
              this.x=x;
              this.y=y;
              major=1;
              minor=7;
              proximityUuid="B5B182C7-EAB1-4988-AA99-B5C1517008D9";//每一個ibeacon設備的uuid,major和minor是要設置的一樣嗎
        }
        public int getMajor() {
            return major;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getMinor() {
            return minor;
        }

        public String getBluetoothAddress() {
            return bluetoothAddress;
        }

        public String getProximityUuid() {
            return proximityUuid;
        }





}
