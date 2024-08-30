package donk;

import donk.task.*;

import java.io.File;
import java.io.IOException;

public class Parser {

    /**
     * Checks if the given string is a valid integer.
     *
     * @param s The string to be checked.
     * @return true if the string can be parsed as an integer, otherwise false.
     */
    public static boolean validNum(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parses the user's input command and executes the corresponding action.
     *
     * @param fullCommand The full command string entered by the user.
     * @param tasks The TaskList object containing the current list of tasks.
     * @param storage The Storage object responsible for saving tasks to a file.
     * @param ui The Ui object used to interact with the user.
     * @throws Exception If the command is not recognized or if there are issues processing the command.
     *
     * Supported commands include:
     * - "bye": Saves the current tasks to a file and exits the program.
     * - "list": Displays all tasks.
     * - "mark <index>": Marks the task at the given index as done.
     * - "unmark <index>": Unmarks the task at the given index as not done.
     * - "delete <index>": Deletes the task at the given index.
     * - "todo <description>": Adds a new todo task with the given description.
     * - "deadline <description> /by <date>": Adds a new deadline task with the given description and due date.
     * - "event <description> /start <start> /end <end>": Adds a new event task with the given description, start time, and end time.
     */
    public static void parse (String fullCommand, TaskList tasks, Storage storage, Ui ui) throws Exception {
        String[] inputArray = fullCommand.split("\\s+");
        String command = inputArray[0];
        if (fullCommand.isBlank()) {
            return;
        } else if (fullCommand.equals("bye")) {
            String filePath = "./save.txt";
            File file = new File(filePath);
            if (!file.exists()) {
                try {
                    // Attempt to create the file
                    if (file.createNewFile()) {
                        System.out.println("Save file created successfully.");
                    } else {
                        System.out.println("Failed to create the file.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating the file: " + e.getMessage());
                    e.printStackTrace();
                    return;
                }
            }
            storage.writeToFile("./save.txt", tasks);
            ui.bye();
            return;
        } else if (fullCommand.equals("list")) {
            ui.listTasks(tasks);
        } else if (inputArray[0].equals("mark") && inputArray[1].matches("\\d+")) {
            int index = Integer.parseInt(inputArray[1]) - 1;
            Task temp = tasks.getTask(index);
            temp.markDone();
            ui.markedDone(temp);
        } else if (inputArray[0].equals("unmark") && inputArray[1].matches("\\d+")) {
            int index = Integer.parseInt(inputArray[1]) - 1;
            Task temp = tasks.getTask(index);
            temp.unmarkDone();
            ui.unmarkedDone(temp);
        } else if (command.equals("delete")) {
            if (inputArray.length < 2) {
                throw new IllegalArgumentException ("Please provide the index of the task to delete");
            }
            if (!Parser.validNum(inputArray[1])) {
                throw new IllegalArgumentException("Please provide a valid index");
            }
            int index = Integer.parseInt(inputArray[1]) - 1;
            tasks.remove(index);
            ui.delete(tasks.getTask(index), tasks.size());
        }else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {

            Task t;
            if (inputArray[0].equals("todo")) {
                if (inputArray.length < 2 || fullCommand.length() < 5) {
                    throw new TodoException("Bruh todo task cannot be empty.\nExample:\n    todo homework");
                }
                t = new ToDo(fullCommand.substring(5));

                tasks.add(t);
            } else if (inputArray[0].equals("deadline")) {
                String[] split = fullCommand.split("/by");
                if (split.length < 2) {
                    ui.invalidFormat(TaskType.DEADLINE);
                    return;
                }
                String bef = split[0].substring(9);
                String aft = split[1];
                t = new Deadline(bef, aft.strip());
                tasks.add(t);

            } else {
                String[] split1 = fullCommand.split("/start");
                if (split1.length < 2) {

                    return;
                }
                String[] split2 = split1[1].split("/end");
                if (split1.length < 2 || split2.length < 2) {
                    ui.invalidFormat(TaskType.EVENT);
                    return;
                }
                String start = split2[0];
                String end = split2[1];
                String description = split1[0].substring(6);
                t = new Event(description, start.strip(), end.strip());
                tasks.add(t);

            }
            System.out.println("    Got it. I've added this task:");
            System.out.println("        " + t.toString());
            System.out.println("    You now have " + tasks.size() + " tasks");
        } else if(command.equals("find")) {
            String searchTerm = fullCommand.substring(5);
            TaskList results = tasks.find(searchTerm);
            ui.listTasks(results);
        } else {
            throw new Exception("Ehhh not sure what this is man");
        }
    }

}
