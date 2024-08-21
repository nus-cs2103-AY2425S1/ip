import java.util.Scanner;

public class Jay {
    public static void main(String[] args) {
        Jay jay = new Jay("Jay");
        jay.start();
    }

    private final String name;
    private final Task[] tasks;
    private int taskCount;

    public Jay(String name) {
        this.name = name;
        this.tasks = new Task[100];
        this.taskCount = 0;
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

        if (command.equals("list")) {
            return this.showTasks();
        } else if (commands[0].equals("mark")) {
            try {
                int taskNumber = Integer.parseInt(commands[1]);

                if (taskNumber > this.taskCount) {
                    return formattedCommand("Invalid task number");
                }

                this.tasks[taskNumber - 1].markAsDone();
                return formattedCommand("Nice! I've marked this task as done:\n" + this.tasks[taskNumber - 1]);
            } catch (NumberFormatException e) {
                return formattedCommand("Invalid task number");
            }
        } else if (commands[0].equals("unmark")) {
            try {
                int taskNumber = Integer.parseInt(commands[1]);

                if (taskNumber > this.taskCount) {
                    return formattedCommand("Invalid task number");
                }

                this.tasks[taskNumber - 1].markAsNotDone();
                return formattedCommand("OK, I've marked this task as not done yet:\n" + this.tasks[taskNumber - 1]);
            } catch (NumberFormatException e) {
                return formattedCommand("Invalid task number");
            }
        } else {
            return this.addTask(this.parseTask(command));
        }
    }

    private String showTasks() {
        StringBuilder tasks = new StringBuilder();

        for (int i = 0; i < this.taskCount; i++) {
            if (i == this.taskCount - 1) {
                tasks.append((i + 1)).append(". ").append(this.tasks[i]);
            } else {
                tasks.append((i + 1)).append(". ").append(this.tasks[i]).append("\n");
            }
        }

        if (this.taskCount == 0) {
            return formattedCommand("You have no tasks in the list.");
        } else {
            return formattedCommand("Here are the tasks in your list:\n" + tasks);
        }
    }

    private Task parseTask(String command) {
        String taskType = command.split(" ")[0];

        switch (taskType) {
            case "todo": {
                String description = command.split(" ", 2)[1].trim();
                return new ToDoTask(description);
            }
            case "deadline": {
                String description = command.split("/")[0].split(" ", 2)[1].trim();
                String by = command.split("/")[1].split(" ", 2)[1].trim();
                return new DeadlineTask(description, by);
            }
            case "event": {
                String[] commands = command.split("/");
                String description = commands[0].split(" ", 2)[1].trim();
                String startTime = commands[1].split(" ", 2)[1].trim();
                String endTime = commands[2].split(" ", 2)[1].trim();
                return new EventTask(description, startTime, endTime);
            }
            default:
                return new Task(command);
        }
    }

    private String addTask(Task task) {
        this.tasks[this.taskCount] = task;
        this.taskCount++;
        return formattedCommand("Got it. I've added this task:\n" + task +
                "\nNow you have " + this.taskCount + " tasks in the list.");
    }

    private String formattedCommand(String command) {
        return "____________________________________________________________\n" +
                command + "\n" +
                "____________________________________________________________\n";
    }

}
