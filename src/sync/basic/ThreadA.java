package sync.basic;

public class ThreadA extends Thread {

    public void run() {
        for (int i = 1; i <= 10; i++)
            System.out.println("A: " + i);
        System.out.println("A - done");
    }
}
