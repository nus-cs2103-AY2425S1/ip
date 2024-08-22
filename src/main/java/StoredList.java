public class StoredList {
    private Task[] data;
    private int last = 0;

    public StoredList(int len) {
        this.data = new Task[len];
    }

    public void addItem(Task item) {
        this.data[last] = item;
        this.last++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it, I've added this task: ");
        System.out.println(item);
        System.out.println("Now you have " + this.getSize() + " task in the list.");
        System.out.println("____________________________________________________________");
    }

    public Task getItem(int index) {
        return this.data[index];
    }

    public int getSize() {
        return this.last;
    }
}
