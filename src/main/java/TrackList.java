import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrackList {
    private List<Task> list;
    private TrackBotStorage storage;

    public TrackList(File file, TrackBotStorage storage) throws IOException {
        this.list = new ArrayList<>();
        this.storage = storage;
        try {
            storage.loadContents(list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty task list.");
        }
    }

    private void saveList() {
        try {
            storage.saveContents(list);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tasks: " + e.getMessage());
        }
    }

    public void addToList(Task task) throws TrackBotException, IOException {
        if (task == null) {
            throw new TrackBotException("No task found.");
        }
        list.add(task);
        saveList();
        System.out.println("````````````````````````````````````````````````````````````");
        System.out.println("Successfully added this task:\n  " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println("````````````````````````````````````````````````````````````");
    }

    public void markTask(int num) throws TrackBotException {
        if (num < 0 || num > list.size() - 1) {
            throw new TrackBotException("Please enter a valid task number.");
        }
        list.get(num).mark();
        saveList();
        System.out.println("````````````````````````````````````````````````````````````");
        System.out.println("Successfully marked task " + (num + 1) + " as done:");
        System.out.println("  " + list.get(num).toString());
        System.out.println("````````````````````````````````````````````````````````````");

    }

    public void unmarkTask(int num) throws TrackBotException {
        if (num < 0 || num > list.size() - 1) {
            throw new TrackBotException("Please enter a valid task number.");
        }
        list.get(num).unmark();
        saveList();
        System.out.println("````````````````````````````````````````````````````````````");
        System.out.println("Successfully marked task " + (num + 1) + " as not done yet:");
        System.out.println("  " + list.get(num).toString());
        System.out.println("````````````````````````````````````````````````````````````");
    }
    public void deleteFromList(int num) throws TrackBotException {
        if (num < 0 || num > list.size() - 1) {
            throw new TrackBotException("Please enter a valid task number.");
        }
        String deletedTask = list.get(num).toString();
        list.remove(num);
        saveList();
        System.out.println("````````````````````````````````````````````````````````````");
        System.out.println("Successfully deleted task " + (num + 1) + " from list:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println("````````````````````````````````````````````````````````````");
    }

    public void listToString() throws TrackBotException {
        if (list.isEmpty()) {
            throw new TrackBotException("The list is currently empty.");
        }
        System.out.println("````````````````````````````````````````````````````````````\n" + "List:");
        int i = 1;
        for (Task item : list) {
            System.out.print(i + ". " + item.toString() + "\n");
            i++;
        }
        System.out.println("````````````````````````````````````````````````````````````");
    }

}
