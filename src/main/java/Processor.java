import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Processor {

    public boolean HandleCommand(Command command, TaskList taskList, boolean continueScanning) {
        CommandTypes commandType = command.commandType();

        String commandDetails = Arrays.stream(command.commandDetails())
                .reduce((accumulator, element) -> accumulator + " " + element)
                .orElse("");

        switch (commandType) {
        case LIST:
            FormattedPrinting.printList(taskList);
            break;

        case MARK:
        case UNMARK:
        case DELETE:
            markUnmarkOrDelete(commandDetails, taskList, commandType);
            break;

        case TODO:
        case DEADLINE:
        case EVENT:
            createTask(commandDetails, taskList, commandType);
            break;

        case UNKNOWN:
            FormattedPrinting.unknownCommand();
            break;

        case CLEAR_TASKS:
            taskList.clearList();
            Storage.clearListFile();
            break;

        case UPCOMING_TASKS:
            upcomingTasks(taskList);
            break;

        case BYE:
            continueScanning = false;
            break;
        }

        return continueScanning;
    }

    // the following code was slightly optimised using ChatGPT
    public void markUnmarkOrDelete(String commandDetails, TaskList taskList, CommandTypes commandType) {
        try {
            int index = Integer.parseInt(commandDetails) - 1;
            Task newTask = taskList.getTask(index);
            if (newTask != null) {
                if (commandType == CommandTypes.MARK) {
                    newTask.markDone();
                } else if (commandType == CommandTypes.UNMARK) {
                    newTask.markUndone();
                } else {
                    taskList.deleteTask(newTask);
                }
            }
        } catch (NumberFormatException e) {
            FormattedPrinting.unknownNumber();
        }
    }

    // the following code was slightly optimised using ChatGPT
    public void createTask(String commandDetails, TaskList taskList, CommandTypes commandType) {
        try {
            Task newTask;
            if (commandType == CommandTypes.TODO) {
                newTask = new ToDo(commandDetails);
            } else if (commandType == CommandTypes.DEADLINE) {
                newTask = new Deadline(commandDetails);
            } else {
                newTask = new Events(commandDetails);
            }
            taskList.addTask(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            FormattedPrinting.invalidNumberOfDetails();
        } catch (DateTimeParseException e) {
            FormattedPrinting.invalidDate();
        }
    }

    public void upcomingTasks(TaskList taskList) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (Objects.equals(task.getType(), "D") ||
                    Objects.equals(task.getType(), "E")) {
                TimedTask timedTask = (TimedTask) task;
                if (LocalDateTime.now().isBefore(timedTask.getDueDate()) && !task.isDone) {
                    tasks.add(task);
                }
            }
        }
        FormattedPrinting.upcomingDeadlinesEvents(tasks);
    }

}
