package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.parser.Parser;
import lutodo.tasks.EventTask;
import lutodo.tasks.DeadlineTask;
import lutodo.tasks.TodoTask;
import lutodo.storage.Storage;
import java.util.NoSuchElementException;

/**
 * Represents the command of adding new tasks to the task list.
 */
public class AddCommand extends Command{

    private String taskMessage;

    private void addTodo(TaskList tasks, Storage storage) {
        try {
            String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
            TodoTask newTask = new TodoTask(taskInfo);
            tasks.addTask(newTask);
            storage.save(tasks);
            System.out.print("Got it. I've added this task:\n  "
                    + newTask.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The description of the todo task cannot be empty, please try again.");
        }
    }

    private String addTodoAndReturn(TaskList tasks, Storage storage) {
        try {
            String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
            TodoTask newTask = new TodoTask(taskInfo);
            tasks.addTask(newTask);
            storage.save(tasks);
            return "Got it. I've added this task:\n  "
                    + newTask.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list.\n";
        } catch (IndexOutOfBoundsException e) {
            return "The description of the todo task cannot be empty, please try again.";
        }
    }

    private void addDeadline(TaskList tasks, Storage storage) {
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
            storage.save(tasks);
            System.out.print("Got it. I've added this task:\n  "
                    + newTask.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("""
                        The description of the deadline task must include the ddl time, please try again.
                        Tips: use '/by' to enter the ddl date.
                        Supported format: yyyy-MM-dd
                         e.g. deadline quiz1 /by 2024-12-31
                        """);
        }
    }

    private String addDeadlineAndReturn(TaskList tasks, Storage storage) {
        try {
            String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
        } catch (IndexOutOfBoundsException e) {
            return "The description of the deadline task cannot be empty, please try again.";
        }
        String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
        try {
            DeadlineTask newTask = new DeadlineTask(taskInfo);
            tasks.addTask(newTask);
            storage.save(tasks);
            return  "Got it. I've added this task:\n  "
                    + newTask.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list.\n";
        } catch (IndexOutOfBoundsException e) {
            return  """
                    The description of the deadline task must include the ddl time, please try again.
                    Tips: use '/by' to enter the ddl date.
                    Supported format: yyyy-MM-dd
                    e.g. deadline quiz1 /by 2024-12-31
                    """;
        }
    }

    private void addEvent(TaskList tasks, Storage storage) {
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
            storage.save(tasks);
            System.out.print("Got it. I've added this task:\n  "
                    + newTask.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("""
                        The description of the event task must include the start time and the end time, please try again.
                        Tips: use '/from' and '/to' to enter the start and end time.
                        Supported time format: HH:mm, HH:mm:ss
                        e.g. event meeting1 /from 12:00 /to 14:00""");
        }
    }

    private String addEventAndReturn(TaskList tasks, Storage storage) {
        try {
            String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
        } catch (IndexOutOfBoundsException e) {
            return  "The description of the event task cannot be empty, please try again.";
        }
        String taskInfo = Parser.splitTaskInfo(taskMessage)[1];
        try {
            EventTask newTask = new EventTask(taskInfo);
            tasks.addTask(newTask);
            storage.save(tasks);
            return "Got it. I've added this task:\n  "
                    + newTask.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list.\n";
        } catch (IndexOutOfBoundsException e) {
            return """
                   The description of the event task must include the start time and the end time, please try again.
                   Tips: use '/from' and '/to' to enter the start and end time.
                   Supported time format: HH:mm, HH:mm:ss
                   e.g. event meeting1 /from 12:00 /to 14:00
                   """;
        }
    }

    /**
     * Constructs an AddCommand object with the entire task message.
     *
     * @param taskMessage The entire message imputed, including task type.
     */
    public AddCommand(String taskMessage) {
        this.taskMessage = taskMessage;
    }

    /**
     * Adds one of the 3 types of tasks to the task list.
     *
     * @param tasks The TaskList object that is to be added to.
     * @param storage The Storage object used to save the new task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        assert taskMessage != null : "task message cannot be null";
        String taskType = Parser.splitTaskInfo(taskMessage)[0].trim();
        if (taskType.equalsIgnoreCase("todo")) {
            addTodo(tasks, storage);
        } else if (taskType.equalsIgnoreCase("deadline")) {
            addDeadline(tasks, storage);
        } else if (taskType.equalsIgnoreCase("event")) {
            addEvent(tasks, storage);
        } else throw new NoSuchElementException("This kind of task is not supported: " + taskType);
    }

    /**
     * Adds one of the 3 types of tasks to the task list and returns the according response.
     *
     * @param tasks The TaskList object that is to be added to.
     * @param storage The Storage object used to save the new task list.
     * @return The string that should be shown in the Ui.
     */
    @Override
    public String executeAndRespond(TaskList tasks, Storage storage) {
        assert taskMessage != null : "task message cannot be null";
        String taskType = Parser.splitTaskInfo(taskMessage)[0].trim();
        if (taskType.equalsIgnoreCase("todo")) {
            return addTodoAndReturn(tasks, storage);
        } else if (taskType.equalsIgnoreCase("deadline")) {
            return addDeadlineAndReturn(tasks, storage);
        } else if (taskType.equalsIgnoreCase("event")) {
            return addEventAndReturn(tasks, storage);
        } else throw new NoSuchElementException("This kind of task is not supported: " + taskType);
    }


    /**
     * Returns false since this type of command is not exit command.
     *
     * @return whether this is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the type of command and the message received.
     *
     * @return Command type and message
     */
    @Override
    public String toString() {
        return "AddCommand: " + taskMessage;
    }
}
