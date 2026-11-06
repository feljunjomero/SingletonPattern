public class OnlineMonitor implements QueueObserver {
    @Override
    public void onQueueUpdated(int currentNumber) {
        System.out.println("   [MONITOR] Current queue number: " + currentNumber);
    }
}