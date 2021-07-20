package com.test;

import java.util.concurrent.Semaphore;

/*
Number: 5
EvenThread: 0
OddThread:1
EvenThread: 2
OddThread:3
EvenThread: 4

 */


public class EvenOddThreadTestWithSemaphore {
    private int num;
    private Semaphore semaphore1;
    private Semaphore semaphore2;
    public EvenOddThreadTestWithSemaphore(int n){
        this.num= n;
        this.semaphore1=new Semaphore(1);
        this.semaphore2= new Semaphore(0);
    }

    public void printEven() throws InterruptedException {
        for(int i=0;i<num;i++){
            semaphore1.acquire();
            if(i%2==0)
                System.out.println("EvenThread: "+ i);
            semaphore2.release();
        }
    }

    public void printOdd() throws InterruptedException{
        for(int k=0;k<num;k++){
            semaphore2.acquire();
            if(k%2!=0)
                System.out.println("OddThread:"+ k);
            semaphore1.release();
        }
    }

    public static void main(String args[]){
        EvenOddThreadTestWithSemaphore evenOdd= new EvenOddThreadTestWithSemaphore(5);
        Thread t1 = new Thread(()->{
            try {
                evenOdd.printEven();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 =new Thread(()->{
            try {
                evenOdd.printOdd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }


}
