import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList = new ArrayList<>();

    public TaskList() {

    }

    public int noOfTasks() {
        return taskList.size();
    }

    public Task addTask(String input) {

        String taskName = input.split(" ", 2)[1];

        String name;
        Task t;

        if (input.contains("todo")) {
            name = taskName.trim();
            t = new ToDo(name);
            taskList.add(t);

        } else if (input.contains("deadline")) {

            String[] split = taskName.split("/", 2);
            name = split[0].trim();
            String date = split[1].split(" ", 2)[1];

            t = new Deadline(name, date);
            taskList.add(t);

        } else {
            String[] split = taskName.split("/", 3);

            name = split[0].trim();
            String startDate = split[1].split(" ", 2)[1].trim();
            String endDate = split[2].split(" ", 2)[1].trim();

            t = new Event(name, startDate, endDate);
            taskList.add(t);

        }

        return t;
    }

    public Task findTask(String input) {

        int taskNo = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;

        return taskList.get(taskNo);
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder("Here are your tasks, champ. Let's see how many you can actually cross off.\n");
        int index = 1;

        for (Task task : taskList) {
            str.append(index).append(". ").append(task.toString()).append('\n');
            index++;
        }

        if (!str.isEmpty()) {
            str.setLength(str.length() - 1);
        }


        return str.toString();

    }
}
