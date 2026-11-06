import java.util.ArrayList;
import java.util.List;

public final class CentralizedQueue {

    // volatile + double-checked locking = thread-safe lazy singleton
    private static volatile CentralizedQueue instance;

    private int currentNumber = 0;
    private final List<QueueObserver> observers = new ArrayList<>();

    private CentralizedQueue() { }   // private constructor: no outside `new`

    public static CentralizedQueue getInstance() {
        if (instance == null) {
            synchronized (CentralizedQueue.class) {
                if (instance == null) {
                    instance = new CentralizedQueue();
                }
            }
        }
        return instance;
    }

    // Called when a station serves the next person
    public synchronized int issueNextNumber() {
        currentNumber++;
        notifyObservers();
        return currentNumber;
    }

    public synchronized int getCurrentNumber() {
        return currentNumber;
    }

    // Reset option for reorganization / technical issues
    public synchronized void reset(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Queue number cannot be negative.");
        }
        currentNumber = number;
        notifyObservers();
    }

    // --- Real-time monitoring support ---
    public synchronized void addObserver(QueueObserver observer) {
        observers.add(observer);
    }

    public synchronized void removeObserver(QueueObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (QueueObserver o : observers) {
            o.onQueueUpdated(currentNumber);
        }
    }
}