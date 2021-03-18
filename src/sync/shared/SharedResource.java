package sync.shared;

public class SharedResource {

    private static int count = 0;

    public void incrementCount() {
        //synchronized (SharedResource.class) {
            int newCount = count;
            newCount++;
            count = newCount;
            System.out.println(count);
      //  }
    }
}
