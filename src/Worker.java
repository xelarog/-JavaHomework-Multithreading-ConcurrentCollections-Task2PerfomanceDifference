import java.util.Map;
import java.util.Random;

public class Worker {

    private final int[] arrayInt;

    private final Map<Integer, Integer> map;
    private final String collectionName;

    public Worker(int[] arrayInt, Map<Integer, Integer> map, String collectionName) {
        this.arrayInt = arrayInt;
        this.map = map;
        this.collectionName = collectionName;
    }

    public void work() {
        for (int i = 0; i < arrayInt.length; i++) {
            if (map.putIfAbsent(i, arrayInt[i]) == null) {
            }
        }
        for (int i = 0; i < arrayInt.length; i++) {
            map.get(new Random().nextInt(arrayInt.length));
        }
    }

    public String getCollectionName() {
        return collectionName;
    }
}
