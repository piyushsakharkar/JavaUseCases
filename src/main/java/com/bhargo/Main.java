package com.bhargo;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by barya on 10/5/2016.
 */
public class Main {

    public static void main(String[] args) {
        //multipleProdCons();
        ExecutorService executorService = Executors.newScheduledThreadPool(2);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        System.out.println(executor.getMaximumPoolSize() + " " + executor.getCorePoolSize());
        //executor.submit(() -> {});

        //findHighestTwoNumbers(new Integer[]{54,856,86,12,4,66,856,35});

    }

    public static void findHighestTwoNumbers (Integer[] arr) {
        int highest, secondHighest;
        if(arr[0] > arr[1]) {
            highest = arr[0];
            secondHighest = arr[1];
        } else {
            secondHighest = arr[0];
            highest = arr[1];
        }
        for (int i =2; i<arr.length -1; i++ ) {
            if(arr[i] > secondHighest) {
                if(arr[i] > highest) {
                    secondHighest = highest;
                    highest = arr[i];
                } else if (arr[i] < highest) {
                    secondHighest = arr[i];
                }
            }
        }
        System.out.printf("The largest num is %d and the 2nd largest is %d", highest, secondHighest);
    }

    public static void sortMapByValues (Map<Integer, String> map) {
        Set<String> set = new TreeSet<>(map.values());
    }

    public static void multipleProdCons () {
        BlockingDeque<Resource> blockingDeque = new LinkedBlockingDeque<>();
        for(int i =1; i<=10;i++) {
            new Thread(new Producer(blockingDeque)).start();
        }

        for(int i =1; i<=10;i++) {
            new Thread(new Consumer(blockingDeque)).start();
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
            return str;
        }
    }

    static class Producer implements Runnable {

        private BlockingDeque<Resource> blockingDeque;

        public Producer(BlockingDeque<Resource> blockingDeque) {
            this.blockingDeque = blockingDeque;
        }

        @Override
        public void run() {
            Resource resource;
                while (true) {
                    try {
                        Thread.sleep(2000);
                        resource = new Resource(Thread.currentThread().getName() + " " + new Date().toString());
                        blockingDeque.add(resource);
                        System.out.println(resource);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    static class Consumer implements Runnable {

        private BlockingDeque<Resource> blockingDeque;

        public Consumer(BlockingDeque<Resource> blockingDeque) {
            this.blockingDeque = blockingDeque;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(2000);
                    if(blockingDeque.size() > 0)
                    System.out.println(Thread.currentThread().getName() + " consumed " + blockingDeque.take());
                } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
