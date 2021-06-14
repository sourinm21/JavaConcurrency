package com.test;

import java.util.concurrent.Semaphore;

public class PrintInOrder2 {

    private Semaphore semaphore1;
    private Semaphore semaphore2;
    public PrintInOrder2(){
        semaphore1= new Semaphore(0);
        semaphore2= new Semaphore(0);
    }
    public void first(Runnable printFirst){
        printFirst.run();
        semaphore1.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        semaphore1.acquire();
        printSecond.run();
        semaphore2.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        semaphore2.acquire();
        printThird.run();
    }


    public static void main(String args[]){
        PrintInOrder2 printInOrder2 = new PrintInOrder2();
        Thread t1 = new Thread(()->{
            printInOrder2.first(new PrintFirst());
        });

        Thread t2 = new Thread(()->{
            try {
                printInOrder2.second(new PrintSecond());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(()->{
            try {
                printInOrder2.third(new PrintThird());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

}

class PrintFirst implements Runnable{
    @Override
    public void run() {
        System.out.println("First====>>>");
    }
}

class PrintSecond implements Runnable{
    @Override
    public void run() {
        System.out.println("Second===>>>");
    }
}

class PrintThird implements  Runnable{
    @Override
    public void run() {
        System.out.println("Third===>>>");
    }
}
