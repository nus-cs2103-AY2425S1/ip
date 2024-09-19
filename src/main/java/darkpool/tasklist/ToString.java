package darkpool.tasklist;

import darkpool.task.Task;

import java.util.ArrayList;

/**
 * This class is used to convert the task list into a string format.
 * It is used to display the task list in a readable format.
 */
class ToString {
    static String toString(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "bozo you got no tasks";
        }

        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            temp.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n");
        }

        temp.setLength(temp.length());
        return String.valueOf(temp);
    }
}
