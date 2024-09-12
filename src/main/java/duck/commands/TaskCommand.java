package duck.commands;

import java.time.format.DateTimeParseException;
import java.util.List;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.UsageException;
import duck.tasks.DateAndTime;
import duck.tasks.Task;
import duck.utils.Formatter;

public abstract class TaskCommand extends Command {
    protected List<String> parts;
    private List<String> dateAndTimeParts;
    private TaskList taskList;

    public TaskCommand(TaskList taskList, Parser lineBuffer, String... args) {
        this.taskList = taskList;
        this.parts = lineBuffer.parseArgs(args);
        System.out.println(parts);

        // TODO: Assume that for tasks, all arguments except for description are
        // DateAndTime
        this.dateAndTimeParts = this.parts.subList(1, this.parts.size());
    }

    protected DateAndTime[] parseDateTime() throws DateTimeParseException {
        DateAndTime[] dateAndTimes = new DateAndTime[dateAndTimeParts.size()];
        for (int i = 0; i < dateAndTimeParts.size(); i++) {
            dateAndTimes[i] = new DateAndTime(dateAndTimeParts.get(i));
        }
        return dateAndTimes;
    }

    protected String handleNewTask(Task task) {
        taskList.addTask(task);
        String response = "Got it. I've added this task:\n"
                + Formatter.indentText(task.toString(), 2) + "\n"
                + "Now you have " + taskList.getTaskCount() + " tasks in the list.";
        return response;
    }

    public abstract String createNewTask(TaskList taskList) throws UsageException, DateTimeParseException;

    @Override
    public String executeCommand() {
        try {
            return createNewTask(taskList);
        } catch (UsageException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            // TODO: friendly error message
            return e.toString();
        }
    }
}
