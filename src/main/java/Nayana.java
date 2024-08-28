import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
/**
 * Nayana is a class that demonstrates a simple console-based interaction.
 * It prints an ASCII logo and several lines of text to the console.
 */
public class Nayana {

    /**
     * The main method is the entry point of the application.
     * It prints a logo and a series of messages to the console.
     * and continues to process user input until the user types "bye"
     * Commands include listing tasks or adding new tasks.
     * The tasks can be marked as done or not done
     * tasks can be deleted
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String pathName = "data/nayana.txt";
        File taskListFile = new File(pathName);
        try {
            Scanner scannerFile= new Scanner(taskListFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found." +
                  "Please ensure that the file nayana.txt is saved under the directory data/nayana.txt");
            System.exit(0);
        }

        String logo =
              "\n" +
                    " ___ .-.     .---.   ___  ___    .---.   ___ .-.     .---.\n" +
                    "(   )   \\   / .-, \\ (   )(   )  / .-, \\ (   )   \\   / .-,\\\n" +
                    " |  .-. .  (__) ; |  | |  | |  (__) ; |  |  .-. .  (__) ; |\n" +
                    " | |  | |    .'  |  | |  | |   .'  |  |  |  | |    .'  |\n" +
                    " | |  | |   / .'| |  | '  | |  / .'|  |  | |  | |   / .'| |\n" +
                    " | |  | |  | /  | |  '  -' |  | /  |  |  | |  | |  | /  | |\n" +
                    " | |  | |  ; |  ; |   .__. |  ; |  ;  |  | |  | |  ; |  ; |\n" +
                    " | |  | |  ' -'  |   ___ | |  ' -'    |  | |  | |  ' -'  |\n" +
                    "(___)(___) .__.'_.  (   )' |  .__.'_.   (___)(___) .__.'_.\n" +
                    "                      ; -' '\n" +
                    "                       .__.'";

        System.out.println("Hello from" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Nayana");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        TaskList taskList = new TaskList(taskListFile, pathName);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            try {
                command = checkCommand(command, taskList.getSize(), scanner);
            } catch (NayanaException e) {
                command = "-1";
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
            try {
                if (command.equals("bye")) {
                    System.out.print("Bye!!! Hope to help you again soon!\n");
                    System.out.print("____________________________________________________________\n");
                    break;
                } else if (command.equals("list")) {
                    System.out.print(taskList);
                    System.out.print("____________________________________________________________\n");
                } else if (command.startsWith("mark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    taskList.markAsDone(index);
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    taskList.markAsNotDone(index);
                } else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    taskList.delete(index);
                } else if (command.startsWith("deadline")) {
                    String[] parts = command.split(" /by ");
                    String description = parts[0].substring(8).trim();
                    String deadline = parts[1].trim();
                    LocalDate d1 = LocalDate.parse(deadline);
                    Deadlines nextTask = new Deadlines(description, d1);
                    taskList.addTask(nextTask);
                } else if (command.startsWith("event")) {
                    String[] fromParts = command.split(" /from ");
                    String description = fromParts[0].substring(5).trim();
                    String[] toParts = fromParts[1].split(" /to ");
                    String startTime = toParts[0].trim();
                    LocalDate d1 = LocalDate.parse(startTime);
                    String endTime = toParts[1].trim();
                    LocalDate d2 = LocalDate.parse(endTime);

                    Event nextTask = new Event(description,d1,d2);
                    taskList.addTask(nextTask);
                } else if (command.startsWith("todo")) {
                    String[] parts = command.split("todo ");
                    String description = parts[1].trim();
                    ToDos nextTask = new ToDos(description);
                    taskList.addTask(nextTask);
                } else if (command.equals("-1")) {
                    // Do nothing on error to prevent trailing newlines
                }
            } catch (IOException e) {
                System.out.println("Error when writing to the file. Try again");
            }
        }
        scanner.close();
    }


    /**
     * Validates and processes the command entered by the user.
     * It checks the format of the command and ensures that it meets the expected criteria.
     * If the command is invalid, a NayanaException is thrown with an appropriate error message.
     *
     * @param command The command entered by the user.
     * @param size The current number of tasks in the TaskList.
     * @param scanner The scanner used to read user input.
     * @return The validated command to be executed.
     * @throws NayanaException If the command is invalid or if required fields are missing.
     */
    private static String checkCommand(String command, int size, Scanner scanner) throws NayanaException {
        if (command.isBlank()) {
            throw new NayanaException("tasks cannot be empty :(");
        } else if (command.equals("bye") || command.equals("list")) {
            return command;
        } else if (command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            if (index < 0 || index >= size) {
                throw new NayanaException("index for mark/unmark/delete is out of range :(");
            }
            return command;
        } else if (command.startsWith("deadline")) {
            String[] parts = command.split(" /by ");
            if (parts.length != 2) {
                throw new NayanaException("invalid format for deadline");
            } else if (parts[0].substring(8).trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new NayanaException("description and deadline cannot be empty.");
            } else {
                String deadline = parts[1].trim();
                if (!validDateFormat(deadline)) {
                    System.out.println(deadline);
                    throw new NayanaException("invalid date format");
                }
            }
            return command;
        } else if (command.startsWith("event")) {
            String[] fromParts = command.split(" /from ");
            if (fromParts.length != 2) {
                throw new NayanaException("invalid format for event");
            } else {
                String[] toParts = fromParts[1].split(" /to ");
                if (toParts.length != 2) {
                    throw new NayanaException("invalid format for event");
                } else if (fromParts[0].substring(5).trim().isEmpty() ||
                      toParts[0].trim().isEmpty() || toParts[1].trim().isEmpty()) {
                    throw new NayanaException("description, start time and end time cannot be empty.");
                } else {
                    String startTime = toParts[0].trim();
                    LocalDate d1 = LocalDate.parse(startTime);
                    String endTime = toParts[1].trim();
                    LocalDate d2 = LocalDate.parse(endTime);
                    if (!validDateFormat(startTime) || !validDateFormat(endTime)) {
                        throw new NayanaException("invalid date format");
                    }
                }
            }
            return command;
        } else if (command.startsWith("todo")) {
            String[] parts = command.split("todo ");
            if (parts.length != 2) {
                throw new NayanaException("invalid format for todo");
            } else if (parts[1].trim().isEmpty()) {
                throw new NayanaException("description cannot be empty.");
            }
            return command;
        } else {
            throw new NayanaException("Please use the correct format and start your prompts \nwith " +
                  "deadline, event, todo, mark, unmark, delete, list, bye ");
        }
    }

    private static boolean validDateFormat(String deadline) {
        try {
            LocalDate d1 = LocalDate.parse(deadline);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
