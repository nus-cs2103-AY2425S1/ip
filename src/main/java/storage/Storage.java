package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> initList() {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("No task file found. Creating new file at " + path);
            return new ArrayList<>();
        }
        System.out.println("Reading task list from " + path);
        return parseList();
    }

    public ArrayList<Task> parseList() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    break;
                }
                list.add(parseTask(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + e.getMessage());
        }
        return list;
    }

    // type, name, completed, date1, date2
    public Task parseTask(String line) throws IOException {
        String[] values = line.split(",");
        Task task;
        switch (values[0]) {
            case "td":
                if (values.length != 3) {
                    throw new IOException("Unexpected number of parameters");
                }
                task = new Todo(values[1]);
                if (values[2].equals("y")) {
                    task.setDone();
                }
                return task;
            case "d":
                if (values.length != 4) {
                    throw new IOException("Unexpected number of parameters");
                }
                task = new Deadline(values[1], values[3]);
                if (values[2].equals("y")) {
                    task.setDone();
                }
                return task;
            case "e":
                if (values.length != 5) {
                    throw new IOException("Unexpected number of parameters");
                }
                task = new Event(values[1], values[3], values[4]);
                if (values[2].equals("y")) {
                    task.setDone();
                }
                return task;
            default:
                throw new IOException("Unexpected parameter");
        }
    }

    public void saveList(ArrayList<Task> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.path))){
            for (Task task : list) {
                String str = "";
                if (task instanceof Todo) {
                    str = "td," + task.getName();
                    if (task.isDone()) {
                        str += ",y";
                    } else {
                        str += ",n";
                    }
                }
                if (task instanceof Deadline d) {
                    str += "d," + d.getName();
                    if (task.isDone()) {
                        str += ",y,";
                    } else {
                        str += ",n,";
                    }
                    str += d.getDeadline();
                }
                if (task instanceof Event e) {
                    str += "e," + e.getName();
                    if (task.isDone()) {
                        str += ",y,";
                    } else {
                        str += ",n,";
                    }
                    str += e.getStart() + "," + e.getEnd();
                }
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file " + e.getMessage());
        }
    }
}
