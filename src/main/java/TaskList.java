import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    List<Task> taskList;

    Parser toDoParser = new ToDoParser();
    Parser deadlineParser = new DeadlineParser();
    Parser eventParser = new EventParser();

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int noOfTasks() {
        return taskList.size();
    }

    public Task addTask(String input) {

        String[] taskInfo = input.split(" ", 2);
        Task t;

        String taskType = taskInfo[0];

        if (taskType.contains("todo")) {
            t = toDoParser.parse(taskInfo);
            taskList.add(t);

        } else if (taskType.contains("deadline")) {
            t = deadlineParser.parse(taskInfo);
            taskList.add(t);

        } else if (taskType.contains("event")) {
            t = eventParser.parse(taskInfo);
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

    public List<Task> getTasks() {
        return taskList;
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
