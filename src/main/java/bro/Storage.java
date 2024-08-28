package bro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String PATH;
    private final TaskList tasks;

    public Storage(String path, TaskList tasks) {
        this.PATH = path;
        this.tasks = tasks;
    }
    public void saveToFile() {
        try {
            FileWriter f = new FileWriter(PATH);
            f.write(this.toString());
            f.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void loadIn() throws FileNotFoundException, BroException {
        File f = new File(PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String curr = s.nextLine();
            switch (curr.charAt(3)) {
                case 'T':
                    tasks.addTodo(curr.split("] ", 2)[1]);
                    if (curr.charAt(6) == 'X') {
                        tasks.get(tasks.size() - 1).mark();
                    }
                    break;
                case 'E':
                    tasks.addEvent(curr.split("] ", 2)[1]);
                    if (curr.charAt(6) == 'X') {
                        tasks.get(tasks.size() - 1).mark();
                    }
                    break;
                case 'D':
                    tasks.addDeadline(curr.split("] ", 2)[1]);
                    if (curr.charAt(6) == 'X') {
                        tasks.get(tasks.size() - 1).mark();
                    }
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }
}
