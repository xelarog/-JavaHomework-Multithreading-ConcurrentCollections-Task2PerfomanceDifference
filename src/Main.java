import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    private static final int SIZE = 1_000;

    public static void main(String[] args) throws InterruptedException {

        final int[] arrayInt = fillArrayRandomInt();

        final Worker[] workers = {
                new Worker(arrayInt, new ConcurrentHashMap<>(), "ConcurrentHashMap"),
                new Worker(arrayInt, Collections.synchronizedMap(new HashMap<>()), "synchronizedMap")
        };

        long timeStart, timeEnd;
        for (Worker worker : workers) {
            timeStart = System.currentTimeMillis();
            startWork(worker);
            timeEnd = System.currentTimeMillis();
            System.out.println((timeEnd - timeStart) + " ms - время c " + worker.getCollectionName());
        }
    }

    private static void startWork(Worker worker) throws InterruptedException {
        Thread[] threads = {null, null, null};
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(null, worker::work, "Поток " + (i + 1));
            threads[i].start();
        }
        for (Thread thread : threads)
            thread.join();
    }

    private static int[] fillArrayRandomInt() {
        Random random = new Random();

        final int range = 1_000;
        int[] arr = new int[SIZE];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(range);
        }
        return arr;
    }
}
