import java.util.Scanner;
import java.util.ArrayList;

public class Winner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(("-".repeat(100) + "\n" +
                "Hello! I am Winner, your personal task trackBOT!" + "\n"
                + "You can send me these commands in the form shown below so I can help you keep track of your tasks :" + "\n"
                + " ".repeat(5) + "- todo (task) --> tasks without any date/time attached" + "\n"
                + " ".repeat(5) + "- deadline (task) by (deadline) --> tasks with a deadline" + "\n"
                + " ".repeat(5) + "- event (task) from (start) to (end) --> tasks with a start and end date/time" + "\n"
                + "\n"
                + "You can also use these additional commands:" + "\n"
                + " ".repeat(5) + "- list --> shows you your list of tasks" + "\n"
                + " ".repeat(5) + "- mark (task number) --> mark the task number that you input as done" + "\n"
                + " ".repeat(5) + "- unmark (task number) --> mark the task number that you input as undone" + "\n"
                + " ".repeat(5) + "- delete (task number) --> remove the task number that you input from your list of tasks" + "\n"
                + "-".repeat(100)).indent(10));

        while (true) {
            try {
                String input = scanner.nextLine();

                if (input.matches("(?i)hi|hello")) {
                    System.out.println(("-".repeat(100) + "\n"
                            + "hi" + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+todo\\b+.*")) {
                    String description = input.split("todo", 2)[1].trim().toLowerCase();
                    if (description.isEmpty()) {
                        throw new WinnerException("""
                                Oh no! Your task cannot be empty.
                                Please tell me your task in this form :
                                todo (task)""");
                    }
                    ToDo newToDo = new ToDo(description);
                    tasks.add(newToDo);
                    System.out.println(("-".repeat(100) + "\n"
                            + newToDo.addTaskToString() + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+deadline\\b+.*")) {
                    String[] parts = input.split("(?i)\\b+deadline\\b+ | \\bby\\b");
                    if (parts.length != 3) {
                        throw new WinnerException("""
                                Oh no! You are missing a task and a deadline.
                                Please tell me your task in this form :
                                deadline (task) by (deadline)""");
                    }
                    if (parts[1].trim().isEmpty()) {
                        throw new WinnerException("""
                                Oh no! Your task cannot be empty.
                                Please tell me your task in this form :
                                deadline (task) by (deadline)""");
                    }
                    if (parts[2].trim().isEmpty()) {
                        throw new WinnerException("""
                                Oh no! We are going to need a deadline for this task.
                                Please tell me your task in this form :
                                deadline (task) by (deadline)""");
                    }
                    String description = parts[1].trim().toLowerCase();
                    String by = parts[2].trim().toLowerCase();
                    Deadline newDeadline = new Deadline(description, by);
                    tasks.add(newDeadline);
                    System.out.println(("-".repeat(100) + "\n"
                            + newDeadline.addTaskToString() + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+event\\b+.*")) {
                    String[] parts = input.split("(?i)\\b+event\\b+ | \\bfrom\\b | \\bto\\b");
                    if (parts.length != 4) {
                        throw new WinnerException("""
                                Oh no! You are missing a task, start and end day/date/time.
                                Please tell me your task in this form :
                                event (task) from (start) to (end)""");
                    }

                    if (parts[1].trim().isEmpty()) {
                        throw new WinnerException("""
                                Oh no! Your task cannot be empty.
                                Please tell me your task in this form :
                                event (task) from (start) to (end)""");
                    }
                    if (parts[2].trim().isEmpty()) {
                        throw new WinnerException("""
                                Oh no! We are going to need a start day/date/time for this task.
                                Please tell me your task in this form :
                                event (task) from (start) to (end)""");
                    }
                    if (parts[3].trim().isEmpty()) {
                        throw new WinnerException("""
                                Oh no! We are going to need an end day/date/time for this task.
                                Please tell me your task in this form :
                                event (task) from (start) to (end)""");
                    }
                    String description = parts[1].trim().toLowerCase();
                    String start = parts[2].trim().toLowerCase();
                    String end = parts[3].trim().toLowerCase();
                    Event newEvent = new Event(description, start, end);
                    tasks.add(newEvent);
                    System.out.println(("-".repeat(100) + "\n"
                            + newEvent.addTaskToString() + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+list\\b+.*")) {
                    int counter = 1;
                    System.out.println(" ".repeat(10) + "-".repeat(100));
                    System.out.println(" ".repeat(10) + "Here are the tasks you have in your list:");
                    for (Task i : tasks) {
                        System.out.println(" ".repeat(10) + counter + ". " + i.taskToString());
                        counter++;
                    }
                    System.out.println(" ".repeat(10) + "-".repeat(100) + "\n");

                } else if (input.matches("(?i).*\\b+mark\\b+.*")) {
                    int taskNumber = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new WinnerException("""
                                Oh no! I cannot mark this task as done because the number is invalid.
                                Please tell me which task to mark as done in this form :
                                mark (task number)""");
                    }
                    Task task = tasks.get(taskNumber - 1);
                    if (task.isDone) {
                        throw new WinnerException("You have already marked this task as DONE :D");
                    }
                    task.markDone();
                    System.out.println(("-".repeat(100) + "\n"
                            + task.markDoneToString() + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+unmark\\b+.*")) {
                    int taskNumber = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new WinnerException("""
                                Oh no! I cannot mark this task as undone because the number is invalid.
                                Please tell me which task to mark as undone in this form :
                                unmark (task number)""");
                    }
                    Task task = tasks.get(taskNumber - 1);
                    if (!task.isDone) {
                        throw new WinnerException("You cannot unmark this task as it has not been marked as done :(");
                    }
                    task.unmarkDone();
                    System.out.println(("-".repeat(100) + "\n"
                            + task.unmarkDoneToString() + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+delete\\b+.*")) {
                    int taskNumber = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new WinnerException("""
                                Oh no! I cannot remove this task from the list because the number is invalid.
                                Please tell me which task to remove in this form :
                                delete (task number)""");
                    }
                    Task task = tasks.get(taskNumber - 1);
                    tasks.remove(taskNumber - 1);
                    System.out.println(("-".repeat(100) + "\n"
                            + task.deleteTask() + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*bye.*")) {
                    System.out.println(("-".repeat(100) + "\n"
                            + "Hope I have been of service!" + "\n"
                            + "Thank you and see you again soon :D" + "\n"
                            + "Remember!!! A WINNER NEVER LOSES!!!" + "\n"
                            + "-".repeat(100)).indent(10));
                    break;
                } else {
                    throw new WinnerException("""
                            Sorry, I do not know what that means
                            ⡴⠒⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠉⠳⡆⠀
                            ⣇⠰⠉⢙⡄⠀⠀⣴⠖⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣆⠁⠙⡆
                            ⠘⡇⢠⠞⠉⠙⣾⠃⢀⡼⠀⠀⠀⠀⠀⠀⠀⢀⣼⡀⠄⢷⣄⣀⠀⠀⠀⠀⠀⠀⠀⠰⠒⠲⡄⠀⣏⣆⣀⡍
                            ⠀⢠⡏⠀⡤⠒⠃⠀⡜⠀⠀⠀⠀⠀⢀⣴⠾⠛⡁⠀⠀⢀⣈⡉⠙⠳⣤⡀⠀⠀⠀⠘⣆⠀⣇⡼⢋⠀⠀⢱
                            ⠀⠘⣇⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⡴⢋⡣⠊⡩⠋⠀⠀⠀⠣⡉⠲⣄⠀⠙⢆⠀⠀⠀⣸⠀⢉⠀⢀⠿⠀⢸
                            ⠀⠀⠸⡄⠀⠈⢳⣄⡇⠀⠀⢀⡞⠀⠈⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⠀⠈⢧⠀⠀⢳⣰⠁⠀⠀⠀⣠⠃
                            ⠀⠀⠀⠘⢄⣀⣸⠃⠀⠀⠀⡸⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠈⣇⠀⠀⠙⢄⣀⠤⠚⠁⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⢘⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⢰⣿⣿⣿⡿⠛⠁⠀⠉⠛⢿⣿⣿⣿⣧⠀⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡀⣸⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⡀⢀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡇⠹⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⡿⠁⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣤⣞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢢⣀⣠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠙⠲⢤⣀⣀⠀⢀⣀⣀⠤⠒⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""");
                }
            } catch (WinnerException e) {
                System.out.println(("-".repeat(100) + "\n"
                        + e.getMessage() + "\n"
                        + "-".repeat(100)).indent(10));
            }
        }
        scanner.close();
    }
}