package com.example.administrator.i;

import java.util.ArrayList;

public class sma {
    IbeaconDevice dev1, dev2, dev3, dev4, dev5;

    //private queue queue_x;
    public ArrayList<queue> arr_queue;//存放 5个ibeacon的动态数组 每个队列有5个值
   public  ArrayList<IbeaconDevice> arr_ineacon_device;
    //private  int LENGTH=5;

    sma()
    {
        arr_queue=new ArrayList<>();
        arr_ineacon_device=new ArrayList<>();
        dev1 = new IbeaconDevice("F5:2B:37:C4:31:5E", 0, 0);//更改ibeacon的蓝牙地址
        dev2 = new IbeaconDevice("D3:4D:FA:CD:DA:7E1", 0, 2);
        dev3 = new IbeaconDevice("F7:1D:CA:D3:61:491", 0, 4);
        dev4 = new IbeaconDevice("FD:96:41:97:3E:911", 0, 6);
        dev5 = new IbeaconDevice("CF:40:20:F4:31:0B1", 0, 8);
        arr_queue.add(new queue());//给每一个设备安排一个数组装数据
        arr_queue.add(new queue());
        arr_queue.add(new queue());
        arr_queue.add(new queue());
        arr_queue.add(new queue());
        arr_ineacon_device.add(dev1);
        arr_ineacon_device.add(dev2);
        arr_ineacon_device.add(dev3);
        arr_ineacon_device.add(dev4);
        arr_ineacon_device.add(dev5);

    }

    public  int run(String address,int rssi)
    {


        if (address.equals(dev1.bluetoothAddress))
        {
            arr_queue.get(0).offer(rssi);
           /* if (arr_queue.get(0).full())
            {
                return arr_queue.get(0).getRelute();
            }*/
           // else
            {
                return 0;
            }
        }
        else if (address.equals(dev2.bluetoothAddress))
        {
            arr_queue.get(1).offer(rssi);
            /*if (arr_queue.get(1).full())
            {
                return arr_queue.get(1).getRelute();
            }
            else*/
            {
                return 0;
            }
        }
        else if (address.equals(dev3.bluetoothAddress))
        {
            arr_queue.get(2).offer(rssi);
           /* if (arr_queue.get(2).full())
            {
                return arr_queue.get(2).getRelute();
            }
            else*/
            {
                return 0;
            }
        }
        else if (address.equals(dev4.bluetoothAddress))
        {
            arr_queue.get(3).offer(rssi);
           /* if (arr_queue.get(3).full())
            {
                return arr_queue.get(3).getRelute();
            }
            else*/
            {
                return 0;
            }
        }
        else if (address.equals(dev5.bluetoothAddress))
        {
            arr_queue.get(4).offer(rssi);
           /* if (arr_queue.get(4).full())
            {
                return arr_queue.get(4).getRelute();
            }
            else*/
            {
                return 0;
            }
        }
        else {
            return 0;
        }

    }



}
