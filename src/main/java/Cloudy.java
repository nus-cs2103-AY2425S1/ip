import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Cloudy {
    public static void main(String[] args) {
        Scanner echo = new Scanner(System.in);
        ArrayList<Task> userList = new ArrayList<Task>();


        // Starting prompt
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Cloudy.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Asking for user input
        String userInput = echo.nextLine();

        // main program loop
        while (true) {
            // if user types 'bye', program will end
            if (Objects.equals(userInput, "bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            // if user types 'list', user can see the tasks
            } else if (Objects.equals(userInput, "list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println((i + 1) + ". " + userList.get(i).printTaskOnList());
                }
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();

            // if user marks task
            } else if (userInput.startsWith("mark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber > 0 && taskNumber < userList.size() + 1) {
                            Task taskToMark = userList.get(taskNumber - 1);
                            taskToMark.markTask();

                            System.out.println("____________________________________________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(taskToMark.printTaskOnList());
                            System.out.println("____________________________________________________________");

                        } else {
                            System.out.println("____________________________________________________________");
                            System.out.println("This task does not exist. Try again.");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please enter a valid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid format.");
                    System.out.println("____________________________________________________________");
                }

                // prompt for input
                userInput = echo.nextLine();


            // if user unmarks task
            } else if (userInput.startsWith("unmark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber > 0 && taskNumber < userList.size() + 1) {
                        Task taskToMark = userList.get(taskNumber - 1);
                        taskToMark.unmarkTask();

                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(taskToMark.printTaskOnList());
                        System.out.println("____________________________________________________________");

                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Invalid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid format.");
                    System.out.println("____________________________________________________________");
                }

                // prompt for input
                userInput = echo.nextLine();

            // user adds Todo task
            } else if (userInput.startsWith("todo")) {
                System.out.println("____________________________________________________________");
                if (userInput.trim().length() <= 4) {
                    System.out.println("No task detected.");
                } else {
                    String taskDescription = userInput.substring(5).trim();
                    Task newTask = new Todo(taskDescription);
                    userList.add(newTask);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.printTaskOnList());
                    System.out.println("Now you have " + userList.size() + " tasks in the list.");
                }
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();

            // user adds Deadline task
            } else if (userInput.startsWith("deadline")) {
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");

                String[] parts = userInput.split("/by");
                String taskDescription = parts[0].substring(9).trim();
                String deadline = parts.length > 0 ? parts[1].trim() : "";
                Task newTask = new Deadline(taskDescription, deadline);
                userList.add(newTask);

                System.out.println(newTask.printTaskOnList());
                System.out.println("Now you have " + userList.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();

            // user adds Event task
            } else if (userInput.startsWith("event")) {
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");

                String[] partsFrom = userInput.split("/from");
                String taskDescription = partsFrom[0].substring(6).trim();
                String startTime = "", endTime = "";
                if (partsFrom.length > 1) {
                    String[] partsTo = partsFrom[1].split("/to");
                    startTime = partsTo[0].trim();
                    if (partsTo.length > 1) {
                        endTime = partsTo[1].trim();
                    }

                }
                Task newTask = new Event(taskDescription, startTime, endTime);
                userList.add(newTask);

                System.out.println(newTask.printTaskOnList());
                System.out.println("Now you have " + userList.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();

            } else if (userInput.startsWith("delete")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber > 0 && taskNumber < userList.size() + 1) {
                            Task taskToDelete = userList.get(taskNumber - 1);

                            System.out.println("____________________________________________________________");
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(taskToDelete.printTaskOnList());
                            userList.remove(taskNumber - 1);
                            System.out.println("Now you have " + userList.size() + " tasks in the list.");
                            System.out.println("____________________________________________________________");

                        } else {
                            System.out.println("____________________________________________________________");
                            System.out.println("This task does not exist. Try again.");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please enter a valid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid format.");
                    System.out.println("____________________________________________________________");
                }
                userInput = echo.nextLine();

            // if user types invalid command
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Invalid command, try again.");
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();
            }
        }

    }
}
