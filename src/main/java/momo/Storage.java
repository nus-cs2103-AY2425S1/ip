package momo;

import momo.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    // Should handle file creation, file loading, load into list
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String load() {
        StringBuilder fileString = new StringBuilder();
        try {
            File f = new File("data/momo.txt"); // Create a File for the given file path
            Scanner s = new Scanner(f); // Create a Scanner using the File as the source
            String nextLine;

            // Write input line by line into fileString
            while (s.hasNext()) {
                nextLine = s.nextLine();
                fileString.append(nextLine).append("\n");
            }

            s.close();

        } catch (FileNotFoundException e) {
            File f = new File("data/momo.txt");
            File d = new File("data");

            try {
                if (d.mkdirs() || d.exists()) {
                    if (f.createNewFile()) {
                        System.out.println("File and/or folder created successfully");
                    }
                }
            }
            catch (IOException ioe) {
                System.out.println("Error in creating a file and folder ");
            }


        }
        return fileString.toString();

    }


    public void addTaskToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    public void RewriteTasksToFile(String filePath, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            fw.write(task.toFileString() + "\n");
        }
        fw.close();
    }


}
