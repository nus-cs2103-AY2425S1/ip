// use Scanner class to get user inputs
import java.util.Scanner;
import java.util.ArrayList;

public class Bestie {
    public static void main(String[] args) {

        // create Scanner object to read user input
        Scanner sc = new Scanner(System.in);

        // greets the user
        System.out.println("Hello! I'm Bestie, your personal assistant chatbot.");
        System.out.println("Let's get ready to have a productive day!");
        System.out.println("What can I do for you today :)?");

        String userInput;
        ArrayList<Task> tasks = new ArrayList<>();
        // echoes commands entered by the user, and exits when the user types the command "bye"
        while (true) {
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }

            if (userInput.equals("list")) {
                // display the list of all items entered by the user
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++){
                    int index = i + 1;
                    System.out.println(index +"." + tasks.get(i).toString());
                }
            } else if (userInput.startsWith("mark")) {
                // user wants to mark a task as done
                // to get the index, first, split input string into array of substrings
                // first index is "mark", second index is the index of the task to be done
                // use Integer.parseInt to convert from string to integer data type!
                // adjust the off by 1 error
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markTaskDone();
                    System.out.println("Awesome work! I've marked this task as done.");
                    System.out.println("  " + tasks.get(index).toString());
                }


            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(index).toString());
                }
            } else {

                // create a new Task object for that task
                // Task newTask = new Task(userInput);
                Task newTask;
                if (userInput.startsWith("todo")) {
                    String description = userInput.substring(5);
                    newTask = new Todo(description);
                    tasks.add(newTask);
                    System.out.println("added: " + newTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in your list.");
                } else if (userInput.startsWith("deadline")) {
                    String[] parts = userInput.split(" /by ");
                    String description = parts[0].substring(9);
                    String deadline = parts[1];
                    newTask = new Deadline(description, deadline);
                    tasks.add(newTask);
                    System.out.println("added: " + newTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in your list.");
                } else if (userInput.startsWith("event")) {
                    String[] parts = userInput.split("/");
                    String description = parts[0].substring(6).trim();
                    String start = parts[1].substring(5).trim();
                    String end = parts[2].substring(3).trim();
                    newTask = new Event(description, start, end);
                    tasks.add(newTask);
                    System.out.println("added: " + newTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in your list.");
                } else {
                    System.out.println("Invalid input! Please remember to start with \"todo\", \"deadline\" or \"event\".");
                    System.out.println("Double check your spelling for other common commands like \"unmark\" or \"list\".");
                }
            }

        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
