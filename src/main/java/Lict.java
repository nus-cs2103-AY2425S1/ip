import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Lict {
    private final static String NAME = "Lict";
    private final static String HORIZONTAL_LINE = "__________________________________";
    /**
     * The list of current tasks added.
     */
    private final static ArrayList<Task> tasks = new ArrayList<>();
    /**
     * An enum of available actions for handling tasks.
     */
    enum Action {
        MARK,
        UNMARK,
        DELETE,
    }
    /**
     * A enum of valid task types.
     */
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }

    /**
     * Loads the task data from a file named "LictData.txt" located in the "data" directory.
     * If the file or directory does not exist, it will be created.
     */
    private static void loadData() {
        File file = new File("data/LictData.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String dataEntry = sc.nextLine();
                String[] dataParts = dataEntry.split("\\|", 3);
                if (dataParts.length < 3) {
                    continue;
                }
                TaskType taskType = TaskType.valueOf(dataParts[0].trim().toUpperCase());
                boolean isDone = dataParts[1].trim().equals("1");
                String description = dataParts[2].trim();

                Task task;
                String[] messages;
                switch (taskType) {
                case TODO:
                    task = new Todo(description);
                    break;

                case DEADLINE:
                    messages = description.split("\\|", 2);
                    if (messages.length != 2) {
                        //Invalid format, discard data entry
                        continue;
                    }
                    try {
                        task = new Deadline(messages[0].trim(), messages[1].trim());
                    } catch (LictException e) {
                        //Invalid deadline, discard data entry
                        continue;
                    }
                    break;

                case EVENT:
                    messages = description.split("\\|", 3);
                    if (messages.length != 3) {
                        //Invalid format, discard data entry
                        continue;
                    }
                    try {
                        task = new Event(messages[0].trim(), messages[1].trim(), messages[2].trim());
                    } catch (LictException e) {
                        //Invalid event start or end, discard data entry
                        continue;
                    }
                    break;

                default:
                    // Discard data and move on to next iteration
                    continue;

                }
                task.changeStatus(isDone);
                tasks.add(task);

            }
            sc.close();
        } catch (FileNotFoundException fileException) {
            try {
                //Creating directory or file
                File directory = new File("data");
                directory.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the data directory or the LictData.txt file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves the current list of tasks to a file named "LictData.txt" located in the "data" directory.
     */
    private static void saveData() {
        try {
            FileWriter writer = new FileWriter("data/LictData.txt");

            for (Task t : tasks) {
                writer.write(t.toData());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    /**
     * Handles marking, unmarking, and deleting tasks based on the user input.
     * Valid commands are "mark {task number}", "unmark {task number}", and "delete {task number}".
     *
     * @param input The input command provided by the user.
     * @return true if the input was successfully evaluated as a marking action, false otherwise.
     */

    private static boolean handleMarkings(String input) {
        String[] inputParts = input.split(" ");
        Action action;
        try {
            action = Action.valueOf(inputParts[0].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        if (inputParts.length != 2) {
            System.out.println("Please ensure that your input is in the form of: " +action +" {task number}. For eg. " + String.format("'%s 1'", action));
            return true;
        }
        try {
            int index = Integer.parseInt(inputParts[1].trim()) - 1;
            if (index < 0) {
                System.out.println("Invalid task number. Task numbers should all be positive.");
            } else if (index >= tasks.size()) {
                System.out.println("Invalid task number. There are only " + tasks.size() + " tasks in the list.");
            } else {
                Task t = tasks.get(index);

                switch (action) {
                case MARK:
                    t.changeStatus(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    " + t);
                    break;
                case UNMARK:
                    t.changeStatus(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("    " + t);
                    break;
                case DELETE:
                    tasks.remove(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("    " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                default:
                    //Fallthrough
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer index. For eg. " + String.format("'%s 1'", action));
        }
        return true;
    }

    /**
     * Creates a task based on the user input. It supports three types of tasks: todo, deadline, and event.
     *
     * @param input The input command provided by the user.
     * @return The created Task object.
     * @throws LictException If the input is an invalid task or does not follow the required format for each task type.
     */
    public static Task makeTask(String input) throws LictException {
        String[] inputParts = input.split(" ", 2);
        String description;
        if (inputParts.length == 1) {
            description = "";
        } else {
            description = inputParts[1].trim();
        }
        TaskType type;
        try {
            type = TaskType.valueOf(inputParts[0].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new LictException("OOPS!!! I'm sorry, but I don't know what that means. Please only input tasks which start with these words: " + Arrays.toString(TaskType.values()));
        }
        String[] messages;

        switch (type) {
        case TODO:
            if (description.isEmpty()) {
                throw new LictException("OOPS!!! The description of a todo cannot be empty. Please ensure that your input is in the format: todo {description}");
            }
            return new Todo(description);
        case DEADLINE:
            if (description.isEmpty()) {
                throw new LictException("OOPS!!! The description of a deadline cannot be empty. Please ensure that your input is in the format: deadline {description} /by {your deadline}");
            }
            messages = description.split("/by", 2);
            if (messages.length != 2) {
                throw new LictException("OOPS!!! The deadline needs to be indicated. Please ensure that your input is in the format: deadline {description} /by {your deadline}");
            }
            return new Deadline(messages[0].trim(), messages[1].trim());
        case EVENT:
            if (description.isEmpty()) {
                throw new LictException("OOPS!!! The description of an event cannot be empty. Please ensure that your input is in the format: event {description} /from {start} /to {end}");
            }
            messages = description.split("/from", 2);
            if (messages.length != 2) {
                throw new LictException("OOPS!!! The start of the event needs to be indicated. Please ensure that your input is in the format: event {description} /from {start} /to {end}");
            }
            String eventDescription = messages[0].trim();
            String start = messages[1].trim();
            String[] tmp = start.split("/to", 2);
            if (tmp.length != 2) {
                throw new LictException("OOPS!!! The end of the event needs to be indicated. Please ensure that your input is in the format: event {description} /from {start} /to {end}");
            }
            start = tmp[0].trim();
            return new Event(eventDescription, start, tmp[1].trim());
        default:
            //FallThrough
        }
        return null;
    }

    public static void main(String[] args) {
        loadData();
        Scanner sc = new Scanner(System.in);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm "+ NAME);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        String input = sc.nextLine().trim();
        while (!input.equals("bye")) {
            System.out.println(HORIZONTAL_LINE);
            if (input.trim().isEmpty()) {
                System.out.println("Please enter a valid command.");
                input = sc.nextLine();
                continue;
            }
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int counter = 1;
                for (Task task : tasks) {
                    System.out.println(counter + "." + task);
                    counter+=1;
                }
                System.out.println(HORIZONTAL_LINE);
                input = sc.nextLine();
                continue;
            }
            boolean evaluated = handleMarkings(input);

            if (evaluated) {
                saveData();
            } else {
                try {
                    Task newTask = makeTask(input);
                    tasks.add(newTask);
                    saveData();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + newTask);
                    System.out.println("Now you have " + tasks.size() + " in the list.");
                } catch (LictException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(HORIZONTAL_LINE);
            input = sc.nextLine();
        }
        sc.close();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
