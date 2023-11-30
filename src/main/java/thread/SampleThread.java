package thread;

public class SampleThread extends Thread {
    public static Thread t1;
    public static void main(String[] args) throws InterruptedException {
        t1 = new SampleThread();
        t1.start();
        System.out.println("state of thread in main after start(): " + t1.getState());
        t1.join();
        System.out.println("state of thread in main after join(): " + t1.getState());
        System.out.println("state of thread main after join(): " + Thread.currentThread().getState());
    }
    @Override
    public void run() {
        System.out.println("Thread sleep: " + t1.getState());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("State of the thread in run(): " + t1.getState());
    }
}
