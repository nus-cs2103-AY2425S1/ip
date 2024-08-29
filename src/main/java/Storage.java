import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Storage {
    String filepath;

    public Storage (String filepath) {
        try {
            this.filepath = filepath;
            int val = filepath.lastIndexOf('/');

    ;       File directory = new File(this.filepath.substring(0,val));
            if (!directory.exists() && directory.mkdir()) {
                System.out.println("New directory created under the filepath: " + this.filepath.substring(0,val));
            }

            File taskFile = new File(this.filepath);
            if (!taskFile.isFile() && taskFile.createNewFile()) {
                System.out.println("New file created under the filepath: " + this.filepath);
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public ArrayList<Task> getStorage () {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            File file = new File(this.filepath);

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                // Segmenting the tasks
                String[] taskLine = fileScanner.nextLine().split("\\|");
                if (taskLine[0].equals("T")) {
                    Task task = new ToDo(taskLine[2]);
                    if (taskLine[1].equals("1")) {
                        task.completeTask();
                    }
                    taskList.add(task);
                } else if (taskLine[0].equals("D")) {
                    Task task = new Deadline(taskLine[2], taskLine[3]);
                    if (taskLine[1].equals("1")) {
                        task.completeTask();
                    }
                    taskList.add(task);
                } else if (taskLine[0].equals("E")){
                    Task task = new Event(taskLine[2], taskLine[3], taskLine[4]);
                    if (taskLine[1].equals("1")) {
                        task.completeTask();
                    }
                    taskList.add(task);
                }
            }
            fileScanner.close();
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
        return new ArrayList<>();
    }

    public void addToStorage (String data) {
        try {
            FileWriter writeToFile = new FileWriter(this.filepath, true);
            writeToFile.write(data + "\n");
            writeToFile.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void editTask (String oldData, String newData) {
        try {
            // Read the file into an array
            List<String> entireFile = new ArrayList<>();
            File file = new File(this.filepath);

            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNextLine()) {
                entireFile.add(fileScanner.nextLine());
            }
            fileScanner.close();

            // Find the line we want to replace and change it
            for (int i = 0; i < entireFile.size(); i ++) {
                if (entireFile.get(i).equals(oldData)) {
                    entireFile.set(i, newData);
                    break;
                }
            }
            // Rewrite the file line by line with new changes
            FileWriter newFile = new FileWriter(this.filepath);
            for(String line: entireFile) {
                System.out.println(line);
                newFile.write(line + "\n");
            }
            newFile.close();

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
