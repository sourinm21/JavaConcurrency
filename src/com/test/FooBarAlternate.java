package com.test;

import java.util.concurrent.Semaphore;

/*
Print FooBar Alternately
Number: 3
Foo
Bar
Foo
Bar
Foo
Bar
 */
public class FooBarAlternate {
    private Semaphore semaphore1;
    private Semaphore semaphore2;
    private int number;
    public FooBarAlternate(int number){
        semaphore1= new Semaphore(1);
        semaphore2 = new Semaphore(0);
        this.number= number;
        System.out.println("Number: "+ this.number);
    }

    private void foo() throws InterruptedException {
        for(int i=0;i<number;i++){
            semaphore1.acquire();
            System.out.println("Foo");
            semaphore2.release();
        }
    }
    private void bar() throws InterruptedException {
        for(int j=0;j<number;j++){
            semaphore2.acquire();
            System.out.println("Bar");
            semaphore1.release();
        }
    }

    public static void main(String args[]){
        FooBarAlternate fooBarAlternate = new FooBarAlternate(3);
        Thread t1 = new Thread(()->{
            try {
                fooBarAlternate.foo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            try {
                fooBarAlternate.bar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }
}
