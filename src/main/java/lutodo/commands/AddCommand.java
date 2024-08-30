package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.parser.Parser;
import lutodo.tasks.EventTask;
import lutodo.tasks.DeadlineTask;
import lutodo.tasks.TodoTask;
import lutodo.storage.Storage;
import java.util.NoSuchElementException;

public class AddCommand extends Command{

    private String taskMessage;
    public AddCommand(String taskMessage) {
        this.taskMessage = taskMessage;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        String taskType = Parser.splitTaskInfo(taskMessage)[0].trim();
        if (taskType.equalsIgnoreCase("todo")) {
            try {
                String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
                TodoTask newTask = new TodoTask(taskInfo);
                tasks.addTask(newTask);
                System.out.print("Got it. I've added this task:\n  " +
                        newTask.toString() +
                        "\nNow you have " + tasks.size() + " tasks in the list.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The description of the todo task cannot be empty, please try again.");
            }
        } else if (taskType.equalsIgnoreCase("deadline")) {
            try {
                String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The description of the deadline task cannot be empty, please try again.");
                return;
            }
            String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
            try {
                DeadlineTask newTask = new DeadlineTask(taskInfo);
                tasks.addTask(newTask);
                System.out.print("Got it. I've added this task:\n  " +
                        newTask.toString() +
                        "\nNow you have " + tasks.size() + " tasks in the list.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.print("""
                        The description of the deadline task must include the ddl time, please try again.
                        Tips: use '/by' to enter the ddl date.
                        Supported format: yyyy-MM-dd
                         e.g. deadline quiz1 /by 2024-12-31
                        """);
            }
        } else if (taskType.equalsIgnoreCase("event")) {
            try {
                String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The description of the event task cannot be empty, please try again.");
                return;
            }
            String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
            try {
                EventTask newTask = new EventTask(taskInfo);
                tasks.addTask(newTask);
                System.out.print("Got it. I've added this task:\n  " +
                        newTask.toString() +
                        "\nNow you have " + tasks.size() + " tasks in the list.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.print("""
                        The description of the event task must include the start time and the end time, please try again.
                        Tips: use '/from' and '/to' to enter the start and end time.
                        Supported time format: HH:mm, HH:mm:ss
                        e.g. event meeting1 /from 12:00 /to 14:00""");
            }
        } else throw new NoSuchElementException("This kind of task is not supported: " + taskType);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
