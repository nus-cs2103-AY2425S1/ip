import java.util.ArrayList;

public class StoredList {
    private ArrayList<Task> data;

    public StoredList() {
        this.data = new ArrayList<Task>();
    }

    public void addItem(Task item) {
        this.data.add(item);
        System.out.println("____________________________________________________________");
        System.out.println("Got it, I've added this task: ");
        System.out.println(item);
        System.out.println("Now you have " + this.getSize() + " task in the list.");
        System.out.println("____________________________________________________________");
    }

    public Task getItem(int index) {
        return this.data.get(index);
    }

    public int getSize() {
        return this.data.size();
    }

    public void removeItem(int index) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(this.getItem(index));
        System.out.println("Now you have " + this.getSize() + " task in the list.");
        System.out.println("____________________________________________________________");
        this.data.remove(index);
    }
}
