package reo.storage;

import reo.contacts.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class ContactStorage {
    private String filePath;
    public ContactStorage(String filePath) {
        assert filePath != null : "File path cannot be null";
        assert !filePath.isEmpty() : "File path cannot be empty";
        this.filePath = filePath;
    }
    public ArrayList<Contact> readFile() {
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            final String DIRECTORY_PATH = "./data";
            File dir = new File(DIRECTORY_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File f = new File(filePath);
            if (f.createNewFile()) {
                return contacts;
            }

            try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
                lines.forEach(line -> {
                    String[] split = line.split("\\|");

                    String name = split[0].trim();
                    String phoneNumber = split[1].trim();
                    String address = split[2].trim();

                    Contact contact = new Contact(name, phoneNumber, address);
                    contacts.add(contact);

                });
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Ensure the file path starts with ./data");
        } catch (IOException e) {
            System.out.println("IO Error! Ensure the file path starts with ./data");
        }

        return contacts;
    }

    public void writeFile(Contact contact) {
        if (contact == null) {
            throw new NullPointerException();
        }
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String text = contact.toString() + System.lineSeparator();
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeLine(int lineNumber) {
        assert lineNumber > 0 : "Line number must be greater than 0";
        try {
            final String TEMPORARY_FILE_PATH = "./data/temp.txt";
            File f = new File(filePath);
            File temp = new File(TEMPORARY_FILE_PATH);
            FileWriter tw = new FileWriter(temp, false);
            Scanner s = new Scanner(f);
            int lineCount = 1;

            while (s.hasNext()) {
                String line = s.nextLine();
                if (lineNumber != lineCount) {
                    tw.write(line + System.lineSeparator());
                }
                lineCount ++;
            }
            tw.close();
            s.close();

            Files.move(temp.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }
}
