import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    List<Task> taskList = new ArrayList<>();

    public TaskList() {

    }

    public int noOfTasks() {
        return taskList.size();
    }


    public Task addTask(String input) {

        String[] taskInfo = input.split(" ", 2);
        Task t;

        if (input.contains("todo")) {
            if (taskInfo.length <= 1) {
                throw new TarsException("Add a name to your ToDo task");
            }

            String name = taskInfo[1].trim();

            t = new ToDo(name);
            taskList.add(t);

        } else if (input.contains("deadline")) {
            if (taskInfo.length <= 1) {
                throw new TarsException("Add a name to your deadline");
            }

            String[] split = taskInfo[1].split("/", 2);

            String name = split[0].trim();
            String[] date = split.length > 1
                          ? split[1].split(" ", 2)
                          : null;

            t = new Deadline(name, date);
            taskList.add(t);

        } else if (input.contains("event")) {
            if (taskInfo.length <= 1) {
                throw new TarsException("event? What is that even supposed to mean?\nAdd a name, start time and end time");
            }

            String[] split = taskInfo[1].split("/", 3);

            String name = split[0].trim();

            String[] startDate = split.length > 1
                                ? split[1].split(" ", 2)
                                : null;

            String[] endDate = split.length > 2
                                ? split[2].split(" ", 2)
                                : null;

            t = new Event(name, startDate, endDate);
            taskList.add(t);

        } else {

            throw new TarsException("Hmm, that one flew right over my circuits. Try using words in my vocabulary next time");
        }

        return t;
    }


    public Task findTask(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            int i = Integer.parseInt(matcher.group());
            if (taskList.size() < i) {
                throw new TarsException("Task not in list. Check again");
            }
            return taskList.get(i - 1);
        }

        throw new TarsException("Provide a number at the end");
    }

    public Task deleteTask(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        Task t;

        if (matcher.find()) {
            int i = Integer.parseInt(matcher.group());
            if (taskList.size() < i) {
                throw new TarsException("Task not in list. Check again");
            }
            t = taskList.get(i - 1);
            taskList.remove(t);

            return t;

        }

        throw new TarsException("Provide a number at the end");
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
