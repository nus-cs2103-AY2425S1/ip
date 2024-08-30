package Elon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private final String path;

    private File file;

    public Storage(String path) throws IOException {
        this.path = path;
        this.file = new File(this.path);
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("Failed to create file");
            }
        } else {
            System.out.println("File exists");
        }
    }

    private Task parseTask(String line) throws ElonException {
        String[] elements = line.split(" \\| ");
        String type = elements[0];
        boolean isDone = elements[1].equals("1");
        String description = elements[2];

        switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                LocalDate by = LocalDate.parse(elements[3]);
                return new Deadline(description, isDone, by);
            case "E":
                LocalDate start = LocalDate.parse(elements[3]);
                LocalDate end = LocalDate.parse(elements[4]);
                return new Event(description, isDone, start, end);
            default:
                throw new ElonException("Invalid task format.");
        }
    }


    public ArrayList<Task> loadFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        if (this.file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    try {
                        Task task = parseTask(line);
                        taskList.add(task);
                    } catch (ElonException e) {
                        System.out.println(e);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
        return taskList;
    }

    public void saveFile(TaskList list) throws IOException {
        try(FileWriter fileWriter = new FileWriter(this.file)) {
            int i = 0;
            while (i < list.listSize()) {
                Task task = list.getTask(0);
                fileWriter.write(task.toFileString() + "\n");
                i++;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
