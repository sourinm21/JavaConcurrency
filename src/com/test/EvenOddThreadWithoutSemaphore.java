package com.test;

/**
 * evenThread0
 * OddThread1
 * evenThread2
 * OddThread3
 * evenThread4
 * OddThread5
 * evenThread6
 * OddThread7
 * evenThread8
 * OddThread9
 * evenThread10
 */
public class EvenOddThreadWithoutSemaphore extends Thread{
    Object lock;
    volatile static int i=0;
    EvenOddThreadWithoutSemaphore(Object lock){
        this.lock= lock;
    }

    @Override
    public void run(){

        while(i<=10){
            if(i%2==0 && Thread.currentThread().getName().equalsIgnoreCase("evenThread")){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName() + i);
                    i++;
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            if(i%2==1 && Thread.currentThread().getName().equalsIgnoreCase("oddThread")){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName() + i);
                    i++;
                    lock.notify();

                }
            }
            //System.out.println(i);
    }

}

    public static void main(String args[]){
        Object object = new Object();
        EvenOddThreadWithoutSemaphore even = new EvenOddThreadWithoutSemaphore(object);
        EvenOddThreadWithoutSemaphore odd = new EvenOddThreadWithoutSemaphore(object);
        odd.setName("OddThread");
        even.setName("evenThread");
        even.start();
        odd.start();

    }

}
