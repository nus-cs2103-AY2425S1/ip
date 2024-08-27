package main;

import exception.DukeException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private final String path;
    FileWriter fw;
    File f;
    public Storage(String path) {
        this.path = path;
        f = new File(this.path);
    }

    public ArrayList<Task> readStorage() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String response = s.nextLine();
                String[] splited = response.split(" ", 3);
                if (splited[1].equals("todo") || splited[1].equals("event") || splited[1].equals("deadline")) {
                    Task current = null;
                    String[] taskDetails;
                    try {
                        switch (splited[1]) {
                            case "todo":
                                current = new Todo(splited[2]);
                                break;
                            case "event":
                                taskDetails = splited[2].split(" /from ");
                                String[] taskTimings = taskDetails[1].split(" /to ");
                                current = new Event(taskDetails[0], taskTimings[0], taskTimings[1]);
                                break;
                            case "deadline":
                                taskDetails = splited[2].split(" /by ");
                                current = new Deadline(taskDetails[0],taskDetails[1]);
                                break;
                        }
                        if ("1".equals(splited[0])) {
                            current.mark();
                        }
                        taskList.add(current);
                    } catch (DukeException e) {
                        System.out.println("________________________________");
                        System.out.println(e.getMessage() + "________________________________");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    public void writeStorage(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(this.path,false);
            for (Task curr : taskList) {
                fw.write(curr.getStorageFormat());
            }
            fw.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
