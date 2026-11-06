public class PagIbigOffice {
    public static void main(String[] args) {
        CentralizedQueue queue = CentralizedQueue.getInstance();
        queue.addObserver(new OnlineMonitor());

        HelpDeskStation station1 = new HelpDeskStation("Station 1");
        HelpDeskStation station2 = new HelpDeskStation("Station 2");
        HelpDeskStation station3 = new HelpDeskStation("Station 3");
        
        station1.serveNextCustomer();   // 1
        station2.serveNextCustomer();   // 2
        station3.serveNextCustomer();   // 3
        station1.serveNextCustomer();   // 4

        // Reorganization: continue from 100
        station2.resetQueue(100);
        station3.serveNextCustomer();   // 101
        station1.serveNextCustomer();   // 102

        // Proves all stations & monitor share one instance
        System.out.println("Same instance? " +
            (CentralizedQueue.getInstance() == queue));  // true
    }
}