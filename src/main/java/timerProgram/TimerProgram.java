package timerProgram;

public class TimerProgram {
    public static void main(String[] args) {
        Thread timerThread = new Thread(new Timer());
        Thread messageThread = new Thread(new Message());

        timerThread.start();
        messageThread.start();
    }
}