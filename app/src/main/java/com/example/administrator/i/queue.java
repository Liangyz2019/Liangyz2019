package com.example.administrator.i;

import static java.lang.Math.abs;

public class queue {
    private float[] data ;// 队列
    private int front;// 队列头，允许删除
    private int rear;// 队列尾，允许插入
    private int LENGTH=5;
    private int full=0;
    private int t=0;//判断队列是否装满
    private double relute=0;

    public queue() {
        data = new float[LENGTH];
        front = rear = 0;
    }

    // 入队
    public void offer(float date) {
        if (rear>=LENGTH)
        {
            rear=0;
        }
        data[rear++] = date;

        if (t++>=(LENGTH-1))
        {
            full=1;
            relute=0;
            //relute=(int)(data[0]+data[1]+data[2]+data[3]+data[4])/5;
            for (int i=0;i<LENGTH;i++)
            {
                relute += data[i];
                if(i==(LENGTH-1))
                {
                    relute = relute / LENGTH;
                    relute=getDistance(-51,relute);
                }
            }
        }

    }

    private double getDistance(int txPower, double rssi)
    {
            if (rssi >= 0) {
                return -1.0;
            }
            if (txPower == 0) {
                return -1.0;
            }
            double ratio = rssi * 1.0 / txPower;
            if (ratio < 1.0) {
                return Math.pow(ratio, 10);
            } else {
                double distance= (0.42093) * Math.pow(ratio, 6.9476) + 0.54992;
                return distance;
            }

    }


    // 出队
    public float poll() {

        if (front<LENGTH)
        {
            float value = data[front];// 保留队列的front端的元素的值

            front++;
            return value;

        }
        else
        {
            front=0;
            float value = data[front];
            return  value;
        }

    }

    public boolean full()
    {
        if (full==1)
            return  true;
        else
            return false;
    }

    public double getRelute() {
        return relute;
    }
}
