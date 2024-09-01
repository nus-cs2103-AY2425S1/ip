import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Task> arr;

    public TaskList() {
        this.arr = new ArrayList<>(100);
    }

    public void initialise(ArrayList<Task> arr) {
        this.arr = arr;
    }

    public String add(Task input) {
        this.arr.add(input);
        return String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
                input, this.arr.size());
    }

    public String mark(int index) throws PotongException {
        if (index - 1 >= this.arr.size()) {
            throw new PotongException("We cannot mark a task thats not there!");
        }
        return this.arr.get(index - 1).mark();
    }

    public String unmark(int index) throws PotongException {
        if (index - 1 >= this.arr.size()) {
            throw new PotongException("We cannot unmark a task thats not there!");
        }
        return this.arr.get(index - 1).unmark();
    }

    public String delete(int index) throws PotongException {
        if (index - 1 >= this.arr.size()) {
            throw new PotongException("We cannot delete a task thats not there!");
        }
        Task removed = this.arr.remove(index - 1);
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.",
                removed, this.arr.size());
    }

    public void writeToStorage(Storage storage) throws IOException {
        StringBuilder result = new StringBuilder();
        for (Task curr : this.arr) {
            String type = curr.getType();
            String status = curr.getStatus();
            String description = curr.getDescription();
            String time = curr.getTime();
            if (time.isEmpty()) {
                String task = String.format("%s | %s | %s\n", type, status, description);
                result.append(task);
            } else {
                String task = String.format("%s | %s | %s | %s\n", type, status, description, time);
                result.append(task);
            }
        }
        storage.writeToFile(result.toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.arr.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, this.arr.get(i)));
        }
        return result.toString();
    }
}
