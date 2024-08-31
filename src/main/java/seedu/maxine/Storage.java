package seedu.maxine;

import seedu.maxine.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private String filePath;
    private FileParser fileParser;

    /**
     * Constructs new instance of Storage class
     * @param filePath The path to the file where the data is stored or will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileParser = new FileParser();
    }

    /**
     * Returns a collection of tasks from TaskList which matches the input keyword
     * @param search Keyword to be searched
     * @return a collection of tasks which matches the input keyword
     */
    public ArrayList<Task> queryStorage(String search) {
        ArrayList<Task> currList = this.load();
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : currList) {
            if (task.toString().contains(search)) {
                results.add(task);
            }
        }
        return results;
    }


    /**
     * Returns the most updated collection of tasks. 
     * The method reads the txt file and parses it to convert it into 
     * its corresponding task. The tasks are then added to an ArrayList 
     * @return ArrayList of collection of current tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String line = s.nextLine();
                Task task = fileParser.parse(line);
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oh no! I can't seem to find the file :(");
        } 
        return list;
    }


    /**
     * Adds new lines to the txt file, based on updated list.
     * @param list updated TaskList
     */
    public void refreshStorage(MaxineList list) {

        File file = new File(filePath);

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(file, false))) {
            for (Task item : list) {
                writer.write(item.writeToFile());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.print("Error in File Writing java\n");
        }
    }
}
