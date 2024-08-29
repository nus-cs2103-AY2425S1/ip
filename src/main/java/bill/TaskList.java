package bill;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> userList;

    public TaskList() {
        userList = new ArrayList<>();
    }

    public ArrayList<Task> getUserList() {
        return userList;
    }

    public void addTask(Task newTask, ArrayList<Task> userList, Storage storage) throws IOException {
        userList.add(newTask);
        System.out.println("added: " + newTask);
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
        //update hardisk list
        storage.saveList(userList);
    }

    public void deleteTask(int targetTaskNumber, Storage storage) throws IOException {
        Task targetTask = userList.get(targetTaskNumber);
        userList.remove(targetTaskNumber);
        storage.saveList(userList);
    }

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

    // mark tasks then clean up ui
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
