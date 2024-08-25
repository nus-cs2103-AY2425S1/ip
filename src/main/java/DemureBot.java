import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class DemureBot {
    private static void check(String command, ArrayList<Task> list) throws DemureBotException {
        if (command.startsWith("mark")) {
            String remainder = command.substring(4).trim();
            try {
                int index = Integer.parseInt(remainder) - 1;
                Task task = list.get(index);
                task.markAsDone();
                System.out.println("____________________________________________________________\n" +
                    " Nice! I've marked this task as done:\n   " +
                    task + "\n" +
                    "____________________________________________________________\n" +
                    "\n"
                );
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive integer after mark.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid index: " +
                    (Integer.parseInt(remainder)) +
                    "\n" +
                    "Please enter a number within 1 to number of current tasks.\n"
                );
            }
        } else if (command.startsWith("unmark")) {
            String remainder = command.substring(6).trim();
            try {
                int index = Integer.parseInt(remainder) - 1;
                Task task = list.get(index);
                task.unmark();
                System.out.println("____________________________________________________________\n" +
                    " OK, I've marked this task as not done yet:\n   " +
                    task + "\n" +
                    "____________________________________________________________\n" +
                    "\n"
                );
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive integer after unmark.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid index: " +
                    (Integer.parseInt(remainder)) +
                    "\n" +
                    "Please enter a number within 1 to number of current tasks.\n"
                );
            }
        } else if (command.startsWith("delete")) {
            String remainder = command.substring(6).trim();
            try {
                int index = Integer.parseInt(remainder) - 1;
                Task task = list.get(index);
                list.remove(index);
                System.out.println("____________________________________________________________\n" +
                    " Noted. I've removed this task:\n   " +
                    task + "\n" +
                    "Now you have " + list.size() + " tasks in the list.\n" +
                    "____________________________________________________________\n" +
                    "\n"
                );
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive integer after delete.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid index: " +
                    (Integer.parseInt(remainder)) +
                    "\n" +
                    "Please enter a number within 1 to number of current tasks.\n"
                );
            }
        } else if (command.startsWith("todo")) {
            String description = command.substring(4).trim();
            // check that there is a task description
            if (description.isEmpty()) {
                throw new DemureBotException("The description of a todo cannot be empty.\nAdd description after todo.\n");
            }
            Todo todo = new Todo(description, false);
            list.add(todo);
            System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n  " +
                todo + "\n" +
                "Now you have " + list.size() + " tasks in the list.\n" +
                "____________________________________________________________\n" +
                "\n"
            );
        } else if (command.startsWith("deadline")) {
            String remainder = command.substring(8).trim();
            // check that there is a task description
            if (remainder.isEmpty() || remainder.startsWith("/by")) {
                throw new DemureBotException("The description of a deadline cannot be empty.\nAdd description after deadline.\n");
            }
            String[] splitBy = remainder.split("/by");
            // check that there is a deadline
            if (splitBy.length == 1) {
                throw new DemureBotException("The deadline of a deadline cannot be empty.\nAdd deadline after /by.\n");
            }
            String description = splitBy[0].trim();
            String by = splitBy[1].trim();
            Deadline deadline = new Deadline(description, by, false);
            list.add(deadline);
            System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n  " +
                deadline + "\n" +
                "Now you have " + list.size() + " tasks in the list.\n" +
                "____________________________________________________________\n" +
                "\n"
            );
        } else if (command.startsWith("event")) {
            String remainder = command.substring(5).trim();
            // check that there is a task description
            if (remainder.isEmpty() || remainder.startsWith("/from")) {
                throw new DemureBotException("The description of an event cannot be empty.\nAdd description after event.\n");
            }
            String[] splitFrom = remainder.split("/from");
            // check that there is a start time
            if (splitFrom.length == 1) {
                throw new DemureBotException("The start time of an event cannot be empty.\nAdd start time after /from.\n");
            }
            String description = splitFrom[0].trim();
            String[] splitTo = splitFrom[1].split("/to");
            // check that there is an end time
            if (splitTo.length == 1) {
                throw new DemureBotException("The end time of an event cannot be empty.\nAdd end time after /to.\n");
            }
            String from = splitTo[0].trim();
            String to = splitTo[1].trim();
            Event event = new Event(description, from, to, false);
            list.add(event);
            System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n  " +
                event + "\n" +
                "Now you have " + list.size() + " tasks in the list.\n" +
                "____________________________________________________________\n" +
                "\n"
            );
        } else {
            // throw invalid command exception
            throw new DemureBotException("Invalid command\nCreate a new task starting with the command todo, deadline or event.\n");
        }
    }

    private static void checkFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean isFolderCreated = folder.mkdirs();
            if (!isFolderCreated) {
                System.out.println("Failed to create directory: " + folderPath);
            }
        }
    }

    private static boolean checkFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean isFileCreated = file.createNewFile();
                if (!isFileCreated) {
                    System.out.println("Failed to create file: " + filePath);
                }
                return false;
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        return true;
    }

    private static String formatTask(Task task) {
        String[] parts = task.toString().split("]", 3);
        String type = parts[0].substring(1, 2);
        String status = parts[1].charAt(1) == 'X' ? "1" : "0";
        String description = parts[2].trim();

        if (type.equals("D")) {
            int byIndex = description.indexOf("(by: ");
            String by = description.substring(byIndex + 5, description.length() - 1);
            description = description.substring(0, byIndex - 1);
            return type + " | " + status + " | " + description + " | " + by;
        } else if (type.equals("E")) {
            int fromIndex = description.indexOf("(from: ");
            int toIndex = description.indexOf(" to: ");
            String from = description.substring(fromIndex + 7, toIndex);
            String to = description.substring(toIndex + 5, description.length() - 1);
            description = description.substring(0, fromIndex - 1);
            return type + " | " + status + " | " + description + " | " + from + " | " + to;
        } else {
            return type + " | " + status + " | " + description;
        }
    }

    private static Task getTask(String line) throws DemureBotException {
        String[] split = line.split(" \\| ");
        validateTaskFormat(split, 3);
        String type = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];
        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description, isDone);
            break;
        case "D":
            validateTaskFormat(split, 4);
            String by = split[3];
            task = new Deadline(description, by, isDone);
            break;
        case "E":
            validateTaskFormat(split, 5);
            String from = split[3];
            String to = split[4];
            task = new Event(description, from, to, isDone);
            break;
        }
        if (task == null) {
            throw new DemureBotException("Saved task has invalid format");
        }
        return task;
    }

    private static void validateTaskFormat(String[] split, int expectedLength) throws DemureBotException {
        if (split.length < expectedLength) {
            throw new DemureBotException("Saved task has invalid format");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // check if user ended session
        boolean isFinished = false;
        // list of items to do
        ArrayList<Task> list = new ArrayList<>();

        // introduction to chatbot
        System.out.println("""
            ____________________________________________________________
             Hello! I'm DemureBot
             What can I do for you?
            ____________________________________________________________

            """
        );

        // check if data folder exists if not create it
        String folderPath = "./data";
        checkFolder(folderPath);

        // check if saved data exists if not create it
        String filePath = "./data/tasks.txt";
        boolean savedDataExists = checkFile(filePath);

        // load saved data if exists
        if (savedDataExists) {
            try (Scanner fileScanner = new Scanner(new File(filePath))) {
                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();
                    Task task = getTask(line);
                    list.add(task);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (DemureBotException e) {
                System.out.println("Error loading task: " + e.getMessage());
            }
        }

        // while user hasn't ended session
        while (!isFinished) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                // end session
                isFinished = true;
            } else if (command.equals("list")) {
                // list all tasks
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.println((i + 1) + "." + task);
                }
            } else {
                try {
                    DemureBot.check(command, list);
                } catch (DemureBotException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // close scanner and end session
        scanner.close();
        System.out.println("""
            ____________________________________________________________
             Bye. Hope to see you again soon!
            ____________________________________________________________

            """
        );

        // save the tasks
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            for (Task task : list) {
                String formattedTask = formatTask(task);
                writer.write(formattedTask + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing FileWriter: " + e.getMessage());
                }
            }
        }
    }
}
