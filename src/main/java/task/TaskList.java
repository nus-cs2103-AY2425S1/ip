package task;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList extends ArrayList<Task> {

    public TaskList(ArrayList<Task> taskList) {
        super();
        this.addAll(taskList);
    }

    public static <T> String arrayToNumberedString(ArrayList<T> array) {
        return IntStream.range(0, array.size()).mapToObj(number -> number + 1 + ". " + array.get(number)).map(Objects::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for in the tasks.
     * @return A TaskList containing tasks that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }
}
