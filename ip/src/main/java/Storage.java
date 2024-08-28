import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Storage {
    File storage;
    public static Storage instance;
    public static final String LOAD_SAVE = "src/Data/SavedData.txt";
    public Storage() {
        storage = new File(LOAD_SAVE);
        try {
            if (!storage.exists()) {

                // Create the file if it doesn't exist and write initial data
                if (storage.createNewFile()) {
                    System.out.println("File created: " + storage.getName());
                } else {
                    System.out.println("File already exists.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating or writing to the file.");
            e.printStackTrace();
        }
    }

    public void delete(int i){
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOAD_SAVE));
            lines.remove(i);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOAD_SAVE))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();  // Ensure each line ends with a new line character
                }
            }

        } catch(java.io.IOException e) {
            System.out.println("errornous entry");
            e.printStackTrace();
        }
    }

    public void mark(int i) {
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(LOAD_SAVE));

            // Get the specific line and replace [ ] with [X]
            String lineToUpdate = lines.get(i);
            String updatedLine = lineToUpdate.replace("[ ]", "[X]");
            lines.set(i, updatedLine);

            // Write the updated lines back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOAD_SAVE))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();  // Ensure each line ends with a new line character
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void unmark(int i) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOAD_SAVE));


            // Replace [X] with [ ] on the specified line
            String updatedLine = lines.get(i).replace("[X]", "[ ]");
            lines.set(i, updatedLine);

            // Write the updated lines back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOAD_SAVE))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void add(String s) {
        try {
            FileWriter myWriter = new FileWriter(LOAD_SAVE, true);
            myWriter.write(s);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void list() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOAD_SAVE));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    public void clearFile() {
        try {
            FileWriter myWriter = new FileWriter(LOAD_SAVE, false);
            myWriter.close();
            System.out.println("File cleared successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the file.");
            e.printStackTrace();
        }
    }

    public static Storage getInstance() {
        if(instance == null){
            instance = new Storage();
        }
        return instance;
    }
}


