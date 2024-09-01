package elysia.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static ArrayList<Task> arrayLists;

    public Storage(ArrayList<Task> arrayLists) {
        this.arrayLists = arrayLists;
    }

    /**
     * Load the saved list
     * @param filePath
     * @return the saved arraylist
     * @throws FileNotFoundException
     */
    public void scanFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);


        if (f.exists() && f.isFile()) {
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] str = input.split(" \\| ");

                if (str[0].equals("T")) {
                    addToDos(str[2], Integer.parseInt(str[1]));
                }

                if (str[0].equals("D")) {
                    addDeadline(str[2], Integer.parseInt(str[1]), str[3]);
                }

                if (str[0].equals("E")) {
                    addEvent(str[2], Integer.parseInt(str[1]), str[3], str[4]);
                }
            }
        }
    }

    private void addToDos(String s, int i) {

        Task task = new ToDos(s);
        if (i == 1) {
            task.markAsDone();
        }
        arrayLists.add(task);
    }


    private void addDeadline(String s, int i, String by) {
        Task task = new Deadline(s, DateParser.parseDate(by));
        if (i == 1) {
            task.markAsDone();
        }
        arrayLists.add(task);
    }
    private void addEvent(String s, int i, String from, String to) {

        Task task = new Event(s, from, to);
        if (i == 1) {
            task.markAsDone();
        }
        arrayLists.add(task);
    }

    private void createFile(String folderName, String fileName) throws IOException {
        File dataDir = new File(folderName);

        if (!dataDir.exists()) {
            dataDir.mkdir();

        }

        if (dataDir.exists() && dataDir.isDirectory()) {
            File txtFile = new File(dataDir, fileName);

            try {
                if (txtFile.createNewFile()) {
                    System.out.println(fileName + " successfully created.");
                } else {
                    System.out.println(fileName + " already exists");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the " + fileName);
            }
        }
    }

    private void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void handleExit(String folderName, String fileName, String filePath) throws IOException {
        createFile(folderName, fileName);

        try {
            writeToFile(filePath, arrayLists.get(0).saveToTxt());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        for (int i = 1; i < arrayLists.size(); i++) {
            appendToFile(filePath, "\n" + arrayLists.get(i).saveToTxt());
        }
    }
}
