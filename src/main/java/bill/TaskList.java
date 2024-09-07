package bill;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The TaskList class allows for initialising and storing and modifying the lists of tasks during the program's run.
 */
public class TaskList {
    private ArrayList<Task> userList;

    /**
     * Initializes the userList of tasks.
     */
    public TaskList() {
        userList = new ArrayList<>();
    }

    public ArrayList<Task> getUserList() {
        return userList;
    }

    /**
     * Adds task to list to userList and calls storage to save current state of list of tasks.
     *
     * @param newTask Task the user is attempting to add to the userList.
     * @param userList Current accessible state of mutable list.
     * @param storage Storage to save the updated list to bill.txt persistent data.
     * @throws IOException If there is an error writing to the bill.txt file.
     */
    public String addTask(Task newTask, ArrayList<Task> userList, Storage storage) throws IOException {
        userList.add(newTask);

        //update hardisk list
        storage.saveList(userList);

        return "added: " + newTask.toString() + "\n"
                + "Now you have " + userList.size() + " tasks in the list.\n";
    }

    /**
     * Deletes task from userList and calls storage to save current state of list of tasks.
     *
     * @param targetTaskNumber The index of the targeted task from the userList to remove it.
     * @param storage Storage to save the updated list to bill.txt persistent data.
     * @throws IOException If there is an error writing to the bill.txt file.
     */
    public void deleteTask(int targetTaskNumber, Storage storage) throws IOException {
        Task targetTask = userList.get(targetTaskNumber);
        userList.remove(targetTaskNumber);
        storage.saveList(userList);
    }

    /**
     * Shows task from userList.
     *
     * @param userList Current accessible state of mutable list.
     */
    public String showList(ArrayList<Task> userList) {
        if (userList.isEmpty()) {
            return "List is empty\n";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < userList.size(); i++) {
                sb.append((i + 1)).append(". ").append(userList.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Deletes task from userList and calls storage to save current state of list of tasks.
     *
     * @param keyWord The keyword that the user want to match.
     */
    public String showFilterList(String keyWord) {
        // similar to show list but filter by description to contains the keyword searched by the user
        if (userList.isEmpty()) {
            return "List is empty, no matching tasks\n";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");
            boolean hasNoMatch = true;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getDescription().contains(keyWord)) {
                    sb.append(i + 1).append(". ").append(userList.get(i)).append("\n");
                    hasNoMatch = false;
                }
            }
            if (hasNoMatch) {
                sb.append("There are no matching tasks in your list currently matching the keyword ").append(keyWord);
            }
            return sb.toString();
        }
    }

    /**
     * Marks or unmarks tasks in userList.
     *
     * @param targetTaskNumber The index of the targeted task from the userList to mark or unmark it.
     * @param markOrUnmark Identifier of mark or unmark, which determines if the target task will be marked or unmarked.
     */
    public String markOrUnmarkTask(int targetTaskNumber, String markOrUnmark, Storage storage) throws IOException {
        Task targetTask = userList.get(targetTaskNumber);
        if (markOrUnmark.equals("mark")) {
            targetTask.mark();
        } else {
            // unmarked
            targetTask.unmark();
        }
        storage.saveList(userList);
        return targetTask.toString();
    }
}
