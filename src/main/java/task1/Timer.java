package task1;

import java.util.concurrent.TimeUnit;

class Timer implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime + 1000;
            System.out.println("Пройшло " + TimeUnit.MILLISECONDS.toSeconds(elapsedTime) + " секунд");

            try {
                Thread.sleep(1000); // Пауза 1 секунда
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}