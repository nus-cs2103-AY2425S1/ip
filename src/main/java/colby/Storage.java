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
                String nextLine = scanner.nextLine();;
                if (nextLine.contains("[X]")) {
                    if (nextLine.contains("[T]")) {
                        String fullDescription = nextLine.split(" ")[1];
                        if (fullDescription.split("").length > 1) {
                            list.addTask(new ToDo(fullDescription.split("\\(needs\\) ")[0] +
                                    "/needs" + fullDescription.split("\\(needs\\)")[1]));
                        } else {
                            list.addTask(new ToDo(fullDescription));
                        }
                    } else if (nextLine.contains("[D]")) {
                        
                    }
                }



                Task task = Parser.parseToDoTask(scanner.nextLine());
                list.addTask(task);
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
