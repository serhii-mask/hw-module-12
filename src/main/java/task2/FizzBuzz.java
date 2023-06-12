package task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class FizzBuzz {
    private int n;
    private BlockingQueue<String> queue;
    private Object lock;
    private int current;

    public FizzBuzz(int n) {
        this.n = n;
        this.queue = new LinkedBlockingQueue<>();
        this.lock = new Object();
        this.current = 1;
    }

    public void fizz() {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 3 != 0 || current % 5 == 0)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (current > n) {
                    break;
                }
                queue.add("fizz");
                System.out.println("fizz");
                current++;
                lock.notifyAll();
            }
        }
    }

    public void buzz() {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 5 != 0 || current % 3 == 0)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (current > n) {
                    break;
                }
                queue.add("buzz");
                System.out.println("buzz");
                current++;
                lock.notifyAll();
            }
        }
    }

    public void fizzBuzz() {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 3 != 0 || current % 5 != 0)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (current > n) {
                    break;
                }
                queue.add("fizzbuzz");
                System.out.println("fizzbuzz");
                current++;
                lock.notifyAll();
            }
        }
    }

    public void number() {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 3 == 0 || current % 5 == 0)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (current > n) {
                    break;
                }
                System.out.println(current);
                current++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzBuzz);
        Thread threadD = new Thread(fizzBuzz::number);

        threadD.start();
        threadA.start();
        threadB.start();
        threadC.start();

        try {
            threadD.join();
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
