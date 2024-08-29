import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Victor {
    public static Task readTask(String taskFileLine) {
        Task lineTask;
        String[] lineContent = taskFileLine.split(" \\| ");
        if (lineContent[0].equals("T")) {
            lineTask = new ToDo(lineContent[2]);
        } else if (lineContent[0].equals("D")) {
            lineTask = new Deadline(lineContent[2], lineContent[3]);
        } else {
            lineTask = new Event(lineContent[2], lineContent[3], lineContent[4]);
        }

        // Check if task was marked done
        if (Integer.parseInt(lineContent[1]) == 1) {
            lineTask.markDone();
        }

        return lineTask;
    }

    public static ArrayList<Task> readFileContents(Path filePath) {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            Scanner fileScanner = new Scanner(filePath);
            while (fileScanner.hasNextLine()) {
                Task nextTask = readTask(fileScanner.nextLine());
                tasks.add(nextTask);
            }
            return tasks;
        } catch (IOException scannerIOException) {
            throw new RuntimeException(scannerIOException);
        }
    }

    public static void writeToFile(Path filePath, ArrayList<Task> tasks) {
        // Make new file writer to overwrite current contents
        try {
            FileWriter fw = new FileWriter(String.valueOf(filePath));
            for (Task task : tasks) {
                task.writeToFile(fw);
            }
            fw.close();
        } catch (IOException fileWriterIOException) {
            throw new RuntimeException(fileWriterIOException);
        }
    }

    public static void main(String[] args) {
        String logo = ",---.  ,---..-./`)     _______ ,---------.    ,-----.    .-------.\n"
+ "|   /  |   |\\ .-.')   /   __  \\\\          \\ .'  .-,  '.  |  _ _   \\    \n"
+ "|  |   |  .'/ `-' \\  | ,_/  \\__)`--.  ,---'/ ,-.|  \\ _ \\ | ( ' )  |\n"
+ "|  | _ |  |  `-'`\"`,-./  )         |   \\  ;  \\  '_ /  | :|(_ o _) /\n"
+ "|  _( )_  |  .---. \\  '_ '`)       :_ _:  |  _`,/ \\ _/  || (_,_).' __\n"
+ "\\ (_ o._) /  |   |  > (_)  )  __   (_I_)  : (  '\\_/ \\   ;|  |\\ \\  |  |\n"
+ " \\ (_,_) /   |   | (  .  .-'_/  ) (_(=)_)  \\ `\"/  \\  ) / |  | \\ `'   /\n"
+ "  \\     /    |   |  `-'`-'     /   (_I_)    '. \\_/``\".'  |  |  \\    /\n"
+ "   `---`     '---'    `._____.'    '---'      '-----'    ''-'   `'-'\n";
        Scanner inp = new Scanner(System.in);

        // Declare array, reassign if data file exists
        ArrayList<Task> inputs = new ArrayList<Task>();

        // Declare file writer, initialise differently if exists
        FileWriter fw;
        Path dataPath = Paths.get("data");
        Path filePath = Paths.get("data", "data.txt");

        // Check if data directory exists in file structure
        if (Files.exists(dataPath)) {
            // Check if data file exists
            if (Files.exists(filePath)) {
                inputs = readFileContents(filePath);
                try {
                    // Make file writer append to file instead of overwriting
                    fw = new FileWriter(String.valueOf(filePath), true);
                } catch (IOException ioException) {
                    // Handle IO Exception if file is corrupted
                    // Delete file and create new one
                    try {
                        Files.delete(filePath);
                        File dataFile = new File(String.valueOf(filePath));
                        fw = new FileWriter(String.valueOf(filePath));
                    } catch (IOException deleteIOException) {
                        throw new RuntimeException(deleteIOException);
                    }
                }
            } else {
                // File does not exist -> Create data file
                try {
                    File dataFile = new File(String.valueOf(filePath));
                    fw = new FileWriter(String.valueOf(filePath));
                } catch (IOException ioException) {
                    throw new RuntimeException(ioException);
                }
            }
        } else {
            // Directory does not exist
            try {
                // Create directory
                Files.createDirectories(dataPath);
                // Create new file
                File dataFile = new File(String.valueOf(filePath));
                // Make new file writer to the file
                fw = new FileWriter(String.valueOf(filePath));
            } catch (IOException makeDirIOException) {
                throw new RuntimeException(makeDirIOException);
            }
        }

        System.out.println(logo);
        System.out.println("Hello! My name is Victor!");
        System.out.println("What can I do for you?");
        System.out.println("============================================================");
        String userInput = inp.nextLine();

        // Continues asking for input until user input is "bye"
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println("============================================================");

            // Parse user input into separate words
            String[] inputWords = userInput.trim().split(" ");

            // Handles case of empty user input
            if (userInput.trim().isEmpty()) {
                // User input is empty - ask again for input
                System.out.println("  ~  What can I do for you?");
            } else if (inputWords[0].equalsIgnoreCase("list")) {
                if (inputs.isEmpty()) {
                    // No tasks were added to the list yet
                    System.out.println("  ~  No tasks in the list, add some To Dos, Events, and Deadlines first!");
                } else {
                    System.out.println("  ~  Sure! Here are all of your tasks:");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println("  ~  " + (i + 1) + ". " + inputs.get(i));
                    }
                }
            } else if (inputWords[0].equalsIgnoreCase("delete")) {
                try {
                    String[] parsed = userInput.trim().split(" ");
                    int num = Integer.parseInt(parsed[parsed.length - 1]) - 1;

                    // Retrieve and remove deleted task to reference later
                    Task removed = inputs.get(num);
                    inputs.remove(num);

                    // Overwrite file contents with updated list
                    writeToFile(filePath, inputs);

                    System.out.println("  ~  Deleting the task below now!");
                    System.out.println("  ~  " + removed);
                } catch (NumberFormatException e) {
                    System.out.println("  ~  Sorry, I don't think you entered a number for which task to delete!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("  ~  I don't think there's a task with that number!");
                }
            } else if (inputWords[0].equalsIgnoreCase("mark")) {
                try {
                    String[] parsed = userInput.trim().split(" ");
                    int num = Integer.parseInt(parsed[parsed.length - 1]) - 1;
                    inputs.get(num).markDone();

                    // Overwrite file contents with updated list
                    writeToFile(filePath, inputs);

                    System.out.println("  ~  You finished a task! Well done! I marked this task as done:");
                    System.out.println("  ~  " + inputs.get(num));
                } catch (NumberFormatException e) {
                    System.out.println("  ~  Sorry, I don't think you entered a number for which task to mark" +
                            " as done!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("  ~  I don't think there's a task with that number!");
                }
            } else if (inputWords[0].equalsIgnoreCase("unmark")) {
                try {
                    String[] parsed = userInput.trim().split(" ");
                    int num = Integer.parseInt(parsed[parsed.length - 1]) - 1;
                    inputs.get(num).markUndone();

                    // Overwrite file contents with updated list
                    writeToFile(filePath, inputs);

                    System.out.println("  ~  Oops, I guess you didn't finish the task! I marked this task as undone:");
                    System.out.println("  ~  " + inputs.get(num));
                } catch (NumberFormatException e) {
                    System.out.println("  ~  Sorry, I don't think you entered a number for which task to " +
                            "mark as not done!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("  ~  I don't think there's a task with that number!");
                }
            } else if (inputWords[0].equalsIgnoreCase("todo")) {
                String[] parsed = userInput.trim().split(" ");
                String taskName = "";

                for (int i = 1; i < parsed.length; i++) {
                    taskName += " " + parsed[i];
                }

                // Trim so that blank space cannot be counted as name for task
                taskName = taskName.trim();

                if (taskName.isEmpty()) {
                    System.out.println("  ~  Please give a name for the To Do. The format should be \"todo " +
                            "(description)\"");
                } else {
                    ToDo task = new ToDo(taskName);
                    inputs.add(task);

                    // Write todo to file
                    try {
                        FileWriter addWriter = new FileWriter(String.valueOf(filePath), true);
                        task.writeToFile(addWriter);
                        addWriter.close();
                    } catch (IOException writeIOException) {
                        throw new RuntimeException(writeIOException);
                    }

                    System.out.println("  ~  Cool! I added this To Do:");
                    System.out.println("  ~    " + task);
                    System.out.println("  ~  You now have " + inputs.size() +
                            ((inputs.size() == 1) ? " task" : " tasks") + " in your list.");
                }
            } else if (inputWords[0].equalsIgnoreCase("deadline")) {
                String[] parsed = userInput.trim().split(" ");
                String taskName = "";
                String deadline = "";

                boolean isDeadline = false;

                for (int i = 1; i < parsed.length; i++) {
                    if (parsed[i].startsWith("/")) {
                        isDeadline = true;
                        continue;
                    }
                    if (!isDeadline) {
                        taskName += " " + parsed[i];
                    } else {
                        deadline += " " + parsed[i];
                    }
                }
                // Trim so that blank space cannot be counted as name for task or deadlines
                taskName = taskName.trim();
                deadline = deadline.trim();

                if (taskName.isEmpty()) {
                    System.out.println("  ~  Please give a name for the Tasks.Deadline. The format should be \"deadline" +
                            " (description) /by (deadline, in format yyyy-mm-dd or dd-mm-yyyy)\"");
                } else if (deadline.isEmpty()) {
                    System.out.println("  ~  Please give a deadline for the Tasks.Deadline. The format should be \"deadline" +
                            " (description) /by (deadline, in format yyyy-mm-dd or dd-mm-yyyy)\"");
                } else {
                    try {
                        Deadline task = new Deadline(taskName, deadline);
                        inputs.add(task);

                        // Write deadline to file
                        try {
                            FileWriter addWriter = new FileWriter(String.valueOf(filePath), true);
                            task.writeToFile(addWriter);
                            addWriter.close();
                        } catch (IOException writeIOException) {
                            throw new RuntimeException(writeIOException);
                        }

                        System.out.println("  ~  Splendid! I added this Tasks.Deadline:");
                        System.out.println("  ~    " + task);
                        System.out.println("  ~  You now have " + inputs.size() +
                                ((inputs.size() == 1) ? " task" : " tasks") + " in your list.");
                    } catch (DateTimeParseException parseException) {
                        System.out.println("  ~  Please format deadline as " + "\"deadline" +
                                " (description) /by (deadline, in format yyyy-mm-dd or dd-mm-yyyy)\"");
                    }
                }
            } else if (inputWords[0].equalsIgnoreCase("event")) {
                String[] parsed = userInput.trim().split(" ");
                String taskName = "";
                String start = "";
                String end = "";

                boolean isStart = false;
                boolean isEnd = false;

                for (int i = 1; i < parsed.length; i++) {
                    if (parsed[i].startsWith("/")) {
                        if (isStart) {
                            isEnd = true;
                        } else {
                            isStart = true;
                        }
                        continue;
                    }
                    if (!isStart) {
                        taskName += " " + parsed[i];
                    } else if (!isEnd) {
                        start += " " + parsed[i];
                    } else {
                        end += "" + parsed[i];
                    }
                }
                // Trim so that blank space cannot be counted as name for task, start or end
                taskName = taskName.trim();
                start = start.trim();
                end = end.trim();

                if (taskName.isEmpty()) {
                    System.out.println("  ~  Please give a name for the Tasks.Event. The format should be \"event" +
                            " (description) /from (start, in format yyyy-mm-dd or dd-mm-yyyy) /to" +
                            " (end, in format yyyy-mm-dd or dd-mm-yyyy)\"");
                } else if (start.isEmpty()) {
                    System.out.println("  ~  Please give a start time for the Tasks.Event. The format should be \"event" +
                            " (description) /from (start, in format yyyy-mm-dd or dd-mm-yyyy) /to" +
                            " (end, in format yyyy-mm-dd or dd-mm-yyyy)\"");
                } else if (end.isEmpty()) {
                    System.out.println("  ~  Please give an end time for the Tasks.Event. The format should be \"event" +
                            " (description) /from (start, in format yyyy-mm-dd or dd-mm-yyyy) /to" +
                            " (end, in format yyyy-mm-dd or dd-mm-yyyy)\"");
                } else {
                    try {
                        Event task = new Event(taskName, start, end);
                        inputs.add(task);

                        // Write event to file
                        try {
                            FileWriter addWriter = new FileWriter(String.valueOf(filePath), true);
                            task.writeToFile(addWriter);
                            addWriter.close();
                        } catch (IOException writeIOException) {
                            throw new RuntimeException(writeIOException);
                        }

                        System.out.println("  ~  Wonderful! I added this Tasks.Event:");
                        System.out.println("  ~    " + task);
                        System.out.println("  ~  You now have " + inputs.size() +
                                ((inputs.size() == 1) ? " task" : " tasks") + " in your list.");
                    } catch (DateTimeParseException parseException) {
                        System.out.println("  ~  Please format the event as \"event" +
                                " (description) /from (start, in format yyyy-mm-dd or dd-mm-yyyy) /to" +
                                " (end, in format yyyy-mm-dd or dd-mm-yyyy)\"");
                    } catch (DateTimeException dateException) {
                        System.out.println("  ~  Please ensure end time is later than start time!");
                        System.out.println("  ~  Format the event as \"event (description) /from" +
                                " (start, in format yyyy-mm-dd or dd-mm-yyyy) /to" +
                                " (end, in format yyyy-mm-dd or dd-mm-yyyy)\"");
                    }
                }
            } else {
                // User input does not match any specified command
                System.out.println("  ~  Sorry, that's not something I know how to do :( Please specify either a " +
                        "To Do, a Tasks.Deadline or an Tasks.Event!");
            }
            System.out.println("============================================================");
            userInput = inp.nextLine();
        }

        // Write final list of tasks to file
        writeToFile(filePath, inputs);

        // Closing file writer
        try {
            fw.close();
        } catch (IOException closeException) {
            throw new RuntimeException(closeException);
        }

        System.out.println("============================================================");
        System.out.println("Goodbye! Hope to see you again soon!");
        System.out.println("============================================================");
    }
}
