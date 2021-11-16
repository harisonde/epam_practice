package com.epam.producerConsumer;

import java.util.Random;
import java.util.concurrent.*;

public class ProducerConsumerSemaphore {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        Semaphore producerPermit = new Semaphore(1);
        Semaphore consumerPermit = new Semaphore(0);

        ProducerSemaphore producerSemaphore = new ProducerSemaphore(queue, producerPermit, consumerPermit);
        ConsumerSemaphore consumerSemaphore = new ConsumerSemaphore(queue, producerPermit, consumerPermit);

        Thread producer = new Thread(producerSemaphore);
        Thread consumer = new Thread(consumerSemaphore);

        producer.start();
        consumer.start();

        Thread.sleep(12000);

        System.out.println("Interrupting consumer thread");
        consumer.interrupt();
    }
}

class ProducerSemaphore implements Runnable {

    private final LinkedBlockingQueue<Integer> queue;
    private final Semaphore producerSemaphore;
    private final Semaphore consumerSemaphore;
    private final Random random = new Random();

    ProducerSemaphore(LinkedBlockingQueue<Integer> queue, Semaphore producerSemaphore, Semaphore consumerSemaphore) {
        this.queue = queue;
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
    }

    @Override
    public void run() {
        int count = 0;

        while (count < 30) {
            try {
                producerSemaphore.acquire();
                System.out.println("Producer acquired permit");

                while (queue.size() < 10) {
                    Thread.sleep(1000);
                    int value = random.nextInt();
                    System.out.println("Value " + value + " added to queue");

                    queue.offer(value);

                    if (queue.size() == 10) {
                        consumerSemaphore.release();
                    }
                    count++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConsumerSemaphore implements Runnable{
    private final LinkedBlockingQueue<Integer> queue;
    private final Semaphore producerSemaphore;
    private final Semaphore consumerSemaphore;

    ConsumerSemaphore(LinkedBlockingQueue<Integer> queue, Semaphore producerSemaphore, Semaphore consumerSemaphore) {
        this.queue = queue;
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
    }

    @Override
    public void run() {
        int count = 0;

        while(count < 20){
            try {
                consumerSemaphore.acquire();
                System.out.println("Consumer acquired permit");

                while (queue.size() > 0 && !Thread.interrupted()) {
                    Integer value = queue.poll();
                    System.out.println("Value " + value + " retrieved from queue");

                    if (queue.size() == 0) {
                        producerSemaphore.release();
                    }
                    count++;
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer thread is Interrupted " + e);
                break;
            }
        }
    }
}
