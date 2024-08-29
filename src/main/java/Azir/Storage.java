package Azir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static ArrayList<Task> readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        System.out.println("This is your current list:");
        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (s.hasNextLine()) {
            String[] arr = s.nextLine().split(" \\| ");
            if (arr[0].equals("T")) {
                Task currTask = new Todo(arr[2]);
                if (arr[1].equals("Complete")) {
                    currTask.setDone();
                } else {
                    currTask.setNotDone();
                }
                taskList.add(currTask);
                System.out.println(currTask);
            } else if (arr[0].equals("D")) {
                Task currTask = new Deadline(arr[2], LocalDate.parse(arr[3], DateTimeFormatter.ofPattern("MMM dd yyyy")));
                if (arr[1].equals("Complete")) {
                    currTask.setDone();
                } else {
                    currTask.setNotDone();
                }
                taskList.add(currTask);
                System.out.println(currTask);
            } else {
                Task currTask = new Event(arr[2], arr[3], arr[4]);
                if (arr[1].equals("Complete")) {
                    currTask.setDone();
                } else {
                    currTask.setNotDone();
                }
                taskList.add(currTask);
                System.out.println(currTask);
            }
        }
        if (taskList.isEmpty()) {
            System.out.println("Your current list does not have any tasks");
        }
        return taskList;
    }

    public static void writeToFile(String filePath, ArrayList<String> lines) {
        try {
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }

    public ArrayList<Task> load() throws IOException {
        return readFileContents(this.filePath);
    }

    public void write(String filePath, ArrayList<String> lines) throws IOException {
        if (!Files.exists(Paths.get("./data"))) {
            Files.createDirectory(Paths.get("./data"));
        }
        writeToFile(filePath, lines);
    }
}
