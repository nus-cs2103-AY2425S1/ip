package wenjieBot;

import wenjieBot.tasks.Deadline;
import wenjieBot.tasks.Event;
import wenjieBot.tasks.Task;
import wenjieBot.tasks.ToDo;
import wenjieBot.exceptions.NoFileException;

import java.io.File;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.BufferedWriter;

public class Storage {

    private String filePath;
    private ArrayList<Task> tasks = new ArrayList<>(0);


    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            readTasks();
        } catch (NoFileException e) {
            System.out.println("Bro I can't find a file to retrieve the data, \n" +
                    " can help lobang me and create a file pls");
        }

    }

    public ArrayList<Task> load() {
        return tasks;
    }

    public void readTasks() throws NoFileException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new NoFileException();
        } else {
            try {
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                String[] parts = content.split("\n");

                if (!content.isBlank()) {
                    for (String stringifiedTask : parts) {
                        boolean isDone = stringifiedTask.charAt(4) == '1';
                        Task taskToAdd = getTask(stringifiedTask, isDone);

                        tasks.add(taskToAdd);
                    }
                }

            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }

    private static Task getTask(String stringifiedTask, boolean isDone) {
        String description = stringifiedTask.substring(8);
        Task taskToAdd = null;

        switch (stringifiedTask.charAt(0)) {
            case 'T': {
                taskToAdd = new ToDo(description);
                break;
            }
            case 'D': {
                String[] segments = description.split("/");
                int index = 0;
                for(int i = 0; i < description.length(); i++) {
                    if (description.charAt(i) == '/') {
                        index = i + 5;
                        break;
                    }
                }
                taskToAdd = new Deadline(segments[0], description.substring(index));
                break;
            }
            case 'E': {
                String[] segments = description.split("/");
                taskToAdd = new Event(segments[0], segments[1].substring(6), segments[2].substring(4));

                break;
            }
        }

        if (isDone) {
            taskToAdd.setStatusIcon(true);
        } else {
            taskToAdd.setStatusIcon(false);
        }
        return taskToAdd;
    }

    public void writeTasks() {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                throw new FileNotFoundException();
            } else {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

                    StringBuilder formattedString = new StringBuilder();


                    for (Task task : tasks) {
                        formattedString.append(task.toPrettierString());
                        formattedString.append("\n");
                    }

                    writer.write(formattedString.toString());
                    writer.flush();


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
