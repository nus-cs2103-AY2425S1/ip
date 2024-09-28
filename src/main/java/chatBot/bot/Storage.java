package chatbot.bot;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chatbot.task.Task;

/** Storage handles the loading and writing to filepath provided */
public class Storage {
    private File file;
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
        try {
            this.file = new File(filePath);
        } catch (NullPointerException e) {
            System.out.println("pathname unrecognised");
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        }
    }

    ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                Task t = Parser.parseTask(s);
                if (t == null) {
                    continue;
                }
                tasks.add(t);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        sc.close();
        file.delete();
        return tasks;
    }

    /** Writes to file given string s */
    public void writeToFile(String s) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(s);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
