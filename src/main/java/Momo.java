import java.io.IOException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.time.DateTimeException;
import java.util.*;
import java.util.ArrayList;
import java.io.FileWriter;
import static java.lang.System.exit;
import java.util.regex.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Momo {

    public static String horizontalLine = "____________________________________________________________";

    public enum Command {
        BYE,
        LIST,
        DELETE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT
    }

    static ArrayList<Task> list = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) throws MomoException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Momo\nWhat can I do for you?");
        loadListFile();
        printList();
        System.out.println("This is your current list");
        String input = sc.nextLine();

        while (true) {
            try {
                Command command = checkValidInput(input);
                if (command == Command.BYE) {
                    MomoBye();
                }
                else if (command == Command.LIST) {
                    printList();
                }
                else if (command == Command.MARK) {
                    changeCompletion(Integer.parseInt(input.split(" ")[1]) - 1, Command.MARK);
                }
                else if (command == Command.UNMARK) {
                    changeCompletion(Integer.parseInt(input.split(" ")[1]) - 1, Command.UNMARK);
                }
                else if (command == Command.DELETE) {
                    deleteTask(Integer.parseInt(input.split(" ")[1]) - 1);
                }
                else {
                    if (command == Command.TODO) {
                        addToDo(input);
                    }
                    else if (command == Command.DEADLINE) {
                        addDeadline(input);
                    }

                    else if (command == Command.EVENT) {
                        addEvent(input);
                    }

                    printTaskAdded();

                }

            }
            catch (MomoException e){
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }
    }

    public static Command checkValidInput(String input) throws MomoException {

        // Check if input is empty
        if (Objects.equals(input, "")) {
            throw new MomoException("I dare you to send an empty command again...");
        }

        // Check if input is bye
        if (Objects.equals(input, "bye")) {
            return Command.BYE;
        }

        // Check if input is list
        if (Objects.equals(input, "list")) {
            return Command.LIST;
        }

        // Check if input is delete
        if (input.startsWith("delete")) {
            if (Pattern.matches("delete\\s\\d", input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                if (index >= count || index < 0) {
                    throw new MomoException("You can only delete a task of a number your task list contains");
                }
                return Command.DELETE;
            }

            throw new MomoException("You're supposed to indicate the task number you want to delete!");

        }

        // Checking for mark and unmark input
        if (input.startsWith("mark") || input.startsWith("unmark")) {
            // Check if input has format "mark x" or "unmark x"
            if (Pattern.matches("mark\\s\\d", input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                if (index >= count || index < 0) {
                    throw new MomoException("You can only mark a number your task list contains");
                }
                return Command.MARK;
            }

            else if (Pattern.matches("unmark\\s\\d", input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                if (index >= count || index < 0) {
                    throw new MomoException("You can only unmark a number your task list contains");
                }
                return Command.UNMARK;
            }

            throw new MomoException("Mark my words - You must enter a number x next to mark or unmark in the exact format `mark x`.");
        }

        // Checking for proper task input
        String taskType = input.split(" ")[0];

        switch (taskType) {
            case "todo" -> {
                if (input.split(" ").length < 2) {
                    throw new MomoException("Where's the description of your task??");
                }
                return Command.TODO;
            }
            case "deadline" -> {

                try {
                    String desc = input.split(" ", 2)[1];
                    String task = desc.split("/", 2)[0];
                    String by = desc.split("/by", 2)[1];
                    return Command.DEADLINE;
                }
                catch (Exception e) {
                    throw new MomoException("You likely did not format your deadline properly\nIt should be in the format `deadline task /by date`");
                }
            }
            case "event" -> {
                try {
                    String desc = input.split(" ",2)[1];
                    String task =  desc.split("/",2)[0];
                    String from = desc.split("/from")[1].split("/to ")[0];
                    String to = desc.split("/to")[1];
                    return Command.EVENT;
                }
                catch (Exception e) {
                    throw new MomoException("You likely did not format your event properly\nIt should be in the format `event task /from date time /to time`");
                }
            }
            default -> {
                throw new MomoException("You did not properly specify the type of task (todo/deadline/event) or command (bye/list)");
            }
        }
    }

    public static void MomoBye() {
        String logo =
                        "⣿⣿⣿⡉⢀⣾⣿⡟⣩⣭⣭⡈⠙⢿⣿⣿⣿⣿⣿⡿⣻⣿⣿⣿⣿⣿⣿⣿⡇⠄\n" +
                        "⣿⣿⡗⠄⣼⣿⣿⢸⡿⠉⠉⢻⡆⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢠⠄\n" +
                        "⣿⡻⠁⢠⣿⣿⣿⣦⡛⠢⠴⠛⠁⣸⣿⣿⣿⣿⡿⠛⢉⣉⣉⡙⢻⣿⣿⣗⠄⠄\n" +
                        "⠷⠁⠄⢰⣿⣿⣿⣷⣬⣭⣼⣷⣿⣿⣿⣿⣿⡏⢀⣾⠟⠛⢿⣿⣄⣿⣿⡏⠄⠄\n" +
                        "⠄⠄⠄⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠄⠳⢀⣀⡼⢟⣼⣿⡟⠄⠄⠄\n" +
                        "⠄⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣒⣲⣶⣾⣿⣿⠏⠄⠄⠄⢠\n" +
                        "⠄⠄⠄⠸⣿⣽⣿⣿⣿⣿⣉⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠄⠄⠄⢠⣷\n" +
                        "⠄⠄⠄⠄⢻⣷⢻⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⠄⠄⠄⠄⢀⣾⣿\n" +
                        "⠄⠄⠄⠄⠄⢻⣧⡙⢿⣿⣿⣿⣿⣿⡿⣿⣿⣿⠿⠛⠁⠄⠄⠄⠄⠄⢠⣿⣿⣿\n" +
                        "⠄⠄⠄⡀⠄⠈⣿⣿⣶⣭⣭⣭⣿⣾⡿⠟⠋⠁⠄⠄⠄⠄⠄⠄⠄⢠⣿⣿⣿⣿\n" +
                        "⠄⠄⠎⠄⠄⣨⣿⣿⣿⣿⣿⣿⠋⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⣀⡲⣿⣿⣿⣿";

        System.out.println(horizontalLine);
        System.out.println("Farewell... for now. I'll be waiting for your return, taking refuge in your shadows. Rest well.... wħɨłɇ ɏøᵾ sŧɨłł ȼȺn\n" + logo);
        System.out.println(horizontalLine);
        System.exit(0);
    }

    public static void printList() {
        System.out.println(horizontalLine);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        System.out.println(horizontalLine);
    }

    public static void loadListFile() {
        // Load file if it exists
        try {
            File f = new File("data/momo.txt"); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            String nextLine;
            while (s.hasNext()) {
                nextLine = s.nextLine();
                String[] inputs = nextLine.split("\\|");
                if (Objects.equals(inputs[0], "T")) {
                    if (Objects.equals(inputs[1], "1")) {
                        list.add(new Todo(inputs[2], false));
                    }
                    else {
                        list.add(new Todo(inputs[2], true));
                    }
                }
                else if (Objects.equals(inputs[0], "D")) {
                    if (Objects.equals(inputs[1], "1")) {
                        list.add(new Deadline(inputs[2], parseDate(inputs[3]), false));
                    }
                    else {
                        list.add(new Deadline(inputs[2], parseDate(inputs[3]), true));
                    }
                }
                else if (Objects.equals(inputs[0], "E")) {
                    if (Objects.equals(inputs[1], "1")) {
                        list.add(new Event(inputs[2], parseDate(inputs[3]), parseDate(inputs[4]), false));
                    }
                    else {
                        list.add(new Event(inputs[2], parseDate(inputs[3]), parseDate(inputs[4]), true));
                    }

                }
                else {
                    System.out.println("Invalid file formatting....");
                }
            }
            count = list.size();
            System.out.println(count);
            s.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found, creating a new file");
            File f = new File("data/momo.txt");
            File d = new File("data");

            try {
                boolean folderCreated = d.mkdir();
                boolean fileCreated = f.createNewFile();
                if (fileCreated) {
                    System.out.println("File created successfully");
                }
            }
            catch (IOException ioe) {
                System.out.println("Error in adding a file");
            }
        }
    }



    public static void changeCompletion(int index, Command command) {
        System.out.println(horizontalLine);
        Task task = list.get(index);

        if (command == Command.MARK) {
            task.markComplete();
        }
        else {
            task.unmark();
        }

        System.out.println(horizontalLine);

        try {
            RewriteTasksToFile("data/momo.txt");
        }
        catch (IOException e) {
            System.out.println("Tasks not written successfully:" + e.getMessage());
        }

    }

    public static void addToDo(String input) {
        String task = input.split(" ",2)[1].trim();
        list.add(new Todo(task, false));


        String file2 = "data/momo.txt";
        try {
            writeToFile(file2, new Todo(task, false).toFileString());
            count++;

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void addDeadline(String input) {
        String desc = input.split(" ",2)[1];
        String task =  desc.split("/",2)[0].trim();
        String by = desc.split("/by",2)[1].trim();

        try {
            list.add(new Deadline(task, parseDate(by), false));
        } catch (DateTimeException de) {
            System.out.println("Please format your dates in yyyy-mm-dd with valid numbers: " + de.getMessage());
            return;
        }

        String file2 = "data/momo.txt";
        try {
            writeToFile(file2, new Deadline(task, parseDate(by), false).toFileString());
            count++;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void addEvent(String input) {
        String desc = input.split(" ",2)[1];
        String task =  desc.split("/",2)[0].trim();
        String from = desc.split("/from")[1].split("/to")[0].trim();
        String to = desc.split("/to")[1].trim();


        try {
            list.add(new Event(task, parseDate(from), parseDate(to), false));
        } catch (DateTimeException de) {
            System.out.println("Please format your dates in yyyy-mm-dd with valid numbers: " + de.getMessage());
            return;
        }

        String file2 = "data/momo.txt";
        try {
            writeToFile(file2, new Event(task, parseDate(from), parseDate(to), false).toFileString());
            count++;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    public static void printTaskAdded() {
        try {
            System.out.println(horizontalLine);

            System.out.println("Noted. I've added this task:\n " + list.get(count - 1));
            System.out.println(String.format("Now you have %d task(s) in the list", count));
            System.out.println(horizontalLine);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("This task has not been added: " + e.getMessage());
            System.out.println(horizontalLine);
        }
    }

    public static void deleteTask(int index) {
        count--;
        System.out.println("Noted. I've removed this task:\n " + list.get(index));
        list.remove(index);
        System.out.println(String.format("Now you have %d task(s) in the list", count));
        System.out.println(horizontalLine);
        try {
            RewriteTasksToFile("data/momo.txt");
        }
        catch (IOException e) {
            System.out.println("Tasks not written successfully:" + e.getMessage());
        }
    }

    public static LocalDate parseDate(String input) throws DateTimeException {
        return LocalDate.parse(input);
    }

    public static void RewriteTasksToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            fw.write(task.toFileString() + "\n");
        }
        fw.close();
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }
}




