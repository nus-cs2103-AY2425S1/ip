package blacknut.ui;

import java.io.*;
import java.util.ArrayList;
import blacknut.ui.BlacknutExceptions.IncorrectFormatException;


class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                tasks.add(task);
            }
            reader.close();
        } catch (java.io.IOException | BlacknutExceptions.IncorrectFormatException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}