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
    public void addTask(Task newTask, ArrayList<Task> userList, Storage storage) throws IOException {
        userList.add(newTask);
        System.out.println("added: " + newTask);
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
        //update hardisk list
        storage.saveList(userList);
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
    public void showList(ArrayList<Task> userList) {
        if (userList.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println((i + 1) + "." + userList.get(i));
            }
        }
    }

    public void showFilterList(String keyWord) {
        // similar to show list but filter by description to contains the keyword searched by the user
        if (userList.isEmpty()) {
            System.out.println("List is empty, no matching tasks");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            boolean hasNoMatch = true;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getDescription().contains(keyWord)) {
                    System.out.println((i + 1) + "." + userList.get(i));
                    hasNoMatch = false;
                }
            }
            if (hasNoMatch) {
                System.out.println("There are no matching tasks in your list currently matching the keyword " + keyWord);
            }
        }
    }

    /**
     * Marks or unmarks tasks in userList.
     *
     * @param targetTaskNumber The index of the targeted task from the userList to mark or unmark it.
     * @param markOrUnmark Identifier of mark or unmark, which determines if the target task will be marked or unmarked.
     */
    public void markOrUnmarkTask(int targetTaskNumber, String markOrUnmark) {
        Task targetTask = userList.get(targetTaskNumber);
        if (markOrUnmark.equals("mark")) {
            targetTask.mark();
        } else {
            // unmarked
            targetTask.unmark();
        }
        System.out.println(targetTask);
    }
}
