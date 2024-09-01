import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.SecurityException;
import java.util.Scanner;
public class Void {
    private static final String FILE_PATH = "./data/void.txt";
    //Tab string FORMAT
    private static final String FORMAT = "\t%s%n";
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } catch (IOException e) {
                System.out.printf(FORMAT, "------------------------------------------------------------------");
                System.out.printf(FORMAT, "Error loading tasks from file: " + e.getMessage());
                System.out.printf(FORMAT, "------------------------------------------------------------------");
            }
        } else {
            try {
                // Check if the data directory exists
                File directory = new File("./data");
                boolean wasDirectoryCreated = true;
                boolean wasFileCreated = true;
                if (!directory.exists()) {
                    wasDirectoryCreated = directory.mkdirs();  // Create the directory if it doesn't exist
                }
                if (wasDirectoryCreated && !file.exists()) { //Checks if directory created but file was not
                    wasFileCreated = file.createNewFile();
                }
                if (wasDirectoryCreated && wasFileCreated) {
                    System.out.printf(FORMAT, "No saved tasks found yet! Task list is empty.\n\tStart adding tasks and track them!");
                    System.out.printf(FORMAT, "------------------------------------------------------------------");
                } else {
                    if (wasDirectoryCreated) {
                        throw new VoidException("Error in creating file!");
                    } else {
                        throw new VoidException("Error in creating directory!");
                    }
                }
            } catch (IOException e) {
                System.out.printf(FORMAT, "------------------------------------------------------------------");
                System.out.printf(FORMAT, "IO error in creating file: " + e.getMessage());
                System.out.printf(FORMAT, "------------------------------------------------------------------");
            } catch (SecurityException s) {
                System.out.printf(FORMAT, "------------------------------------------------------------------");
                System.out.printf(FORMAT, "Security error in creating file or directory: " + s.getMessage());
                System.out.printf(FORMAT, "------------------------------------------------------------------");
            } catch (VoidException v) {
                v.voidExceptionMessage();
            }
        }
    }

    private static void saveTasksToFile() {
        try {
            // Ensure the directory exists
            File directory = new File("./data");
            boolean wasDirectoryCreated = true;
            if (!directory.exists()) {
                wasDirectoryCreated = directory.mkdirs();  // Create the directory if it doesn't exist
            }

            if (wasDirectoryCreated) {
                // Save tasks to file
                BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
                for (Task task : tasks) {
                    bw.write(task.toSaveFormat());
                    bw.newLine();
                }
                bw.close();
            } else {
                throw new VoidException("Error in creating directory!");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        } catch (VoidException v) {
            v.voidExceptionMessage();
        }
    }

    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T":
                return new ToDo(parts[2], Integer.parseInt(parts[1]));
            case "D":
                return new Deadline(parts[2], parts[3], Integer.parseInt(parts[1]));
            case "E":
                return new Event(parts[2], parts[3], parts[4], Integer.parseInt(parts[1]));
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        String logo =
                "### ###   ## ##     ####   ### ##\n" +
                " ##  ##  ##   ##     ##     ##  ##\n" +
                " ##  ##  ##   ##     ##     ##  ##\n" +
                " ##  ##  ##   ##     ##     ##  ##\n" +
                " ### ##  ##   ##     ##     ##  ##\n" +
                "  ###    ##   ##     ##     ##  ##\n" +
                "   ##     ## ##     ####   ### ##\n";

        String[] greetings = {
//                "Hello! I'm your friendly void cat, \n",
//                "Purr... Hello, wanderer. I am \n",
//                "Mew! Welcome human! I'm \n",
                "Greetings from the abyss, friend, for I am\n"
//                ,
//                "Meow! Happy to help, they call me \n"
        };

        String[] assistGreeting = {
//                "How can this void assist you today?",
                "At your service, human."
//                ,
//                "What help does human need today?",
//                "Need any help?",
//                "What can I do for you?"
        };

        // Example of exits
        String[] exits = {
//                "Purr... Until next time, friend.",
                "Meow! I shall vanish into the shadows now."
//                ,
//                "Farewell! May your path be clear.",
//                "Mew! See you in the void again soon.",
//                "The void calls, but I'll return. Goodbye!"
        };

        Scanner scanner = new Scanner(System.in);
        String input;

        // Display a random greeting
        System.out.printf(FORMAT, "------------------------------------------------------------------");
        System.out.printf(FORMAT, greetings[(int) (Math.random() * greetings.length)]);

        System.out.println(logo.indent(4));
        // Display a random assist greeting
        System.out.printf(FORMAT, assistGreeting[(int) (Math.random() * assistGreeting.length)]);
        System.out.printf(FORMAT, "------------------------------------------------------------------");

        // Load tasks from file
        loadTasksFromFile();

        while (true) {
            input = scanner.nextLine();  // Reads user
            String[] splitInput = input.split(" ", 2); //to get the command and argument

            try {
                if (splitInput[0].equalsIgnoreCase("bye")) {
                    System.out.printf(FORMAT, "------------------------------------------------------------------");
                    //Display a random exit
                    System.out.printf(FORMAT, exits[(int) (Math.random() * exits.length)]);
                    break;  // Exit loop when bye
                } else if (splitInput[0].equalsIgnoreCase("list")) {
                    if (tasks.isEmpty()) {
                        throw new VoidException("No tasks found in list yet!");
                    } else {
                        System.out.printf(FORMAT, "------------------------------------------------------------------");
                        System.out.printf(FORMAT, "Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.printf(FORMAT, (i + 1) + ". " + tasks.get(i));  // Lists all task objects
                        }
                        System.out.printf(FORMAT, "------------------------------------------------------------------");
                    }
                } else if (splitInput[0].equalsIgnoreCase("delete")) {
                    if (splitInput.length < 2 || splitInput[1].isBlank()) {
                        throw new VoidException("Hm.. I dont know which to delete! Give me the task number please.");
                    } else {
                        int taskIndex = Integer.parseInt(splitInput[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size()) {
                            throw new VoidException("OOPS!!! The task number provided is invalid.");
                        } else {
                            Task removedTask = tasks.remove(taskIndex);
                            System.out.printf(FORMAT, "------------------------------------------------------------------");
                            System.out.printf(FORMAT, "Noted. I've removed this task:");
                            System.out.printf("\t\t%s%n", removedTask);
                            System.out.printf(FORMAT, "Now you have " + tasks.size() + " tasks in the list.");
                            System.out.printf(FORMAT, "------------------------------------------------------------------");
                        }
                    }
                } else if (splitInput[0].equalsIgnoreCase("mark")) {
                    if (splitInput.length < 2) {
                        throw new VoidException("Hm.. I don't know which to mark! Give me the task number please.");
                    } else {
                        int tasksIndex = Integer.parseInt(splitInput[1]) - 1;
                        if (tasksIndex >= tasks.size() || tasksIndex < 0) { //checks if index is appropriate
                            throw new VoidException("OOPS!!! The task number provided is invalid.");

                        } else {
                            Task t = tasks.get(tasksIndex);
                            t.markAsDone();
                            System.out.printf(FORMAT, "------------------------------------------------------------------");
                            System.out.printf(FORMAT, "Good job! I've marked this task as done:");
                            System.out.printf("\t\t%s%n", t);
                            System.out.printf(FORMAT, "------------------------------------------------------------------");

                        }
                    }
                } else if (splitInput[0].equalsIgnoreCase("unmark")) {
                    if (splitInput.length < 2) {
                        throw new VoidException("Hm.. I don't know which to unmark! Give me the task number please.");
                    } else {
                        int tasksIndex = Integer.parseInt(splitInput[1]) - 1;
                        if (tasksIndex >= tasks.size() || tasksIndex < 0) { //checks if index is appropriate
                            throw new VoidException("OOPS!!! The task number provided is invalid.");

                        } else {
                            Task t = tasks.get(tasksIndex);
                            t.unmarkAsDone();
                            System.out.printf(FORMAT, "------------------------------------------------------------------");
                            System.out.printf(FORMAT, "OK, I've marked this task as not done yet:");
                            System.out.printf("\t\t%s%n", t);
                            System.out.printf(FORMAT, "------------------------------------------------------------------");
                        }
                    }
                } else if (splitInput[0].equalsIgnoreCase("todo")) {
                    if (splitInput.length == 1 || splitInput[1].isBlank()) {
                        throw new VoidException("Meow! The description of a todo can't be empty!");
                    } else {
                        ToDo t = new ToDo(splitInput[1]);
                        t.addTaskMessage(tasks);

                    }
                } else if (splitInput[0].equalsIgnoreCase("deadline")) {
                    if (splitInput.length == 1 || splitInput[1].isBlank()) {
                        throw new VoidException("Meow! The description of a deadline can't be empty!");

                    } else {
                        String[] details = splitInput[1].split(" /by ");
                        if (details.length != 2) {
                            throw new VoidException("AH! The description of a deadline and the deadline can't be empty!\n\tRemember to put a /by after the description!");

                        } else {
                            Deadline d = new Deadline(details[0], details[1]);
                            d.addTaskMessage(tasks);
                        }
                    }
                } else if (splitInput[0].equalsIgnoreCase("event")) {
                    if (splitInput.length == 1 || splitInput[1].isBlank()) {
                        throw new VoidException("Meow! The description of an event can't be empty!");
                    } else {
                        String[] details = splitInput[1].split(" /from | /to ");
                        if (details.length != 3) {
                            throw new VoidException("AH! The description of an event, and the start and end time can't be empty!\n\tRemember to put a /from and /to after the description!");
                        } else {
                            Event e = new Event(details[0], details[1], details[2]);
                            e.addTaskMessage(tasks);
                        }
                    }
                } else {
                    throw new VoidException("AH!! My apologies, I don't know what that means =T^T=");
                }
                saveTasksToFile();
            } catch (VoidException v) {
                v.voidExceptionMessage();
            }
        }

        System.out.printf(FORMAT, "------------------------------------------------------------------");
        scanner.close();

    }
}
