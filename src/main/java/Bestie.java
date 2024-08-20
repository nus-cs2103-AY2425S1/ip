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
                    System.out.println(index +".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription());
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
                    System.out.println("Nice! I've marked this task as done.");
                    System.out.println("  [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getDescription());
                }


            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getDescription());
                }
            }


            else {

                // create a new Task object for that task
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                System.out.println("added: " + userInput);

            }

        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
