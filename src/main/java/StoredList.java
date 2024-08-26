import java.util.ArrayList;

public class StoredList {
    private ArrayList<Task> data;

    public StoredList() {
        this.data = new ArrayList<Task>();
    }

    public String addItem(Task item) {
        StringBuilder message = new StringBuilder();
        this.data.add(item);
        message.append("____________________________________________________________\n");
        message.append("Got it, I've added this task: \n");
        message.append(item).append("\n");
        message.append("Now you have ").append(this.getSize()).append(" task in the list.\n");
        message.append("____________________________________________________________\n");
        return message.toString();
    }

    public Task getItem(int index) {
        return this.data.get(index);
    }

    public int getSize() {
        return this.data.size();
    }

    public String removeItem(int index) {
        StringBuilder message = new StringBuilder();
        message.append("____________________________________________________________\n");
        message.append("Noted. I've removed this task:\n");
        message.append(this.getItem(index)).append("\n");
        message.append("Now you have ").append(this.getSize()).append(" task in the list.\n");
        message.append("____________________________________________________________\n");
        this.data.remove(index);
        return message.toString();
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("____________________________________________________________\n");
        for (int i = 0; i < this.getSize(); i++) {
            message.append(i+1).append(". ").append(this.getItem(i).toString()).append("\n");
        }
        message.append("____________________________________________________________");
        return message.toString();
    }
}
