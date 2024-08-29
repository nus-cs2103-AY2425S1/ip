package serenity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() throws IOException, SerenityException {
        //create directory if directory does not exist
        Files.createDirectories(Paths.get("data"));
        File f = new File(filePath);

        //create file if file does not exist
        if (!f.exists()) {
            Files.createFile(Paths.get(filePath));
        }

        Scanner sc = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            tasks.add(loadTask(sc.nextLine()));
        }
        sc.close();
        return tasks;
    }

    public Task loadTask(String taskDescription) throws SerenityException {
        String[] parts = taskDescription.split("\\|");
        Task t;

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].strip();
        }

        String taskType = parts[0];

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

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    public void saveTask(Task t) throws IOException {
        String textToAppend = t.formatData();
        appendToFile(textToAppend);
    }

    public void writeToFile(TaskList tasks) throws IOException {
        //overwrite
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks.toDataFormat());
        fw.close();
    }

}
