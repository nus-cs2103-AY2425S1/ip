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

        // program
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
                        if (taskNumber > 0 && taskNumber < userList.size()) {
                            Task taskToMark = userList.get(taskNumber - 1);
                            taskToMark.markTask();

                            System.out.println("____________________________________________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(taskToMark.printTaskOnList());
                            System.out.println("____________________________________________________________");

                        } else {
                            System.out.println("Invalid task number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task number.");
                    }
                } else {
                    System.out.println("Invalid format.");
                }

                // prompt for input
                userInput = echo.nextLine();


            // if user unmarks task
            } else if (userInput.startsWith("unmark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber > 0 && taskNumber < userList.size()) {
                        Task taskToMark = userList.get(taskNumber - 1);
                        taskToMark.unmarkTask();

                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(taskToMark.printTaskOnList());
                        System.out.println("____________________________________________________________");

                    } else {
                        System.out.println("Invalid task number.");
                    }
                } else {
                    System.out.println("Invalid format.");
                }

                // prompt for input
                userInput = echo.nextLine();
            // if user types in a task, it is added to the list
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                Task newTask = new Task(userInput);
                userList.add(newTask);
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();
            }
        }



    }
}
