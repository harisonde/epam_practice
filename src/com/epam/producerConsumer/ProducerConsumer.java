package com.epam.producerConsumer;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();

        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

       /* Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();*/

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(consumer);
        executorService.submit(producer);

    }

}

class Producer implements Runnable {

    private final LinkedBlockingQueue<Integer> blockingQueue;
    private final Random ran = new Random();

    Producer(LinkedBlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int count = 0;

        while (count < 20) {
            synchronized (blockingQueue) {
                System.out.println("Producer acquired lock on blockingQueue");

                try {
                    if (blockingQueue.size() < 10) {
                        while (blockingQueue.size() < 10) {
                            Thread.sleep(1000);
                            int value = ran.nextInt();
                            System.out.println("Value " + value + " added to queue");

                            blockingQueue.offer(value);

                            if (blockingQueue.size() == 10) {
                                blockingQueue.notify();
                            }

                            count++;
                        }
                    } else {
                        System.out.println("Queue already has 10 elements so notifying consumer to take elements");
                        blockingQueue.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Consumer implements Runnable {

    private final LinkedBlockingQueue<Integer> blockingQueue;

    Consumer(LinkedBlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int count = 0;

        while (count < 20) {
            synchronized (blockingQueue) {
                System.out.println("Consumer acquired lock on blockingQueue");

                try {
                    if (blockingQueue.size() > 0) {
                        while (blockingQueue.size() > 0) {
                            Integer value = blockingQueue.poll();
                            System.out.println("Consumer retrieved value " + value + " from queue");
                            Thread.sleep(1000);

                            if (blockingQueue.size() == 0) {
                                blockingQueue.notify();
                            }
                            count++;
                        }
                    } else {
                        System.out.println("Queue has no elements in the queue so awaiting for producer to add elements");
                        blockingQueue.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
