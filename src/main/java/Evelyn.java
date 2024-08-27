import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Evelyn {
    private static String chatbotName = "Evelyn";

    private static ArrayList lst = new ArrayList(100);
    private static boolean isChatting = true;
    private static String dataFilePath = "src/main/data/evelyn.txt";
    private static File file = new File(dataFilePath);
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public Evelyn(String filePath) {
        this.storage = new Storage(dataFilePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (IOException e) {
            System.err.println("Error reading/writing file");
        }
        this.parser = new Parser(this.storage, this.taskList);
        this.ui = new Ui(this.parser);
    }

    public void run() {
        this.ui.start();
    }

    public static void main(String[] args) throws IOException {
        new Evelyn(dataFilePath).run();

        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                BufferedReader br = new BufferedReader(new FileReader(dataFilePath));
                String line;
                while ((line = br.readLine()) != null) {
                    fileDataToList(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }

        while (isChatting) {
            text = scanner.nextLine();

            try {
                decipher(text);
            } catch (InvalidInputException e) {
                System.out.println(horizontalLine);
                System.out.println("You did not use the keywords properly!");
                System.out.println("Please use the following key words:");
                System.out.println("todo [task description]");
                System.out.println("deadline [task description] /by [date]");
                System.out.println("event [task description] /from [start date and time] /to [end date and time");
                System.out.println(horizontalLine);
            } catch (NoInputException e) {
                System.out.println(horizontalLine);
                System.out.println("Invalid");
                System.out.println("Please use the following key words:");
                System.out.println("todo [task description]");
                System.out.println("deadline [task description] /by [date]");
                System.out.println("event [task description] /from [start date and time] /to [end date and time");
                System.out.println(horizontalLine);
            } catch (IOException e) {
                System.out.println("Error reading/writing file");
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void fileDataToList(String data) throws IOException {
        boolean isMarked;
        if (data.startsWith("[T]")) {
            if (data.contains("[X]")) {
                isMarked = true;
            } else {
                isMarked = false;
            }
            System.out.println(isMarked ? "True" : "False");
            String description = data.substring(7);
            Todo newTodo = new Todo(description, isMarked);
            lst.add(newTodo);
        } else if (data.startsWith("[D]")) {
            if (data.contains("[X]")) {
                isMarked = true;
            } else {
                isMarked = false;
            }
            String descAndDate = data.substring(7);
            String[] parts = descAndDate.split(" \\(by: ");
            String description = parts[0];
            String deadline = parts[1].substring(0, parts[1].length() - 1);
            Deadline newDeadline = new Deadline(description, deadline, isMarked);
            lst.add(newDeadline);
        } else if (data.startsWith("[E]")) {
            if (data.contains("[X]")) {
                isMarked = true;
            } else {
                isMarked = false;
            }
            String descAndDate = data.substring(7);
            String[] parts1 = descAndDate.split(" \\(from: ");
            String description = parts1[0];
            String[] parts2 = parts1[1].split(" to: ");
            String start = parts2[0];
            String end = parts2[1].substring(0, parts2[1].length() - 1);

            Event newEvent = new Event(description, start, end, isMarked);
            lst.add(newEvent);
        } else {
            throw new IOException();
        }
    }

}
