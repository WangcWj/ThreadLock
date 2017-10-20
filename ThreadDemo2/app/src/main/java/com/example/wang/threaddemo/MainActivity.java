package com.example.wang.threaddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
   final Object lockA = new Object();
   final Object lockB = new Object();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductThreadA productThreadA = new ProductThreadA();
        ProductThreadB productThreadB = new ProductThreadB();

        Thread threadA = new Thread(productThreadA);
        Thread threadB = new Thread(productThreadB);
        threadA.start();
        threadB.start();
    }


    class  ProductThreadA implements Runnable{

        @Override
        public void run() {
            synchronized (lockA){
                Log.e("CHAO","ThreadA lock  lockA");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){
                    Log.e("CHAO","ThreadA lock  lockB");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
    class  ProductThreadB implements Runnable{

        @Override
        public void run() {
            synchronized (lockB){
                Log.e("CHAO","ThreadB lock  lockB");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA){
                    Log.e("CHAO","ThreadB lock  lockA");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
