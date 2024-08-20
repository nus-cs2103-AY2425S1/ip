import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;


public class Main {

    public static String findDeadlineDueDate(String[] commandDetails) {
        String deadline = "";
        // first word in commandDetails must be deadline, so start from the i=1 word
        for (int i = 1; i < commandDetails.length; i++) {
            if (commandDetails[i].equals("/by")) {
                deadline = commandDetails[i + 1];
            }
        }
        return deadline;
    }

    public static void main(String[] args) {
        Janet janet = new Janet();
        System.out.println(janet.greet());

        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                // say bye and exit the program
                String exitMessage = janet.exit();
                System.out.println(exitMessage);
                break;
            } else if (command.equals("list")) {
                // show all the tasks inside the list of task
                String currentListOfTasks = janet.showList();
                System.out.println(currentListOfTasks);
            } else {
                String[] commandDetails = command.split(" ");   // an array containing each word of the command
                if (commandDetails[0].equals("mark")) {
                    // mark the task as done
                    String markSuccess = janet.markAsDone(Integer.parseInt(commandDetails[1]));
                    System.out.println(markSuccess);
                } else if (commandDetails[0].equals("unmark")) {
                    // unmark the task
                    String unmarkSuccess = janet.unmark(Integer.parseInt(commandDetails[1]));
                    System.out.println(unmarkSuccess);
                } else {
                    // add the new task into the list of tasks
                    // Task can be a ToDo, Deadline, Event
                    if (commandDetails[0].equals("todo")) {
                        // get the todo description and create a new Todo object
                        String[] todoItem = Arrays.copyOfRange(commandDetails, 1, commandDetails.length);
                        String todoDescription = String.join(" ", todoItem);
                        Task task = new ToDo(todoDescription, "T");
                        String addTaskSuccess = janet.addTaskToList(task);
                        System.out.println(addTaskSuccess);
                    } else if (commandDetails[0].equals("deadline")) {
                        continue;
                    } else if (commandDetails[0].equals("event")) {
                        continue;
                    }
                }
            }
        }
    }
}
