import java.util.ArrayList;

public class TaskList {
    ArrayList<String> taskList;

    TaskList() {
        taskList = new ArrayList<String>();
    }

    public void PrintList() {
        if (taskList.size() > 0) {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i+1) + ". " + taskList.get(i));
            }
        } else {
            System.out.println("No tasks! What tasks would you like to add?");
        }
    }

    public void AddTask(String task) {
        taskList.add(task);
    }

}
