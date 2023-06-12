package task2;

class FizzBuzz {
    private final int n;
    private volatile int current;

    public FizzBuzz() {
        this.n = 15;
        this.current = 1;
    }

    public FizzBuzz(int n) {
        this.n = n;
        this.current = 1;
    }

    public synchronized void fizzBuzz() {
        while (current <= n) {

            if (current % 3 == 0 && current % 5 == 0) {
                System.out.println("fizzbuzz");
                current++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void fizz() {
        while (current <= n) {

            if (current % 3 == 0 && current % 5 != 0) {
                System.out.println("fizz");
                current++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void buzz() {
        while (current <= n) {

            if (current % 5 == 0 && current % 3 != 0) {
                System.out.println("buzz");
                current++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void number() {
        while (current <= n) {

            if (current % 3 != 0 && current % 5 != 0) {
                System.out.println(current);
                current++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);

        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzBuzz);
        Thread threadD = new Thread(fizzBuzz::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
