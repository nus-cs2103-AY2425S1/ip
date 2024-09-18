package bot;

import taskType.Task;
import taskType.TaskBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

import java.util.Scanner;
import bot.Parser.TaskType;

/**
 * The ListManager class manages a collection of tasks, allowing for creation, listing,
 * updating, and deletion of tasks. It uses an ArrayList to store the tasks and provides
 * methods for interacting with this list.
 */
public class ListManager {
    private ArrayList<Task> itemList = new ArrayList<>();
    private FileManager chickenFileManager = new FileManager("data");


    /**
     * Constructor that automatically loads tasks from the "data" file located in the "src/main/java" directory.
     * If the file does not exist, it is created. Each line from the file is read and passed to the parseTask method.
     *
     */
    public ListManager() {
        try {
            File file = new File("data");
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                parseTask(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line of text from the file to identify the task type and create an appropriate Task object.
     *
     * @param line The string representation of a task, formatted with specific markers for type and status.
     */
    private void parseTask(String line) {
        String type = line.substring(0, 3);  // Extract the type like [T], [D], [E]
        boolean isDone = line.charAt(4) == 'X';


        if (type.equals("[T]")) {
            TaskBuilder taskBuilder = new TaskBuilder(line.substring(7), TaskType.TODO);
            String task = this.createItem(taskBuilder).toString();

        } else if (type.equals("[D]")) {
            // For a Deadline task, extract the description and due date
            String[] parts = line.substring(7).split("\\(by: ");
            String description = parts[0].trim();
            TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.DEADLINE);

            String by = parts.length > 1 ? parts[1].replace(")", "").trim() : "Invalid date format!";
            String task = this.createItem(taskBuilder.specialBy(by)).toString();

        } else if (type.equals("[E]")) {
            // extract the description and from/to dates
            String[] parts = line.substring(7).split("\\(from: ");
            String description = parts[0].trim();
            TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.EVENT);

            if (parts.length > 1) {
                String[] dates = parts[1].split("to: ");
                String from = dates[0].trim();
                String to = dates.length > 1 ? dates[1].replace(")", "").trim() : "Invalid date format!";
                System.out.println(from);
                String task = this.createItem(taskBuilder.specialFrom(from).specialTo(to)).toString();

            }
        }
    }

    public ArrayList<Task> getItemList() {
        return itemList;
    }

    /**
     * Creates a new task item using the provided TaskBuilder.
     * It first builds the task, checks if a similar task already exists in the list,
     * and then adds the new task to the list if it's unique.
     *
     * @param builder The TaskBuilder object that contains the details necessary to build the Task.
     * @return The created Task object.
     * @throws IllegalArgumentException if the task already exists in the itemList.
     */
    public Task createItem(TaskBuilder builder) throws IllegalArgumentException {
        Task task = builder.build();
        for (int i = 0; i < itemList.size(); i++) {
            Task task2 = itemList.get(i);
            if (task2.isEqual(task)) {
                throw new IllegalArgumentException("This item already exists");
            }
        }
        itemList.add(builder.build());
        return task;
    }

    /**
     * Returns all tasks that include the specified string in their description.
     * If an empty string ("") is passed, all tasks are returned.
     *
     * @param item The string to search for within task descriptions.
     * @return A list of tasks that match the search criteria.
     */

    public String listItems(String item) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < itemList.size(); i++) {
            Task task = itemList.get(i);
            if (task.toString().toLowerCase().contains(item.toLowerCase())) {
                result.append(i + 1).append(". ").append(task.toString()).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Sets the completion status of a task at the specified index.
     *
     * @param done  The completion status to set (true for done, false for not done).
     * @param index The index of the task in the itemList (1-based index).
     */
    public boolean setDone(boolean done, int index) {
        boolean isValidIndex = index > 0 && index <= itemList.size();

        assert isValidIndex : "Index out of range";

        if (isValidIndex) {
            itemList.get(index - 1).setDone(done);
            chickenFileManager.markTaskAsDone(index);
            return true;
        }
        return false;
    }

    /**
     * Deletes an item from the list at the specified index.
     *
     * @param index the 1-based index of the item to be deleted from the list
     * @throws AssertionError if the index is out of the valid range
     */
    public boolean delete(int index) {
        boolean isValidIndex = index > 0 && index <= itemList.size();

        assert isValidIndex : "Index out of range";

        if (isValidIndex) {
            itemList.remove(index - 1);
            try {
                chickenFileManager.deleteLine(index);
                return true;
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return false;
    }


    public int getItemSize() {
        return itemList.size();
    }

    /**
     * Retrieves the string representation of the task at the specified index.
     *
     * @param index The index of the task to retrieve (1-based index. We will handle it to be 0-based in this method with a -1).
     * @return The string representation of the task, or an empty string if the index is invalid.
     */
    public String getItem(int index) {
        boolean isValidIndex = index > 0 && index <= itemList.size();

        assert index >= 0:"Index for getItem is negative!";
        if (isValidIndex) {
            Task task = itemList.get(index - 1);
            return task.toString();
        }
        return "";
    }


}
