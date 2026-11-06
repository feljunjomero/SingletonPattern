public class HelpDeskStation {
    private final String name;
    private final CentralizedQueue queue = CentralizedQueue.getInstance(); // same instance

    public HelpDeskStation(String name) {
        this.name = name;
    }

    public void serveNextCustomer() {
        int ticket = queue.issueNextNumber();
        System.out.println("[" + name + "] Now serving number: " + ticket);
    }

    public void resetQueue(int number) {
        queue.reset(number);
        System.out.println("[" + name + "] Queue reset to " + number);
    }
}