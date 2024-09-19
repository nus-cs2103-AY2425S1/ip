package bill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private String generateAddTaskMessage(Task newTask, ArrayList<Task> userList) {
        return "added: " + newTask.toString() + "\n"
                + "Now you have " + userList.size() + " tasks in the list.\n";
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

        //update bill.txt file
        storage.saveList(userList);

        return generateAddTaskMessage(newTask, userList);
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

    private String generateEmptyTaskMessage() {
        return "List is empty\n";
    }

    private String generateTaskListMessage(ArrayList<Task> userList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < userList.size(); i++) {
            sb.append((i + 1)).append(". ").append(userList.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Shows task from userList.
     *
     * @param userList Current accessible state of mutable list.
     */
    public String showList(ArrayList<Task> userList) {
        if (userList.isEmpty()) {
            return generateEmptyTaskMessage();
        } else {
            return generateTaskListMessage(userList);
        }
    }

    private String concatenateFilterListMessage(String keyWord, StringBuilder sb) {
        sb.append("Here are the matching tasks in your list:\n");

        List<String> matchingTasks = userList.stream()
                .filter(task -> task.getDescription().contains(keyWord))
                .map(task -> (userList.indexOf(task) + 1) + ". " + task + "\n")
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            sb.append("There are no matching tasks in your list currently "
                        + "where the description field matches the keyword ").append(keyWord);
        } else {
            matchingTasks.forEach(sb::append);
        }

        return sb.toString();
    }

    private String generateFilterListMessage(String keyWord) {
        StringBuilder sb = new StringBuilder();
        return concatenateFilterListMessage(keyWord, sb);
    }

    /**
     * Deletes task from userList and calls storage to save current state of list of tasks.
     *
     * @param keyWord The keyword that the user want to match.
     */
    public String showFilterList(String keyWord) {
        if (userList.isEmpty()) {
            return generateEmptyFilterListMessage();
        } else {
            return generateFilterListMessage(keyWord);
        }
    }

    private String generateEmptyFilterListMessage() {
        return "List is empty, no matching tasks\n";
    }

    private void handleTaskMarkState(String markOrUnmark, Task targetTask) {
        if (markOrUnmark.equals("mark")) {
            targetTask.mark();
        } else {
            targetTask.unmark();
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
        handleTaskMarkState(markOrUnmark, targetTask);
        storage.saveList(userList);
        return targetTask.toString();
    }
}
