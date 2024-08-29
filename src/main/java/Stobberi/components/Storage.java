package Stobberi.components;

import Stobberi.StobberiException.NotPossibleDurationStobberiException;
import Stobberi.Task.Deadline;
import Stobberi.Task.Event;
import Stobberi.Task.Task;
import Stobberi.Task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> getList() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                case "T":
                    taskList.add(new Todo(parts[1], parts[2]));
                    break;
                case "D":
                    taskList.add(new Deadline(parts[1], parts[2], parts[3]));
                    break;
                case "E":
                    taskList.add(new Event(parts[1], parts[2], parts[3], parts[4]));
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error found: " + e.getMessage());
        } catch (NotPossibleDurationStobberiException e) {
            System.out.println(e.getMessage());
        }

        return taskList;
    }

    public void saveList(ArrayList<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                String taskLine = "";
                if (task instanceof Todo) {
                    taskLine += "T | ";
                    taskLine += task.getDescription();
                } else if (task instanceof Deadline deadline) {
                    taskLine += "D | ";
                    taskLine += task.getDescription();
                    taskLine += " | ";
                    taskLine += deadline.getDeadlineOfTask();
                } else if (task instanceof Event event) {
                    taskLine += "E | ";
                    taskLine += task.getDescription();
                    taskLine += " | ";
                    taskLine += event.getStartOfEvent();
                    taskLine += " | ";
                    taskLine += event.getEndOfEvent();
                }
                if (task.getDone()) {
                    taskLine += " | 1\n";
                } else {
                    taskLine += " | 0\n";
                }
                writer.write(taskLine);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error found: " + e.getMessage());
        }
    }
}