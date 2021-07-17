package com.example.administrator.i;

public class Algorithm {
    sma sma1;

    int x;
    int y;

    Algorithm(sma sma) {
      sma1=sma;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double algorithm(iBeacon now_ibeacon) {
       sma1.run(now_ibeacon.bluetoothAddress,now_ibeacon.rssi);//入队
       double buff=0;
       int temp=0;
       int count=0;
       int min_1=-1;
       int min_2=-1;
       double [] data = new double[5];// 队列
       for(int i=0;i<5;i++)//循环遍历5个队列
       {
          if(sma1.arr_queue.get(i).full())//确保一个队列大于5
              data[i]=sma1.arr_queue.get(i).getRelute();
          else
              data[i]=-1;
          if(data[i]>=0)
              count++;
       }
       if(count>=2)
       {
           double Min=10;
           double min=10;
           for(int i=0;i<5;i++)
           {
               if(Min>data[i]&&data[i]>=0)
               {
                   Min=data[i];
                   min_1=i;
               }
           }
           for(int i=0;i<5;i++)
           {
               if(min>data[i]&&data[i]>=0)
               {
                   if(data[i]>Min&&data[i]>=0)
                   {
                       min = data[i];
                       min_2 = i;
                   }
               }
           }
           if(min_1>min_2)
               buff=twopoint(sma1.arr_ineacon_device.get(min_2),sma1.arr_ineacon_device.get(min_1),sma1.arr_queue.get(min_2).getRelute(),sma1.arr_queue.get(min_1).getRelute());
           else
               buff=twopoint(sma1.arr_ineacon_device.get(min_1),sma1.arr_ineacon_device.get(min_2),sma1.arr_queue.get(min_1).getRelute(),sma1.arr_queue.get(min_2).getRelute());
       }
      return buff;
        }

        public  double twopoint(IbeaconDevice now_ibeacon,IbeaconDevice last_ibeacon,double now_dis,double last_dis)
        {
            double relute = 0;
            relute=now_dis/(now_dis+last_dis)*(last_ibeacon.y-now_ibeacon.y)+now_ibeacon.y;
            return relute;
        }



}

