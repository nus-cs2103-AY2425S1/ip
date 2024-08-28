package storage;

import task.*;
import ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static final String FILE_PATH = "data/bob.txt";
    private Ui ui = new Ui();

    public void loadTasks(TaskList tl) {
        File f = new File(FILE_PATH);
        try {
            // For file
            Scanner s1 = new Scanner(f);
            while (s1.hasNext()) {
                String t = s1.nextLine();
                String[] task_list = t.trim().split(" \\| ");
                Task x;
                switch (task_list[0]) {
                case ("T"):
                    x = new ToDo(task_list[2]);
                    tl.addTask(x);
                    break;
                case ("D"):
                    x = new Deadline(task_list[2], task_list[3]);
                    tl.addTask(x);
                    break;
                case ("E"):
                    x = new Event(task_list[2], task_list[3], task_list[4]);
                    tl.addTask(x);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + task_list[0]);
                }
                if (task_list[1].equals("1")) {
                    x.markAsDone();
                }
            }
        } catch (FileNotFoundException ignored) {
        }

    }

    public void prepareSave() {
        try {
            Files.createDirectories(Path.of("data")); // Hard-coded
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.close();
        } catch (IOException ignored) {
            this.ui.savingError();
        }
    }
    public void saveTask(String s) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(s + System.lineSeparator());
            fw.close();
        } catch (IOException ignored) {
            this.ui.savingError();
        }
    }
}
