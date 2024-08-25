package reo;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void writeFile_emptyFileAndValidTodo() {
        try {
            // Create a test file to write to.
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdir();
                System.out.println("Created new directory to store data.");
            }
            File f = new File("./data/test.txt");
            if (f.createNewFile()) {
                System.out.println("Created new file for testing.");
            }

            // Clear file to delete data from previous tests.
            new PrintWriter("./data/test.txt").close();

            Storage storage = new Storage("./data/test.txt");

            // Dummy Todo object
            Todo todo = new Todo("Test todo item", false);
            storage.writeFile(todo);

            Scanner s = new Scanner(f);
            String result = "";
            while (s.hasNext()) {
                String line = s.nextLine();
                result += line;
            }

            String expected = "T | 0 | Test todo item";
            assertEquals(expected, result);

        } catch (IOException e) {
            System.out.println("Test error.");
        }
    }

    @Test
    public void writeFile_nonEmptyFileAndValidTodo() {
        try {
            // Create a test file to write to.
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdir();
                System.out.println("Created new directory to store data.");
            }
            File f = new File("./data/test.txt");
            if (f.createNewFile()) {
                System.out.println("Created new file for testing.");
            }

            // Clear file to delete data from previous tests.
            new PrintWriter("./data/test.txt").close();

            Storage storage = new Storage("./data/test.txt");

            // Dummy Todo object
            Todo todo = new Todo("Test todo item", false);
            FileWriter fw = new FileWriter("./data/test.txt", true);
            String text = "Header";
            fw.write(text);
            fw.close();
            storage.writeFile(todo);

            Scanner s = new Scanner(f);
            String result = "";
            while (s.hasNext()) {
                String line = s.nextLine();
                result += line;
            }

            String expected = "HeaderT | 0 | Test todo item";
            assertEquals(expected, result);

        } catch (IOException e) {
            System.out.println("Test error.");
        }
    }

    @Test
    public void writeFile_emptyFileAndInvalidTodo_exceptionThrown() {
        try {
            // Create a test file to write to.
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdir();
                System.out.println("Created new directory to store data.");
            }
            File f = new File("./data/test.txt");
            if (f.createNewFile()) {
                System.out.println("Created new file for testing.");
            }

            // Clear file to delete data from previous tests.
            new PrintWriter("./data/test.txt").close();

            Storage storage = new Storage("./data/test.txt");

            // Dummy invalid Todo object
            Todo todo = null;
            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                storage.writeFile(todo);
            });
        } catch (IOException e) {
            System.out.println("Test error.");
        }

    }
}
