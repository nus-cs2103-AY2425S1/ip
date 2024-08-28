import java.util.Scanner;

public class Jay {
    public static void main(String[] args) {
        Jay jay = new Jay("Jay");
        jay.start();
    }

    private final TaskList tasks;
    private final Ui ui;

    public Jay(String name) {
        this.tasks = new TaskList("tasks.txt");
        this.ui = new Ui(name);
    }

    public void start() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                ui.quit();
                break;
            } else {
               ui.output(this.processCommand(command));
            }
        }
    }

    private String processCommand(String commandStr) {
        Command command = new Command(commandStr);
        Command.CommandType commandType = command.getCommandType();

        try {
            switch (commandType) {
                case List -> {
                    return this.showTasks();
                }
                case Add -> {
                    Task.TYPE taskType = command.getTaskType();
                    return this.addTask(Parser.parseTask(taskType, command));
                }

                case Mark -> {
                    int taskNumber = command.getTaskNumber();
                    Task task = this.tasks.markAsDone(taskNumber);
                    return "Nice! I've marked this task as done:\n" + task;
                }

                case Unmark -> {
                    int taskNumber = command.getTaskNumber();
                    Task task = this.tasks.markAsNotDone(taskNumber);
                    return "OK, I've marked this task as not done yet:\n" + task;
                }

                case Delete -> {
                    int taskNumber = command.getTaskNumber();
                    return this.deleteTask(taskNumber);
                }

                case Exit -> {
                    return "Bye. Hope to see you again soon!";
                }

                default ->
                    throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (InvalidCommandException | InvalidTaskException | DataIOException e) {
            return e.getMessage();
        }
    }

    private String showTasks() {
        if (this.tasks.isEmpty()) {
            return "You have no tasks in the list.";
        } else {
            return "Here are the tasks in your list:\n" + tasks;
        }
    }

    private String addTask(Task task) {
        try {
            this.tasks.addTask(task);
            return "Got it. I've added this task:\n" + task + "\n" + this.tasks.getTaskCount();
        } catch (DataIOException e) {
            return e.getMessage();
        }
    }

    private String deleteTask(int taskNumber) {
        try {
            Task task = this.tasks.removeTask(taskNumber);
            return "Noted. I've removed this task:\n" + task + "\n" + this.tasks.getTaskCount();
        } catch (DataIOException | InvalidCommandException e) {
            return e.getMessage();
        }
    }
}
