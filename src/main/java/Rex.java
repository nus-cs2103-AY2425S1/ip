import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rex {
    // File path of task list and temp list
    private static String filepath = "./data/duke.txt";
    private static String temppath = "./data/tmp.txt";

    // Horizontal line separation
    private static String separation = "____________________________________________________________";

    // "rawr" string added to end of each print statement
    private static String rawr = "rawr";

    // "RAWRRRR" that comes with each error message
    private static String errorPrefix = "RAWRRRR!!!";

    public static void main(String[] args) {
        // ArrayList to store added tasks
        ArrayList<Task> list = new ArrayList<>();
        
        try {
            loadFile(list);
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            return;
        } catch (InvalidTaskException e) {
            return;
        }
        // Rex's greeting message
        System.out.println(separation);
        System.out.println("Hello! I'm Rex! " + rawr);
        System.out.println("What can I do for you? " + rawr);

        // Initialize scanner to take in user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(separation);
            // Takes in user input and extract command
            String[] input = scanner.nextLine().split(" ", 2);

            Command command;
            try {
                command = Command.inputToCommand(input[0]);
            } catch (InvalidInputException e) {
                System.out.println(separation);
                System.out.println(errorPrefix + " " + e.getMessage());
                System.out.println("Enter \"help\" for a list of what I do know! " + rawr);
                continue;
            }

            // Process user command
            try {
                switch (command) {
                    // List all valid commands
                    case HELP:
                        // help should be used alone
                        if (input.length > 1) {
                            throw new InvalidInputException(errorPrefix + " Too many arguments!");
                        }

                        System.out.println(Command.listCommands());
                        break;
                    // Adding a task to list
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        // Argument cannot be empty
                        if (input.length <= 1) {
                            throw new InvalidInputException(errorPrefix + " Task description cannot be empty!");
                        }

                        // Create task and add to list
                        String argument = input[1];
                        Task newTask = createTask(list, command, argument);
                        System.out.println(separation);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask);
                        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");

                        updateFile(list);
                        break;
                    // Display items added as a numbered list
                    case LIST:
                        // list should be used alone
                        if (input.length > 1) {
                            throw new InvalidInputException(errorPrefix + " Too MANY arguments!");
                        }

                        displayList(list);
                        break;
                    // Mark/unmark task in specified index as done
                    case MARK:
                    case UNMARK:
                    case DELETE:
                        // Must have a number argument
                        if (input.length <= 1) {
                            throw new InvalidInputException(errorPrefix + " Too FEW arguments!");
                        }

                        // Get task from index
                        System.out.println(separation);
                        argument = input[1];
                        int index = Integer.parseInt(argument);
                        Task actionTask = list.get(index - 1);

                        // Mark/unmark task according to command
                        if (command.equals(Command.MARK)) {
                            actionTask.markDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + actionTask);
                        } else if (command.equals(Command.UNMARK)) {
                            actionTask.unmarkDone();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + actionTask);
                        } else {
                            // Delete task from list
                            deleteTask(list, actionTask);
                        }

                        updateFile(list);
                        break;
                    case RAWR:
                        System.out.println(separation);
                        System.out.println(rawr + "!");
                        break;
                    // Exit program
                    case BYE:
                        // bye should be used alone
                        if (input.length > 1) {
                            throw new InvalidInputException(errorPrefix + " Too many arguments!");
                        }

                        // Rex's goodbye message
                        System.out.println(separation);
                        System.out.println("Bye. Hope to see you again soon! " + rawr);
                        System.out.println(separation);

                        // Close scanner to avoid memory leaks
                        scanner.close();
                        return;
                }
            } catch (InvalidInputException e) {
                System.out.println(separation);
                System.out.println(e.getMessage());
                System.out.println("Usage: " + Command.usageMessage(command));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(errorPrefix + " Invalid task number!");
                System.out.println("Usage: " + Command.usageMessage(command));
            } catch (InvalidTaskException e) {
                System.out.println("Usage: " + Command.usageMessage(command));
            } catch (IOException e) {
                System.out.println("An error has occurred.");
            }
        }
    }

    private static Task createTask(List<Task> list, Command command, String argument)
            throws InvalidTaskException, IOException {
        Task newTask;
        try {
            if (command.equals(Command.TODO)) {
                // Create new ToDo task
                newTask = new ToDo(argument);
            } else if (command.equals(Command.DEADLINE)) {
                // Create new Deadline task
                newTask = createDeadline(argument);
            } else {
                // Create new Event task
                newTask = createEvent(argument);
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            throw new InvalidTaskException();
        }

        // Add task to list
        list.add(newTask);

        return newTask;
    }

    private static Deadline createDeadline(String argument) throws InvalidInputException {
        String[] descriptionBy = argument.split("/by", 2);
        if (descriptionBy.length < 2) {
            System.out.println(separation);
            throw new InvalidInputException(errorPrefix + " /by not found in input!");
        }

        return new Deadline(descriptionBy[0], descriptionBy[1]);
    }

    private static Event createEvent(String argument) throws InvalidInputException {
        String[] tokens = argument.split("/from", 2);
        if (tokens.length < 2) {
            System.out.println(separation);
            throw new InvalidInputException(errorPrefix + " /from not found in input!");
        }

        String description = tokens[0];
        String[] fromTo = tokens[1].split("/to", 2);

        if (fromTo.length < 2) {
            System.out.println(separation);
            throw new InvalidInputException(errorPrefix + " /to not found in input!");
        }

        return new Event(description, fromTo[0], fromTo[1]);
    }

    private static void displayList(List<Task> list) {
        System.out.println(separation);

        // Print statement for an empty list
        if (list.size() == 0) {
            System.out.println("The list is empty! " + rawr);
            return;
        }

        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + "." + list.get(i - 1));
        }
    }

    private static void deleteTask(List<Task> list, Task actionTask) {
        // Remove task from list and decrease number
        list.remove(actionTask);
        Task.removeTask();

        System.out.println("Noted. I've removed this task:");
        System.out.println(actionTask);
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
    }

    private static void loadFile(List<Task> list) 
            throws IOException, InvalidTaskException {
        // Create new file and directory for filepath
        File f = new File(filepath);
        File dir = f.getParentFile();

        // Create new directory if does not exist
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        // Create new file if does not exist
        if (!f.exists()) {
            f.createNewFile();
        }

        // Initialize reader to read from save file
        FileReader r = new FileReader(f);
        BufferedReader reader = new BufferedReader(r);
        String currentLine;

        // Iterate through each line in save file
        while ((currentLine = reader.readLine()) != null) {
            String[] taskStrings = currentLine.split(" \\| ");
            String taskType = taskStrings[0];
            int isMarked = Integer.parseInt(taskStrings[1]);

            // Add to task list
            Task newTask = null;
            String argument = taskStrings[2];
            switch (taskType) {
            case "T":
                newTask = createTask(list, Command.TODO, argument);
                break;
            case "D":
                argument += " /by " + taskStrings[3];
                newTask = createTask(list, Command.DEADLINE, argument);
                break;
            case "E":
                argument += " /from " + taskStrings[3] + " /to " + taskStrings[4];
                newTask = createTask(list, Command.EVENT, argument);
                break;
            }
            
            if (isMarked == 1) {
                newTask.markDone();
            }
        }

        // Close readers
        reader.close();
        r.close();

    }

    private static void updateFile(ArrayList<Task> list) throws IOException {
        File f = new File(filepath);
        File tmp = new File(temppath);

        // Create temp file to copy taskList from
        Files.deleteIfExists(Paths.get(temppath));
        tmp.createNewFile();

        FileWriter writer = new FileWriter(tmp, true);

        // Create new file with updated list
        for (int i = 1; i <= list.size(); i++) {
            Task task = list.get(i - 1);
            writer.write(task.formatted() + System.lineSeparator());
        }

        // Close writer
        writer.close();

        // Copy to save file
        Files.copy(Paths.get(temppath), Paths.get(filepath), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(temppath));
    }
}
