import java.util.Scanner;

public class Friday {
    private String name;
    private boolean isRunning;

    private Task[] tasks;

    private int taskCount;

    public Friday() {
        this.name = "Friday";
        this.isRunning = true;
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public void greet() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Hello! I'm " + name + "\n" +
                        "     What can I do for you?\n" +
                        "    ____________________________________________________________"
        );
    }

    public void echo(String userInput) {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     " + userInput + "\n" +
                        "    ____________________________________________________________"
        );
    }

    public void sayGoodbye() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________"
        );
    }

    public void addTask(Task task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "       " + task + "\n" +
                            "     Now you have " + taskCount + " tasks in the list.\n" +
                            "    ____________________________________________________________"
            );
        } else {
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Task list is full! Cannot add more tasks.\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    public void listTasks() {
        System.out.println("    ____________________________________________________________");
        if (taskCount == 0) {
            System.out.println("     Your task list is empty.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("     " + (i + 1) + "." + tasks[i]);
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    public void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].markAsDone();
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Nice! I've marked this task as done:\n" +
                            "       " + tasks[taskIndex] + "\n" +
                            "    ____________________________________________________________"
            );
        } else {
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Invalid task number. Please enter a valid task number.\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    public void unmarkTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].unmarkAsDone();
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     OK, I've marked this task as not done yet:\n" +
                            "       " + tasks[taskIndex] + "\n" +
                            "    ____________________________________________________________"
            );
        } else {
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Invalid task number. Please enter a valid task number.\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                sayGoodbye();
                isRunning = false;
            } else if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else if (userInput.startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    markTaskAsDone(taskIndex);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     OOPS!!! Invalid input! Please enter a valid task number after 'mark'.\n" +
                                    "    ____________________________________________________________"
                    );
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    unmarkTaskAsDone(taskIndex);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     OOPS!!! Invalid input! Please enter a valid task number after 'unmark'.\n" +
                                    "    ____________________________________________________________"
                    );
                }
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5).trim();
                if (description.isEmpty()) {
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     OOPS!!! The description of a todo cannot be empty.\n" +
                                    "    ____________________________________________________________"
                    );
                } else {
                    addTask(new Todo(description));
                }
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     OOPS!!! Invalid deadline format! Use 'deadline <description> /by <time>'.\n" +
                                    "    ____________________________________________________________"
                    );
                } else {
                    addTask(new Deadline(parts[0].trim(), parts[1].trim()));
                }
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     OOPS!!! Invalid event format! Use 'event <description> /from <start time> /to <end time>'.\n" +
                                    "    ____________________________________________________________"
                    );
                } else {
                    addTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            } else {
                System.out.println("    ____________________________________________________________\n" +
                        "     OOPS!!! I'm sorry, but I don't understand the command :-(\n" +
                        "    ____________________________________________________________");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Friday friday = new Friday();
        friday.run();
    }
}
