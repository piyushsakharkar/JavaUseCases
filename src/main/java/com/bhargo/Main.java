package com.bhargo;

import java.util.Date;
/**
 * Created by barya on 10/5/2016.
 */
public class Main {

    public static void main(String[] args) {

        Resource resource = new Resource("");
        for(int i =1; i<=5;i++) {
            new Thread(new Consumer(resource)).start();
        }
        for(int i =1; i<=5;i++) {
            new Thread(new Producer(resource)).start();
        }

    }

    static class Resource {
        private String str;

        public Resource(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return "Resource{" +
                    "str='" + str + '\'' +
                    '}';
        }
    }

    static class Producer implements Runnable {

        private Resource resource;

        public Producer(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                        synchronized (resource) {
                            resource.setStr("Set by " + Thread.currentThread().getName() + " " + new Date().toString());
                            System.out.println(Thread.currentThread().getName() + " has set " + resource.getStr());
                            resource.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    static class Consumer implements Runnable {

        private Resource resource;

        public Consumer(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (resource) {
                try {
                    resource.wait();
                    System.out.println("Consumed by " + Thread.currentThread().getName()+ "  >>> " + resource.getStr());
                } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        }
}
