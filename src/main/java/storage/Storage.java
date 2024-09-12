package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * The Storage class handles file operations for task management,
 * including reading from, writing to, and modifying a file that stores tasks.
 * It provides methods to create, read, write, update, and delete lines in the file.
 */
public class Storage {
    private static final String PATH = ".\\src\\main\\data\\task_list.txt";
    private File file;
    private Scanner scanner;
    private FileWriter fileWriterAmend;
    private FileWriter fileWriterReplace;

    public Storage() throws IOException {
        file = new File(PATH);
    }

    /**
     * Creates a new file with the specified file path.
     * If the file already exists, it will not be overwritten.
     *
     * @throws IOException If an I/O error occurs while creating the file.
     */
    public void createFile() {
        file.getParentFile().mkdirs();
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Reads the contents of a file and returns it as a StringBuilder object.
     * The file is read line by line, and each line is appended to the StringBuilder object.
     *
     * @return StringBuilder containing the entire contents of the file, with each line separated by a newline.
     * @throws FileNotFoundException If the file specified by the path does not exist.
     */
    public StringBuilder read() throws FileNotFoundException {
        scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine() + "\n");
        }

        return sb;
    }

    /**
     * Marks a specific line in the file as completed by updating its status from "[_]" to "[X]".
     * The file is read line by line, and updates the status of the line specified by given line number, as well as
     * modifying the content back to the file.
     *
     * @param lineNo Line number to be marked as completed. 1-based index system.
     * @throws IOException If an I/O error occurs while reading or writing the file.
     */
    public void mark(int lineNo) throws IOException {
        int count = 1;

        scanner = new Scanner(file);
        fileWriterAmend = new FileWriter(PATH, true);
        StringBuilder updatedText = new StringBuilder();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (count == lineNo) {
                String[] tokens = s.split("\\[_]");
                String newLine = tokens[0] + "[X]" + tokens[1];
                updatedText.append(newLine).append("\n");
            } else {
                updatedText.append(s).append("\n");
            }
            count++;
        }

        clear();
        fileWriterAmend.write(updatedText.toString());
        fileWriterAmend.close();
    }

    /**
     * Marks a specific line in the file as completed by updating its status from "[X]" to "[_]".
     * The file is read line by line, and updates the status of the line specified by given line number, as well as
     * modifying the content back to the file.
     *
     * @param lineNo Line number to be unmarked as not completed. 1-based index system.
     * @throws IOException If an I/O error occurs while reading or writing the file.
     */
    public void unmark(int lineNo) throws IOException {
        int count = 1;

        scanner = new Scanner(file);
        fileWriterAmend = new FileWriter(PATH, true);

        StringBuilder updatedText = new StringBuilder();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (count == lineNo) {
                String[] tokens = s.split("\\[X]");
                String newLine = tokens[0] + "[_]" + tokens[1];
                updatedText.append(newLine).append("\n");
            } else {
                updatedText.append(s).append("\n");
            }
            count++;
        }

        clear();
        fileWriterAmend.write(updatedText.toString());
        fileWriterAmend.close();
    }

    /**
     * Deletes a specific line from the file and re-index the remaining lines, starting from 1.
     * The file is read line by line, and removes the line specified by given line number, as well as
     * modifying the content back to the file with updated line numbers.
     *
     * @param lineNo Line number to be deleted. 1-based index system.
     * @throws IOException If an I/O error occurs while reading or writing the file.
     */
    public void delete(int lineNo) throws IOException {
        int count = 1;
        int lineNumber = 1;

        scanner = new Scanner(file);
        fileWriterAmend = new FileWriter(PATH, true);
        StringBuilder updatedText = new StringBuilder();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] tokens = s.split("\\.");
            if (count != lineNo) {
                updatedText.append(lineNumber).append(".").append(tokens[1]).append("\n");
                lineNumber++;
            }
            count++;
        }

        clear();
        fileWriterAmend.write(updatedText.toString());
        fileWriterAmend.close();
    }

    /**
     * Appends the given text to the end of the file.
     * The text is written to the file and ended with a newline to ensure each entry appears on a new line.
     *
     * @param text Text to be appended to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void write(String text) throws IOException {
        fileWriterAmend = new FileWriter(PATH, true);
        fileWriterAmend.write(text + "\n");
        fileWriterAmend.close();
    }

    /**
     * Clears the text in the file.
     * The text is overwritten with nothing.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private void clear() throws IOException {
        fileWriterReplace = new FileWriter(PATH);
        fileWriterReplace.write("");
        fileWriterReplace.close();
    }

}
