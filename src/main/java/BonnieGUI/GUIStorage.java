package BonnieGUI;

import Exceptions.*;
import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUIStorage {

    static private File f;
    static private String filePath = "src/main/java/Files/tasks.txt";

    // Prepare the file for reading and writing
    protected static void init() {
        f = new File(filePath);
        try {
            if (!f.exists()) {
                f.createNewFile();
            } else {
                initialiseFile(f);
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating file..");
            e.printStackTrace();
        }
    }

    // Note that initialiseFile should only be used within the storage
    private static void initialiseFile(File file) {
        try {
            // Idea is to copy the stuff from the tasks.txt to populate the tasklist array (so the app has the tasks)
            Scanner myReader = new Scanner(file);
            ArrayList<ArrayList<String>> results = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<String> subresult = new ArrayList<>();
                Pattern pattern = Pattern.compile("\\[(.*?)\\]");
                Matcher matcher = pattern.matcher(data);

                while (matcher.find()) {
                    subresult.add(matcher.group(1));
                }
                results.add(subresult);
            }
            myReader.close();

            for (ArrayList<String> lst : results) {
                if (lst.get(0).equals("ToDo")) {
                    GUITaskList.addTask(new Todo(lst.get(2)));
                } else if (lst.get(0).equals("Deadline")) {
                    GUITaskList.addTask(new Deadline(lst.get(2), lst.get(3)));
                } else if (lst.get(0).equals("Event")) {
                    GUITaskList.addTask(new Event(lst.get(2), lst.get(3), lst.get(4)));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when starting the app.");
            e.printStackTrace();
        }
    }

    protected static void updateFile() {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < GUITaskList.getSize(); i++) {
                writer.write(String.format("%s\n", GUITaskList.getTasks().get(i).toString()));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error updating tasks file!");
            e.printStackTrace();
        }
    }
}
