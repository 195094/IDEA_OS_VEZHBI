package sync.basic;

public class ThreadB extends Thread {

    public void run() {
        for (int i = -1; i >= -10; i--)
            System.out.println("\t\tB: " + i);
        System.out.println("\t\tB - done");
    }
}

