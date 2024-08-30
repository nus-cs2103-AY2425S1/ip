package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String path = "src/main/data/task_list.txt";
    File file;
    Scanner scanner;
    FileWriter fileWriterAmend;
    FileWriter fileWriterReplace;

    public Storage() throws IOException {
        file = new File(path);
    }

    public void createFile() throws IOException {
        file.createNewFile();
    }

    public StringBuilder read() throws FileNotFoundException {
        scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine() + "\n");
        }

        return sb;
    }

    public void mark(int lineNo) throws IOException {
        int count = 1;
        scanner = new Scanner(file);
        fileWriterAmend = new FileWriter(path,true);
        StringBuilder updated_text = new StringBuilder();
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (count == lineNo) {
                String[] tokens = s.split("\\[_]");
                String new_line = tokens[0] + "[X]" + tokens[1];
                updated_text.append(new_line).append("\n");
            } else {
                updated_text.append(s).append("\n");
            }
            count++;
        }

        clear();
        fileWriterAmend.write(updated_text.toString());
        fileWriterAmend.close();
    }

    public void unmark(int lineNo) throws IOException {
        int count = 1;
        scanner = new Scanner(file);
        fileWriterAmend = new FileWriter(path,true);
        StringBuilder updated_text = new StringBuilder();
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (count == lineNo) {
                String[] tokens = s.split("\\[X]");
                String new_line = tokens[0] + "[_]" + tokens[1];
                updated_text.append(new_line).append("\n");
            } else {
                updated_text.append(s).append("\n");
            }
            count++;
        }

        clear();
        fileWriterAmend.write(updated_text.toString());
        fileWriterAmend.close();
    }

    public void delete(int lineNo) throws IOException {
        int count = 1;
        int lineNumber = 1;
        scanner = new Scanner(file);
        fileWriterAmend = new FileWriter(path,true);
        StringBuilder updated_text = new StringBuilder();
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] tokens = s.split("\\.");
            if (count != lineNo) {
                updated_text.append(lineNumber).append(".").append(tokens[1]).append("\n");
                lineNumber++;
            }
            count++;
        }

        clear();
        fileWriterAmend.write(updated_text.toString());
        fileWriterAmend.close();
    }

    public int getLineNumber() throws FileNotFoundException {
        int count = 1;
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            count++;
            scanner.nextLine();
        }

        return count;
    }

    private void clear() throws IOException {
        fileWriterReplace = new FileWriter(path);
        fileWriterReplace.write("");
        fileWriterReplace.close();
    }

    public void write(String text) throws IOException {
        fileWriterAmend = new FileWriter(path, true);
        fileWriterAmend.write(text + "\n");
        fileWriterAmend.close();
    }

}
