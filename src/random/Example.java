package random;


public class Example{

    public static void main(String[] args) throws InterruptedException {

        ThreadClass thread = new ThreadClass();
        thread.start();
        //thread.join();
        System.out.println(thread.getCount());
    }


    static class ThreadClass extends Thread{
        static int count = 0;
        public void increment() {
            count++;
        }
        public int getCount(){
            return count;
        }

        @Override
        public void run() {
            for(int i = 0; i < 50; i++)
                increment();
        }
    }
}