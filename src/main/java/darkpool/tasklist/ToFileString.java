package darkpool.tasklist;

import java.util.ArrayList;

import darkpool.task.Task;

/**
 * ToFileString class is used to convert the task list to a string that can be written to a file.
 */
class ToFileString {
    static String toFileString(ArrayList<Task> taskList) {
        StringBuilder fileString = new StringBuilder();

        for (Task task : taskList) {
            fileString.append(task.toFileString());
        }

        return String.valueOf(fileString);
    }
}
