import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Bobby {
    private static ArrayList<Task> storage = new ArrayList<>();
    private static File dataFile;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        try {
            loadData();
        } catch (IOException e) {
            System.out.println("Uh oh something bad happen");
        }

        while (true) {
            System.out.println();
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                bye();
                System.out.println();
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                listOutTasks();
                continue;
            }

            String[] splitInput = input.split(" ");
            switch (splitInput[0].toLowerCase()) {
                case "mark":
                    try {
                        mark(Integer.valueOf(splitInput[1]));
                    } catch (IndexOutOfBoundsException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println("Item cannot be accessed in list.");
                        System.out.println("Check the number of items again.");
                        horizontalLine(35);
                    }
                    break;
                case "unmark":
                    try {
                        unmark(Integer.valueOf(splitInput[1]));
                    } catch (IndexOutOfBoundsException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println("Item cannot be accessed in list.");
                        System.out.println("Check the number of items again.");
                        horizontalLine(35);
                    }
                    break;
                case "delete":
                    try {
                        delete(Integer.valueOf(splitInput[1]));
                    } catch (IndexOutOfBoundsException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println("Item cannot be accessed in list.");
                        System.out.println("Check the number of items again.");
                        horizontalLine(35);
                    }
                    break;
                case "todo":
                    try {
                        Todo td = Todo.createTodo(input);
                        addToList(td);
                    } catch (EmptyDescriptionException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println(e.getMessage());
                        horizontalLine(35);
                    }
                    break;
                case "deadline":
                    try {
                        Deadline dl = Deadline.createDeadline(input);
                        addToList(dl);
                    } catch (EmptyDescriptionException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println(e.getMessage());
                        horizontalLine(35);
                    }
                    break;
                case "event":
                    try {
                        Event event = Event.createEvent(input);
                        addToList(event);
                    } catch (EmptyDescriptionException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println(e.getMessage());
                        horizontalLine(35);
                    }
                    break;
                default:
                    horizontalLine(35);
                    System.out.println();
                    System.out.println("Please use a valid command: todo, deadline or event.");
                    horizontalLine(35);
            }
        }
    }

    // Make a horizontal line of x dashes
    public static void horizontalLine(int x) {
        for (int i = 0; i < x; i++) {
            System.out.print("-");
        }
    }

    public static void greet() {
        horizontalLine(35);
        System.out.println();
        System.out.println("Hello! I'm Bobby");
        System.out.println("What can I do for you?");
        horizontalLine(35);
    }

    public static void bye() {
        horizontalLine(35);
        System.out.println();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine(35);
    }

    public static void repeat(String s) {
        horizontalLine(35);
        System.out.println();
        System.out.println(s);
        horizontalLine(35);
    }

    public static void addToList(Task t) {
        Bobby.storage.add(t);
        horizontalLine(35);
        System.out.println();
        System.out.println("Plus one more thing to do:");
        System.out.println(t.toString());
        System.out.printf("Now you have %d tasks.%n", storage.size());
        horizontalLine(35);
        try {
            writeData(dataFile.getPath(), t.taskInFile());
        } catch (IOException e) {
            System.out.println("Error processing the file: " + e.getMessage());
        }
    }

    public static void listOutTasks() {
        horizontalLine(35);
        int n = Bobby.storage.size();
        System.out.println();
        for (int i = 0; i < n; i++) {
            int number = i + 1;
            System.out.println(number + ". " + Bobby.storage.get(i));
        }
        horizontalLine(35);
    }

    public static void mark(int x) {
        Task t = storage.get(x);
        t.indComplete();
        horizontalLine(35);
        System.out.println();
        System.out.println("Nice! I have marked this task as done:");
        System.out.println(t);
        horizontalLine(35);
        saveData(storage);
    }

    public static void unmark(int x) {
        Task t = storage.get(x);
        t.indIncomplete();
        horizontalLine(35);
        System.out.println();
        System.out.println("OK, I have marked this task as not done yet:");
        System.out.println(t);
        horizontalLine(35);
        saveData(storage);
    }

    public static void delete(int x) {
        horizontalLine(35);
        System.out.println();
        System.out.printf("I have removed this task: %s%n", storage.get(x).toString());
        storage.remove(x);
        System.out.printf("Now you have %d tasks left.%n", storage.size());
        horizontalLine(35);
        saveData(storage);
    }

    public static void loadData() throws IOException {
        File data = new File("data.txt");

        if (data.createNewFile()) {
            dataFile = data;
            System.out.println();
            System.out.println("A new data file has been created");
            horizontalLine(35);
        } else {
            try {
                dataFile = data;
                printFileContent((data.getPath()));
                loadDataIntoStorage(data.getPath());
            } catch (FileNotFoundException e) {
                System.out.println("The file cannot be found, please check again.");
            }
        }
    }

    public static void loadDataIntoStorage(String filePath) {
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                processLines(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file cannot be found, please check again.");
        }
    }

    public static void processLines(String line) {
        String[] lineParts = line.split(" \\| ");
        switch (lineParts[0]) {
        case "T":
            try {
                Task t = Todo.createTodo("todo " + lineParts[2]);
                if (lineParts[1].equals("X")) {
                    t.indIncomplete();
                }
                Bobby.storage.add(t);
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }
            break;

        case "D":
            try {
                Task t = Deadline.createDeadline("deadline " + lineParts[2] + " " + lineParts[3]);
                if (lineParts[1].equals("X")) {
                    t.indIncomplete();
                }
                Bobby.storage.add(t);
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }
            break;

        case "E":
            try {
                Task t = Event.createEvent("event " + lineParts[2] + " " + lineParts[3]);
                if (lineParts[1].equals("X")) {
                    t.indIncomplete();
                }
                Bobby.storage.add(t);
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
    }

    public static void writeData(String filePath, String input) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        File f = new File(filePath);

        if (f.exists() && f.length() > 0) {
            fw.write(System.lineSeparator());
        }
        fw.write(input);
        fw.close();
    }

    public static void saveData(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(dataFile.getPath());
            for (Task task: taskList) {
                String taskEntry = task.taskInFile();
                fw.write(taskEntry + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void printFileContent(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        System.out.println();
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
        horizontalLine(35);
    }
}
