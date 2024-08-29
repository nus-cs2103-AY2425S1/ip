package boss;// deals with loading tasks from the file and saving tasks in the file
import boss.Deadline;
import boss.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void writeToFile(String textToAdd, boolean appendorNot) throws IOException {
        FileWriter fw = new FileWriter(filePath, appendorNot);
        fw.write(textToAdd);
        fw.close();
    }

    public int numofTasks() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int i = 1;
        while (s.hasNext()) {
            String str = s.nextLine();
            i++;
        }
        return i;
    }

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

    public Task typeOfTask(String description, String str, boolean isDone) {
        // todo
        if (str.contains("[T]")) {
            return new Todo(description, isDone);
        } else if (str.contains("[D]")) {

            String[] string = description.split("\\| ");

            String deadline = string[1].split("by: ")[1];

            return Deadline.of(string[0], deadline, isDone);

        } else if (str.contains("[E]")) {
            String[] newStr = description.split("\\| ");

            String from = newStr[1].split("from:")[1];
            String to = newStr[2].split("to:")[1];

            return new Event(newStr[0], from, to, isDone);

        } else {
            return new Task(description, isDone);
        }
    }


}
