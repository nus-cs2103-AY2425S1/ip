import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Oliver {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Path dataPath = Paths.get("./data/oliver.txt");
        boolean fileExists = Files.exists(dataPath);
        // Create empty data file if it does not exist yet
        if (!fileExists) {
            Path directoryPath = Paths.get("data");
            try {
                Files.createDirectory(directoryPath);
                Files.createFile(dataPath);
            } catch (IOException e) {
                System.out.println("\tError occurred when creating directory/file");
            }
        }
        // Read the saved tasks into the arraylist
        loadData();

        System.out.println("Hello! I'm Oliver.");
        System.out.println("What can I do for you?");

        while (true) {
            String input = sc.nextLine();
            String command = input.split(" ")[0];

            if (input.equalsIgnoreCase("bye")) {
                writeToFile(tasks);
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                handleList();
            } else if (command.equalsIgnoreCase("mark")) {
                handleMark(input);
            } else if (command.equalsIgnoreCase("unmark")) {
                handleUnmark(input);
            } else if (command.equalsIgnoreCase("todo")) {
                handleTodo(input);
            } else if (command.equalsIgnoreCase("deadline")) {
                handleDeadline(input);
            } else if (command.equalsIgnoreCase("event")) {
                handleEvent(input);
            } else if (command.equalsIgnoreCase("delete")) {
                handleDelete(input);
            } else {
                System.out.println("\tInvalid command. Command was not recognised.");
            }
        }
        sc.close();
    }

    public static void addSuccess(Task t) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleList() {
        if (tasks.isEmpty()) {
            System.out.println("\tThere are no tasks in your list.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i+1) + "." + tasks.get(i));
            }
        }
    }

    public static void handleMark(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index > tasks.size() - 1 || index < 0) {
                System.out.println("\tNo such task exists. Task number out of range.");
            } else {
                tasks.get(index).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + tasks.get(index));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        } catch (NumberFormatException e) {
            System.out.println("\tInvalid arguments provided for this command.");
        }
    }

    public static void handleUnmark(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index > tasks.size() - 1 || index < 0) {
                System.out.println("\tNo such task exists. Task number out of range.");
            } else {
                tasks.get(index).markAsUndone();
                System.out.println("\tOk, I've marked this task as not done yet:");
                System.out.println("\t" + tasks.get(index));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        } catch (NumberFormatException e) {
            System.out.println("\tInvalid arguments provided for this command.");
        }
    }

    public static void handleTodo(String input) {
        try {
            ToDo t = new ToDo(input.split(" ", 2)[1]);
            tasks.add(t);
            addSuccess(t);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        }
    }

    public static void handleDeadline(String input) {
        try {
            String[] parts = input.split("/by ");
            String time = parts[1];
            String action = parts[0].trim();
            Deadline d = new Deadline(action.split(" ", 2)[1], time);
            tasks.add(d);
            addSuccess(d);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        }
    }

    public static void handleEvent(String input) {
        try {
            String[] parts = input.split("/from |/to ");
            String action = parts[0].trim();
            String start = parts[1].trim();
            String end = parts[2].trim();
            Event e = new Event(action.split(" ", 2)[1], start, end);
            tasks.add(e);
            addSuccess(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        }
    }

    public static void handleDelete(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index > tasks.size() - 1 || index < 0) {
                System.out.println("\tNo such task exists. Task number out of range.");
            } else {
                Task removedTask = tasks.get(index);
                tasks.remove(index);
                System.out.println("\tOk, I've removed this task:");
                System.out.println("\t" + removedTask);
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        } catch (NumberFormatException e) {
            System.out.println("\tInvalid arguments provided for this command.");
        }
    }

    private static void loadData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./data/oliver.txt"));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split("\\|");
                Task t;
                if (data[0].equals("T")) {
                    t = new ToDo(data[2]);
                } else if (data[0].equals("D")) {
                    t = new Deadline(data[2], data[3]);
                } else {
                    t = new Event(data[2], data[3], data[4]);
                }
                if (data[1].equals("X")) {
                    t.markAsDone();
                }
                tasks.add(t);
            }
        } catch (IOException e) {
            System.out.println("\tError occurred when reading data into list");
        }
    }

    private static void writeToFile(ArrayList<Task>taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./data/oliver.txt", false));
            for (Task task : taskList) {
                writer.write(task.getFormatted());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("\tError occurred when writing to the data file.");
        }
    }
}
