package snowy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Represents the object the reads and write data from hard disk.
 */
public class Storage {
    private final File file;

    /**
     * Creates a new Storage with the file being specified by the given filePath.
     * @param filePath the filePath of the file to be read and written.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Reads the data from the file and extracts the lines.
     * @return An ArrayList where each element is a single line from the file.
     * @throws SnowyException if the file does not exist and cannot be created.
     * @throws SnowyException if the file exists but cannot be opened.
     */
    public ArrayList<String> load() throws SnowyException {
        ArrayList<String> lines = new ArrayList<>();
        try {
            file.createNewFile();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                lines.add(nextLine);
            }


        } catch (IOException e) {
            throw new SnowyException("Unable to create new file");
        }
        return lines;
    }


    /**
     * Write into the file with the specified String.
     * @param saveLines the lines to be saved into the file.
     * @throws SnowyException if the file is unable to be updated.
     */
    public void save(String saveLines) throws SnowyException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(saveLines);
            writer.close();
        } catch (IOException e) {
            throw new SnowyException("Unable to update file");
        }
    }

}
