package boss;

import boss.tasks.Deadline;
import boss.tasks.Event;
import boss.tasks.Task;
import boss.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Represents the class that deals with loading tasks
 * from the file and saving tasks in the file
 */

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Prints the tasks from the textfile when user
     * uses the "list" command
     * @throws FileNotFoundException throws an exception
     * if file can not be found
     */

    public void printFileContents() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int i = 1;
        while (s.hasNext()) {
            String str = s.nextLine();
            System.out.println(i + ". " + str);

            i++;
        }
    }

    public String printTasks() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int i = 1;
        String result = "";
        while (s.hasNext()) {
            String str = s.nextLine();
            result = result + i + ". " + str + "\n";
            i++;
        }
        return result;
    }

    /**
     * Method to write data to file
     * @param textToAdd text to write to file
     * @param appendorNot boolean value to append to text or replace
     * @throws IOException throws exception if error is caused
     */
    public void writeToFile(String textToAdd, boolean appendorNot) throws IOException {
        FileWriter fw = new FileWriter(filePath, appendorNot);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Loads the content from text file into arraylist
     * when program is rerun.
     * @return an arraylist containing tasks from text file
     */

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            List<String> lst = Files.readAllLines(Path.of(filePath));

            for (String str : lst) {
                String[] arr = str.split("] ");
                String s = arr[arr.length - 1];
                if (arr[0].contains("X") || arr[1].contains("X")) {
                    Task task = typeOfTask(s, str, true);
                    tasks.add(task);
                } else {
                    Task task = typeOfTask(s, str, false);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Method to determine the type of task
     * @param description description of task
     * @param str string representation of task
     * @param isDone status of task
     * @return Task
     */

    public Task typeOfTask(String description, String str, boolean isDone) {
        // todo
        if (str.contains("[T]")) {
            return new Todo(description, isDone);
        } else if (str.contains("[D]")) {

            String[] string = description.split("\\| ");

            String deadline = string[1].split("by: ")[1];

            return new Deadline(string[0], isDone, deadline);

        } else if (str.contains("[E]")) {
            String[] newStr = description.split("\\| ");

            String from = newStr[1].split("from: ")[1];
            String to = newStr[2].split("to: ")[1];

            return new Event(newStr[0], from, to, isDone);

        } else {
            return new Task(description, isDone);
        }
    }


}
