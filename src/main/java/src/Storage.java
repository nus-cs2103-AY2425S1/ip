package src;

import java.io.File;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


public class Storage {

    private String filePath;
    private ArrayList<Task> tasks = new ArrayList<>(0);


    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            readTasks();
        } catch (FileNotFoundException e) {
            System.out.println("Bro I can't find a file to retrieve the data, \n" +
                    " can help lobang me and create a file pls");
        }

    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void readTasks() throws FileNotFoundException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException();
        } else {
            try {
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                String[] parts = content.split("\n");
                for (int i = 0; i < parts.length - 1; i++) {
                    String stringifiedTask = parts[i];

                    boolean isDone = stringifiedTask.charAt(4) == '1';
                    String description = stringifiedTask.substring(8);
                    Task taskToAdd = null;

                    switch (stringifiedTask.charAt(0)){
                        case 'T': {
                           taskToAdd = new ToDo(description);
                        }
                        case 'D': {
                            String[] segments = description.split("/");
                            taskToAdd = new Deadline(segments[0], segments[1].substring(4));
                        }
                        case 'E': {
                            String[] segments = description.split("/");
                            taskToAdd = new Event(segments[0], segments[1].substring(6), segments[2].substring(4));
                        }
                    }

                    if (isDone) {
                        taskToAdd.setStatusIcon(true);
                    } else {
                        taskToAdd.setStatusIcon(false);
                    }

                    tasks.add(taskToAdd);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }

    public void writeTasks() {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                throw new FileNotFoundException();
            } else {
                try {
                    FileWriter writer = new FileWriter(filePath);
                    String formattedString = "";

                    for (Task task : tasks) {
                        formattedString += task.toPrettierString();
                        formattedString += "\n";
                    }

                    writer.write(formattedString);

                } catch (IOException e) {
                    System.out.println("An error occurred while saving the file.");
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Bro I can't find a file to store the data, \n" +
                    " can help lobang me and create a file pls");
        }




    }

}
