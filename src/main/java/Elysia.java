import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Elysia {
    private static String line = "____________________________________________________________";
    private static String welcomeMessage = "Hi there! Did you miss me?\n" +
            "Wherever you are and whenever you need,\n" +
            "Elysia will always meet your expectations.";
    private static String exitMessage = "Alright, this time we really have to say goodbye.\n" +
            "Goodbye, Mei!";
    private static ArrayList<Task> arrayLists;
    private static String folderName = "data";
    private static String fileName = "elysia.txt";
    private static String filePath = "./data/elysia.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        arrayLists = new ArrayList<>();

        System.out.println(line);
        System.out.println(welcomeMessage);
        System.out.println(line);

        scanFileContents(filePath);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(exitMessage);
                System.out.println(line);

                handleExit();
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark ")) {
                markAsDone(input);
            } else if (input.startsWith("unmark ")) {
                unmarkAsDone(input);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {

                if (input.startsWith("todo")) {
                    addToDos(input.substring(4).trim());
                } else if (input.startsWith("deadline")) {
                    addDeadline(input.substring(8).trim());
                } else {
                    addEvent(input.substring(5).trim());
                }

            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else {
                System.out.println("Oh my! I'm so sorry,\n" +
                        "but it seems I'm not sure what that means.\n" +
                        "Let's figure it out together, shall we?");
            }
        }
    }

    private static void handleAddedMessage(Task task) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + arrayLists.size() + " tasks in the list.");
        System.out.println(line);
    }

    private static void addToDos(String s) {
        if (s.isEmpty()) {
            handleEmptyDescription("todo");
        } else {
            Task task = new ToDos(s);
            arrayLists.add(task);
            handleAddedMessage(task);
        }
    }

    //deadline return book /by Sunday
    private static void addDeadline(String s) {
        if (s.isEmpty()) {
            handleEmptyDescription("deadline");
        } else {
            String[] str = s.split("/by ");
            Task task = new Deadline(str[0].trim(), DateParser.parseDate(str[1]));
            arrayLists.add(task);
            handleAddedMessage(task);
        }
    }

    private static void addEvent(String s) {
        if (s.isEmpty()) {
            handleEmptyDescription("event");
        } else {
            String[] str = s.split("/from | /to ");
            Task task = new Event(str[0].trim(), str[1], str[2]);
            arrayLists.add(task);
            handleAddedMessage(task);
        }
    }

    private static void printList() {
        int n = arrayLists.size();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= n; i++) {
            Task curr = arrayLists.get(i - 1);
            System.out.println(i + "." + curr.toString());
        }
    }

    private static void markAsDone(String input) {
        int index = Integer.parseInt(String.valueOf(input.charAt(5))) - 1;
        Task curr = arrayLists.get(index);
        curr.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(curr);
    }

    private static void unmarkAsDone(String input) {
        int index = Integer.parseInt(String.valueOf(input.charAt(7))) - 1;
        Task curr = arrayLists.get(index);
        curr.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(curr);
    }

    private static void handleEmptyDescription(String taskType) {
        System.out.println(line);
        System.out.println("Oopsie! It looks like the description for this " +
                taskType +
                " is missing.\n" +
                "How about we add a little something to it?");
        System.out.println(line);
    }

    private static void deleteTask(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = arrayLists.get(index);
        arrayLists.remove(index);
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + arrayLists.size() + " tasks in the list.");
        System.out.println(line);
    }

    private static void createFile() throws IOException {
        File dataDir = new File(folderName);

        if (!dataDir.exists()) {
            dataDir.mkdir();

        }

        if (dataDir.exists() && dataDir.isDirectory()) {
            File txtFile = new File(dataDir, fileName);

            try {
                if (txtFile.createNewFile()) {
                    System.out.println(fileName + " successfully created.");
                } else {
                    System.out.println(fileName + " already exists");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the " + fileName);
            }
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void handleExit() throws IOException {
        createFile();

        try {
            writeToFile(filePath, arrayLists.get(0).saveToTxt());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        for (int i = 1; i < arrayLists.size(); i++) {
            appendToFile(filePath, "\n" + arrayLists.get(i).saveToTxt());
        }
    }

    private static void scanFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        if (f.exists() && f.isFile()) {
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] str = input.split(" \\| ");

                if (str[0].equals("T")) {
                    addToDos(str[2], Integer.parseInt(str[1]));
                }

                if (str[0].equals("D")) {
                    addDeadline(str[2], Integer.parseInt(str[1]), str[3]);
                }

                if (str[0].equals("E")) {
                    addEvent(str[2], Integer.parseInt(str[1]), str[3], str[4]);
                }
            }
        }
    }

    private static void addToDos(String s, int i) {

            Task task = new ToDos(s);
            if (i == 1) {
                task.markAsDone();
            }
            arrayLists.add(task);
    }

    //deadline return book /by Sunday
    private static void addDeadline(String s, int i, String by) {
            Task task = new Deadline(s, DateParser.parseDate(by));
            if (i == 1) {
                task.markAsDone();
            }
            arrayLists.add(task);
        }
    private static void addEvent(String s, int i, String from, String to) {

            Task task = new Event(s, from, to);
            if (i == 1) {
                task.markAsDone();
            }
            arrayLists.add(task);
    }

}

