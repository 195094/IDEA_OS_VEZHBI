package sync.basic;

public class BasicThreadTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.run(); // preku run ne se startnuva kako proces/thread tuku vlegva vo funkcijata
        threadA.join();
        threadB.start();

        threadB.join();

      /*  CountThread countThread = new CountThread();
        countThread.start();

        try {
            countThread.join();
            System.out.println(countThread.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
