package sync.basic;

public class CountThread extends Thread {

    private long result;

    public long getResult() {
        return result;
    }

    private long count() {
        long r = 0;
        for (r = 0; r < 1000000; r++) ;
        return r;
    }

    public void run() {
        result = count();
    }
}
