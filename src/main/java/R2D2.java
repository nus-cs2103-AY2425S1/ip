import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class R2D2 {
    private static final String FILE_PATH = "./data/R2D2.txt";

    public static void main(String[] args) {

        // Opening dialogue for the bot
        String hline = "____________________________________________________________";
        System.out.println(hline);
        System.out.println("Hello! I'm R2D2! *Beep* *Boop*");
        System.out.println("What can I do for you?");
        System.out.println(hline);

        // Reading input from user
        Scanner reader = new Scanner(System.in);

        // Initialize a new database and load tasks
        ArrayList<Task> database = new ArrayList<>();
        InputHandler c3po = new InputHandler(database);
        loadTasks(database);

        // Main interaction
        while (true) {
            String input = reader.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.startsWith("mark")) {
                    c3po.markHandle(input);
                    saveTasks(database);
                } else if (input.startsWith("unmark")) {
                    c3po.unmarkHandle(input);
                    saveTasks(database);
                } else if (input.startsWith("delete")) {
                    c3po.deleteHandle(input);
                    saveTasks(database);
                } else if (input.equals("list")) {
                    System.out.println(hline);
                    for (int i = 0; i < database.size(); i++) {
                        System.out.println((i + 1) + "." + database.get(i).toString());
                    }
                    System.out.println(hline);
                } else if (input.startsWith("todo")) {
                    c3po.todoHandle(input);
                    saveTasks(database);
                } else if (input.startsWith("deadline")) {
                    c3po.deadlineHandle(input);
                    saveTasks(database);
                } else if (input.startsWith("event")) {
                    c3po.eventHandle(input);
                    saveTasks(database);
                } else {
                    throw new BuzzException("GRRR! I do not know what that means. Try again! *bzzrg*");
                }
            } catch (BuzzException e) {
                System.out.println(hline);
                System.out.println(" " + e.getMessage());
                System.out.println(hline);
            }
        }

        // Standard exit when bye is typed
        System.out.println(hline);
        System.out.println("Bye. Hope to see you again soon! *bzzzt*");
        System.out.println(hline);
    }

    private static void saveTasks(ArrayList<Task> database) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : database) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasks(ArrayList<Task> database) {
        File file = new File(FILE_PATH);
        File directory = new File(file.getParent());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task = null;
                try {
                    switch (parts[0]) {
                        case "T":
                            task = new Todo(parts[2]); // Create Todo task
                            break;
                        case "D":
                            task = new Deadline(parts[2], parts[3]); // Create Deadline task
                            break;
                        case "E":
                            task = new Event(parts[2], parts[3], parts[4]); // Create Event task
                            break;
                    }
                    if (task != null) {
                        task.setDone(parts[1].equals("1")); // Set the task's done status
                        database.add(task); // Add task to the database
                    }
                } catch (BuzzException e) {
                    System.out.println("ERROR!: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
