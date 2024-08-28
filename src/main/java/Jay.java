import java.util.Scanner;

public class Jay {
    public static void main(String[] args) {
        Jay jay = new Jay("Jay");
        jay.start();
    }

    private final String name;
    private final TaskList tasks;

    public Jay(String name) {
        this.name = name;
        this.tasks = new TaskList("tasks.txt");
    }

    public void start() {
        System.out.println(this.greet());
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(this.quit());
                break;
            } else {
                System.out.println(this.processCommand(command));
            }
        }
    }

    private String greet() {
        String greet =
                " Hello! I'm " + this.name + "\n" +
                " What can I do for you?";
        return formattedCommand(greet);
    }

    private String quit() {
        return formattedCommand("Bye. Hope to see you again soon!");
    }

    private String processCommand(String command) {
        String[] commands = command.split(" ");

        try {
            if (command.equals("list")) {
                return this.showTasks();
            } else if (commands[0].equals("mark") || commands[0].equals("unmark") || commands[0].equals("delete")) {
                int taskNumber = Integer.parseInt(commands[1]);

                if (!tasks.isValidTaskNumber(taskNumber)) {
                    return formattedCommand("Invalid task number");
                }

                switch (commands[0]) {
                    case "mark" -> {
                        try {
                            Task task = this.tasks.markAsDone(taskNumber);
                            return formattedCommand("Nice! I've marked this task as done:\n" + task);
                        } catch (DataIOException e) {
                            return formattedCommand("Sorry! I cannot mark this task " + e.getMessage() + "\n");
                        }
                    }
                    case "unmark" -> {
                        try {
                            Task task = this.tasks.markAsNotDone(taskNumber);
                            return formattedCommand("OK, I've marked this task as not done yet:\n" + task);
                        } catch (DataIOException e) {
                            return formattedCommand("Sorry! I cannot unmark this task " + e.getMessage() + "\n");
                        }
                    }
                    case "delete" -> {
                        return this.deleteTask(taskNumber);
                    }
                    default ->
                            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } else {
                Task.TYPE taskType = switch (commands[0]) {
                    case "todo" -> Task.TYPE.TODO;
                    case "deadline" -> Task.TYPE.DEADLINE;
                    case "event" -> Task.TYPE.EVENT;
                    default ->
                            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                };

                return this.addTask(Parser.parseTask(taskType, command));
            }
        } catch (InvalidCommandException | InvalidTaskException e) {
            return formattedCommand(e.getMessage());
        } catch (NumberFormatException e) {
            return formattedCommand("OOPS!!! Please enter a valid task number.");
        }
    }

    private String showTasks() {
        if (this.tasks.isEmpty()) {
            return formattedCommand("You have no tasks in the list.");
        } else {
            return formattedCommand("Here are the tasks in your list:\n" + tasks);
        }
    }

    private String addTask(Task task) {
        try {
            this.tasks.addTask(task);
            return formattedCommand("Got it. I've added this task:\n" + task + "\n" + this.tasks.getTaskCount());
        } catch (DataIOException e) {
            return formattedCommand(e.getMessage());
        }
    }

    private String deleteTask(int taskNumber) {
        try {
            Task task = this.tasks.removeTask(taskNumber);
            return formattedCommand("Noted. I've removed this task:\n" + task + "\n" + this.tasks.getTaskCount());
        } catch (DataIOException e) {
            return formattedCommand(e.getMessage());
        }
    }

    private String formattedCommand(String command) {
        return "____________________________________________________________\n" +
                command + "\n" +
                "____________________________________________________________\n";
    }

}
