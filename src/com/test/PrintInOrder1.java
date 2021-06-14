package com.test;

import java.util.concurrent.Semaphore;

public class PrintInOrder1 {

    private Semaphore semaphore1;
    private Semaphore semaphore2;
    public PrintInOrder1(){
        semaphore1= new Semaphore(0);
        semaphore2= new Semaphore(0);
    }

    public void printFirst() throws InterruptedException {
        System.out.println("First===>>");
        semaphore1.release();
    }

    public void printSecond() throws InterruptedException {
        semaphore1.acquire();
        System.out.println("Second===>>");
        semaphore2.release();
    }

    public void printThird() throws InterruptedException {
        semaphore2.acquire();
        System.out.println("Third==>>");
    }

    public static void main(String args[]){
        PrintInOrder1 printInOrder1 = new PrintInOrder1();
        Thread t1 = new Thread(() -> {
            try {
                printInOrder1.printFirst();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            try {
                printInOrder1.printSecond();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(()->{
            try {
                printInOrder1.printThird();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();

    }
}
