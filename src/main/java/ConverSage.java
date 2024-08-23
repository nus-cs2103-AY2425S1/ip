import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConverSage {
    public static void main(String[] args) {
        String logo = "  ____                          ____                   \n" +
                " / ___|___  _ ____   _____ _ __/ ___|  __ _  __ _  ___ \n" +
                "| |   / _ \\| '_ \\ \\ / / _ \\ '__\\___ \\ / _` |/ _` |/ _ \\\n" +
                "| |__| (_) | | | \\ V /  __/ |   ___) | (_| | (_| |  __/\n" +
                " \\____\\___/|_| |_|\\_/ \\___|_|  |____/ \\__,_|\\__, |\\___|\n" +
                "                                            |___/      ";
        String horizontalLine = "____________________________________________________________";
        System.out.println(logo);
        System.out.println("Greetings, I'm your ConverSage.");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        String input;

        List<Task> taskList = new ArrayList<>();

        while (true) {
            input = scanner.nextLine();
            System.out.println(horizontalLine);

            try {
                if (input.equalsIgnoreCase("list")) {
                    for (int i = 1; i <= taskList.size(); i++) {
                        System.out.println(i + ". " + taskList.get(i-1));
                    }
                    System.out.println(horizontalLine);
                } else if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Goodbye. We shall meet again soon.");
                    System.out.println(horizontalLine);
                    break;
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        throw new ConverSageException("Task Number is invalid...");
                    }
                    Task currTask = taskList.get(taskIndex);

                    try {
                        String doneTask = currTask.markAsDone();
                        System.out.println("Good job. I've marked this task as done.");
                        System.out.println(doneTask);
                    } catch (ConverSageException e) {
                        System.out.println("ERR " + e.getMessage());
                    }

                    System.out.println(horizontalLine);


                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        throw new ConverSageException("Task Number is invalid...");
                    }

                    Task currTask = taskList.get(taskIndex);
                    try {
                        String undoneTask = currTask.markAsUndone();
                        System.out.println("I've marked this task as not done yet, get to it quickly.");
                        System.out.println(undoneTask);
                    } catch (ConverSageException e) {
                        System.out.println("ERR: " + e.getMessage());
                    }

                    System.out.println(horizontalLine);

                } else if (input.startsWith("todo")) {
                    try {
                        if (input.length() <= 5) {
                            throw new ConverSageException("The description of a todo cannot be empty.");
                        }
                        String taskDesc = input.substring(5);
                        Task newTask = new ToDo(taskDesc);
                        taskList.add(newTask);

                        System.out.println("Understood, I've added this task: ");
                        System.out.println("  " + newTask);
                        System.out.println("You have " + taskList.size() + " tasks in your list" );
                    } catch (Exception e) {
                        System.out.println("ERR: " + e.getMessage());
                    }

                    System.out.println(horizontalLine);

                } else if (input.startsWith("deadline")) {
                    try {
                        if (input.length() <= 9 || !input.contains(" /by ")) {
                            throw new ConverSageException("The deadline task requires a description and a deadline.");
                        }
                        String[] deadlineTaskParts = input.substring(9).split(" /by ");
                        String taskDesc = deadlineTaskParts[0];
                        String deadline = deadlineTaskParts[1];
                        Task newTask = new Deadline(taskDesc, deadline);
                        taskList.add(newTask);

                        System.out.println("Understood, I've added this task: ");
                        System.out.println("  " + newTask);
                        System.out.println("You have " + taskList.size() + " tasks in your list" );
                    } catch (ConverSageException e) {
                        System.out.println("ERR: " + e.getMessage());
                    }

                    System.out.println(horizontalLine);
                } else if (input.startsWith("event")) {
                    try {
                        if (input.length() <= 6) {
                            throw new ConverSageException("The event task requires a description.");
                        }
                        if (!input.contains(" /from ") || !input.contains(" /to ")) {
                            throw new ConverSageException("The event task requires a start time and an end time");
                        }
                        String[] eventTaskParts = input.substring(6).split(" /from | /to ");
                        String taskDesc = eventTaskParts[0];
                        String from = eventTaskParts[1];
                        String to = eventTaskParts[2];
                        Task newTask = new Event(taskDesc, from, to);
                        taskList.add(newTask);

                        System.out.println("Understood, I've added this task: ");
                        System.out.println("  " + newTask);
                        System.out.println("You have " + taskList.size() + " tasks in your list" );
                    } catch (ConverSageException e) {
                        System.out.println("ERR: " + e.getMessage());
                    }

                    System.out.println(horizontalLine);
                } else {
                    throw new ConverSageException("Invalid command, please try again");
                }
            } catch (ConverSageException e) {
                System.out.println("ERR: " + e.getMessage());
                System.out.println(horizontalLine);
            }

        }
    }
}
