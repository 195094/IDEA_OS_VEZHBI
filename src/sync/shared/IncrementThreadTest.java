package sync.shared;

public class IncrementThreadTest {

    public static void main(String[] args) {
        SharedResource sr1 = new SharedResource();
        SharedResource sr2 = new SharedResource();
        SharedResource sr3 = new SharedResource();

        IncrementThread t1 = new IncrementThread(sr1);
        IncrementThread t2 = new IncrementThread(sr2);
        IncrementThread t3 = new IncrementThread(sr3);

        t1.start();
        t2.start();
        t3.start();
    }
}
