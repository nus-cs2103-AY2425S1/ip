package thanos.utility;

import thanos.tasks.Task;

/**
 * The {@code ResponseFormatter} class provides methods for generating formatted responses
 * related to task management operations, such as adding tasks or displaying a list of tasks.
 */
public class ResponseFormatter {
    /**
     * Generates a formatted response after adding a task to the task list.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     * @return A formatted string indicating the task has been added and showing the current size of the task list.
     */
    public static String generateTaskAddedResponse(Task task, int size) {
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n", task, size
        );
    }

    /**
     * Generates a formatted response displaying the list of tasks.
     *
     * @param header The header to be displayed before listing the tasks.
     * @param taskList Varargs parameter representing the tasks to be displayed.
     * @return A formatted string listing all tasks with their indices, or a message indicating
     *         that no tasks were found if the varargs parameter is empty.
     */
    public static String generateTaskListResponse(String header, Task... taskList) {
        if (taskList.length == 0) {
            return "No tasks found\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(header).append("\n");
        for (int i = 0; i < taskList.length; i++) {
            Task task = taskList[i];
            sb.append(String.format("%d.%s\n", i + 1, task));
        }
        return sb.toString();
    }
}
