package chatbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles reading and writing to files.
 *
 * @author celeschai
 */
public class Storage {
    private Scanner sc;

    /**
     * Gets scanner for parsing of text file contents.
     *
     * @return Scanner that reads source file stored locally.
     */
    public Scanner getScanner() {
        return this.sc;
    }

    /**
     * Reads tasks from text file
     * Creates new file if it does not exist.
     *
     * @return True if file is successfully read.
     */
    public boolean readFromTaskListFile() {
        try {
            File file = new File("validTaskList.txt");

            boolean isNowExist = file.createNewFile();
            if (isNowExist) {
                System.out.println("initialised empty task list on local disk");
            }
            this.sc = new Scanner(file);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Writes tasks to text file.
     *
     * @param taskList String representation of task list.
     * @return True if task list is successfully saved.
     */
    public boolean writeToTaskListFile(String taskList) {
        try {
            File file = new File("validTaskList.txt");

            boolean isNowExist = file.createNewFile();
            if (isNowExist) {
                System.out.println("initialised empty task list on local disk");
            }
            FileWriter fw = new FileWriter(file);
            fw.write(taskList);
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
