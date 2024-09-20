package colby;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods for managing data being input into data file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Adds the text input to the contents of the file
     * @param textToAppend text that is to be added into the file
     * @throws IOException if an I/O error occurs
     */
    public void appendToFile(String textToAppend) throws IOException {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(textToAppend);
        }
    }

    /**
     * Deletes existing content in the file and adds new content
     * @param tasks list of tasks to be added to the file
     * @throws IOException if an I/O error occurs
     */
    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }

    /**
     * Prints the contents of the file
     * @throws FileNotFoundException when file does not exist
     */
    public String returnFileContents() throws FileNotFoundException {
        File file = new File(filePath);
        String results  = "";
        int n = 1;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                results = results + n + ". " + scanner.nextLine() + "\n";
                n++;
            }
        }
        return results;
    }

    public TaskList buildList() throws FileNotFoundException {
        File file = new File(filePath);
        TaskList list = new TaskList();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] parts = nextLine.split(" ", 2);
                String fullDescription = parts[1];
                boolean isDone = nextLine.contains("[X]");

                if (nextLine.contains("[T]")) {
                    String[] todoParts = fullDescription.split("\\(needs ", 2);
                    Task newTask;
                    if (todoParts.length > 1) {
                        newTask = new ToDo(todoParts[0].trim() + "/needs " + todoParts[1].trim());
                            newTask.isDone = true;
                    } else {
                            newTask = new ToDo(fullDescription);
                    }
                    list.addTask(newTask);

                    if (isDone) {
                        newTask.isDone = true;
                    }

                } else if (nextLine.contains("[D]")) {
                    String[] deadlineParts = fullDescription.split("\\(by: ", 2);
                    if (deadlineParts.length == 2) {
                        Task newTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].replace(")", "").trim());
                        list.addTask(newTask);
                        if (isDone) {
                            newTask.isDone = true;
                        }
                    }
                } else {
                    String[] eventParts = fullDescription.split("\\(from: ", 2);
                    if (eventParts.length == 2) {
                        String[] duration = eventParts[1].split("to:", 2);
                        if (duration.length == 2) {
                            Task newTask = new Event(eventParts[0].trim(), duration[0].trim(), duration[1].replace(")", "").trim());
                            list.addTask(newTask);
                            if (isDone) {
                                newTask.isDone = true;
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    public void checkFile() {
        try {
            File myObj = new File(filePath);
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
