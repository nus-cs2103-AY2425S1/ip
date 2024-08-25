package level2;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<TaskModel> list = new ArrayList<>();

    public void addTask(TaskModel task) {
        list.add(task);
        System.out.println("added: " + task.toString());
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                string.append("\n");
            }
            string.append(Integer.toString(i + 1)).append(". ").append(list.get(i).toString());
        }

        return string.toString();
    }
}