import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Serenity {
    private static final String horizontalLine = "__________________________________________";
    private static final ArrayList<Task> list = new ArrayList<>();
    private static int count = 0;

    private static final String filePath = "data/serenity.txt";
    public static void main(String[] args) {

        File f = new File(filePath);

        try {
            setUpFile();
            loadFile(f);
        } catch (IOException | SerenityException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(horizontalLine);
        System.out.println("Hi, I'm Serenity!\n" + "What can I do for you?");
        System.out.println(horizontalLine);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            try {
                if (input.equalsIgnoreCase("list")) {
                    System.out.println(horizontalLine);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                } else if (input.contains("mark")) {
                    changeStatus(input);
                    writeToFile();
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else {
                    addTask(input);
                }
            } catch (SerenityException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(horizontalLine);
            input = sc.nextLine();
        }

        sc.close();

        System.out.println(horizontalLine);
        System.out.println("Goodbye. Hope to see you again soon!");
        System.out.println(horizontalLine);

    }

    private static void changeStatus(String input) throws SerenityException, IOException {

        String[] parts = input.split(" ");
        if (parts.length == 1) {
            throw new SerenityException("Error: Missing task index.");
        }

        if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task t = list.get(index);
            t.markAsDone();
            System.out.println(horizontalLine);
            System.out.println("Nice! I've marked this task as done:\n" + t);
        } else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task t = list.get(index);
            t.markAsNotDone();
            System.out.println(horizontalLine);
            System.out.println("OK, I've marked this task as not done yet:\n" + t);
        } else {
            throw new SerenityException("Error: Type of task is not specified.");
        }
    }

    private static void addTask(String input) throws SerenityException, IOException {

        Task t;

        if (input.startsWith("todo")) {
            String[] description = input.split(" ");
            if (description.length == 1) {
                throw new SerenityException("Error: The description of a todo cannot be empty.");
            } else {
                //remove the type of task
                String taskDescription = input.split(" ", 2)[1];
                t = new Todo(taskDescription);
            }
        } else if (input.startsWith("deadline")) {
            String[] description = input.split(" ");
            if (description.length == 1) {
                throw new SerenityException("Error: The description of a deadline cannot be empty.");
            } else {
                String taskDescription = input.split(" ", 2)[1];
                String[] parts = taskDescription.split("/by");
                t = new Deadline(parts[0].strip(), parts[1].strip());
            }
        } else if (input.startsWith("event")) {
            String[] description = input.split(" ");
            if (description.length == 1) {
                throw new SerenityException("Error: The description of an event cannot be empty.");
            } else {
                String taskDescription = input.split(" ", 2)[1];
                String[] parts = taskDescription.split("/from");
                String[] timings = parts[1].split("/to");
                t = new Event(parts[0].strip(), timings[0].strip(), timings[1].strip());
            }
        } else {
            throw new SerenityException("Error: Type of task is not specified.");
        }

        list.add(t);
        count++;
        saveTask(t);
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:\n" + t);
        String numOfTasks = count == 1 ? "task" : "tasks";
        System.out.println("Now you have " + count + " " + numOfTasks + " in the list.");
    }

    private static void deleteTask(String input) throws SerenityException {
        String[] parts = input.split(" ");
        if (parts.length == 1) {
            throw new SerenityException("Error: Missing task index.");
        }

        int index = Integer.parseInt(input.substring(7)) - 1;
        Task t = list.get(index);
        list.remove(index);
        count --;
        System.out.println(horizontalLine);
        System.out.println("Noted. I've removed this task:\n" + t);
        String numOfTasks = count == 1 ? "task" : "tasks";
        System.out.println("Now you have " + count + " " + numOfTasks + " in the list.");
    }

    private static void setUpFile() throws IOException {

        //create directory if directory does not exist
        Files.createDirectories(Paths.get("data"));
        File f = new File(filePath);

        //create file if file does not exist
        if (!f.exists()) {
            Files.createFile(Paths.get(filePath));
        }
    }

    private static void loadFile(File f) throws FileNotFoundException, SerenityException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
           list.add(loadTask(s.nextLine()));
           count++;
        }
    }

    private static Task loadTask(String taskDescription) throws SerenityException {
        String[] parts = taskDescription.split("\\|");
        Task t;

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].strip();
        }

        String taskType = parts[0];

        //noinspection EnhancedSwitchMigration
        switch (taskType) {
        case "T":
            t = new Todo(parts[2]);
            break;
        case "D":
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            LocalDate date = LocalDate.parse(parts[3], formatter);
            String by = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            t = new Deadline(parts[2], by);
            break;
        case "E":
            t = new Event(parts[2], parts[3], parts[4]);
            break;
        default:
            throw new SerenityException("Error: Task cannot be loaded.");
        }

        if (parts[1].equals("1")) {
            t.markAsDone();
        }

        return t;
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    private static void saveTask(Task t) throws IOException {
        String textToAppend = t.formatData();
        appendToFile(textToAppend);
    }

    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath); //overwrite
        for (Task t: list) {
            fw.write(t.formatData() + "\n");
        }
        fw.close();
    }

}
