package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileStorage extends Storage {
    protected String dirPath;
    protected File file;

    public FileStorage(String dirPath) {
        this.dirPath = dirPath;

        // Creates all folders if not exits
        try {
            Files.createDirectories(Paths.get(dirPath));

        } catch (IOException e) {
            // Handle error

        }

        // Creates file if not exists
        file = new File(this.dirPath + "/storage.txt");

        // Create file if not exists
        try {
            file.createNewFile();

        } catch (IOException ioException) {
            System.out.println("Unable to create task save file at path " + file.getAbsolutePath()
                    + "\n" + ioException.getMessage());

        }
    }

    @Override
    public List<String> load() {
        try (Scanner fs = new Scanner(file)) {
            List<String> res = new ArrayList<String>();

            while (fs.hasNextLine()) {
                String line = fs.nextLine();

                res.add(line);
            }

            fs.close();

            return res;
        } catch (FileNotFoundException e) {
            // Print error
            System.out.println("Unable to load data in storage:\n" + e.getMessage());

            // Return empty ArrayList
            return new ArrayList<String>();

        }

    }

    @Override
    public List<String> store(String elem) {
        try {
            // Append to previous save
            FileWriter fw = new FileWriter(file, true);
            fw.write(elem + "\n");
            fw.close();

        } catch (IOException e) {
            // Print error
            System.out.println("Unable to add element:\n" + e.getMessage());

        }

        return load();

    }


    public List<String> update(List<String> data) {
        try {
            // Overwrite previous save
            FileWriter fw = new FileWriter(file);

            for (String line : data) {
                fw.write(line + "\n");
            }

            fw.close();

        } catch (IOException e) {
            // Print error
            System.out.println("Unable to update data:\n" + e.getMessage());

        }

        return load();
    }

    // public static void main(String[] args) {
    //     FileStorage fs = new FileStorage("data");

    //     try {

    //         fs.store("null");
    //     } catch (IOException e) {

    //     }
    // }
}
