package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadlines;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

public class Storage {
    private File file;

    public Storage(String path) {
        this.file = new File(path);
    }

    public ArrayList<Task> loadTaskListFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // use "," as delimiters
                String[] splits = line.split(",");
                switch (splits[0]) {
                case "T":
                    tasks.add(new ToDos(splits[2]));
                    break;
                case "D":
                    tasks.add(new Deadlines(splits[2], splits[3].strip()));
                    break;
                case "E":
                    tasks.add(new Event(splits[2], splits[3].strip(), splits[4].strip()));
                    break;
                }
                if (splits[1].strip() == "0") {
                    tasks.get(tasks.size() - 1).markAsNotDone();
                } else {
                    tasks.get(tasks.size() - 1).markAsNotDone();
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // Admin Message for Generation
            System.out.println("File has been generated.");
            try {
                this.file.createNewFile();
            } catch (IOException ex) {
                UI.updateUserOnError(ex);
            }
        }

        return tasks;
    }

    public void updateFileStatus(int index, boolean status) {
        ArrayList<String> fileContents = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNextLine()) {
                fileContents.add(fileScanner.nextLine());
            }
            fileScanner.close();

            String s = fileContents.get(index);
            String replacement = status ? "1" : "0";
            fileContents.set(index, s.replaceFirst("0|1", replacement));
            FileWriter fw = new FileWriter(this.file);
            for (String line : fileContents) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            UI.updateUserOnError(e);
        }
    }

    public void removeFileTask(int index) {
        ArrayList<String> fileContents = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNextLine()) {
                fileContents.add(fileScanner.nextLine());
            }
            fileScanner.close();

            fileContents.remove(index);
            FileWriter fw = new FileWriter(this.file);
            for (String line : fileContents) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            UI.updateUserOnError(e);
        }
    }

    public void updateFileTasks(String details) {
        try {
            FileWriter fw = new FileWriter(this.file, true);
            fw.write(details + "\n"); // for readibility
            fw.close();
        } catch (IOException e) {
            UI.updateUserOnError(e);
        }
    }
}
