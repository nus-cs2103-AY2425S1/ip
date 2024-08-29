package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String PATH = "src/main/data/task_list.txt";
    File f;
    Scanner sc;
    FileWriter fwA;
    FileWriter fw;

    public Storage() throws IOException {
        f = new File(PATH);
    }

    /**
     * Creates a new file with the specified file path.
     * If the file already exists, it will not be overwritten.
     *
     * @throws IOException If an I/O error occurs while creating the file.
     */
    public void createFile() throws IOException {
        f.createNewFile();
    }

    /**
     * Reads the contents of a file and returns it as a StringBuilder object.
     * The file is read line by line, and each line is appended to the StringBuilder object.
     *
     * @return StringBuilder containing the entire contents of the file, with each line separated by a newline.
     * @throws FileNotFoundException If the file specified by the path does not exist.
     */
    public StringBuilder read() throws FileNotFoundException {
        sc = new Scanner(f);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            sb.append(sc.nextLine() + "\n");
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
        sc = new Scanner(f);
        fwA = new FileWriter(PATH,true);
        StringBuilder updated_text = new StringBuilder();
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
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
        fwA.write(updated_text.toString());
        fwA.close();
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
        sc = new Scanner(f);
        fwA = new FileWriter(PATH,true);
        StringBuilder updated_text = new StringBuilder();
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
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
        fwA.write(updated_text.toString());
        fwA.close();
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
        sc = new Scanner(f);
        fwA = new FileWriter(PATH,true);
        StringBuilder updated_text = new StringBuilder();
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] tokens = s.split("\\.");
            if (count != lineNo) {
                updated_text.append(lineNumber).append(".").append(tokens[1]).append("\n");
                lineNumber++;
            }
            count++;
        }

        clear();
        fwA.write(updated_text.toString());
        fwA.close();
    }

    /**
     * Appends the given text to the end of the file.
     * The text is written to the file and ended with a newline to ensure each entry appears on a new line.
     *
     * @param text Text to be appended to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void write(String text) throws IOException {
        fwA = new FileWriter(PATH, true);
        fwA.write(text + "\n");
        fwA.close();
    }

    private void clear() throws IOException {
        fw = new FileWriter(PATH);
        fw.write("");
        fw.close();
    }


}
