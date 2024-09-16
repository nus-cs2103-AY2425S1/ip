package milutrock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import milutrock.exceptions.UnknownCommandException;

/**
 * Handle loading and storing tasks using a file and a parser, with
 * methods to replay previous input and store new tasks.
 */
public class Storage {
    private File file;
    private Parser parser;
    private FileWriter fw;

    public Storage(String path, Parser parser) {
        this.file = new File("./data.txt");
        this.parser = parser;

        try {
            this.fw = new FileWriter(this.file.getAbsoluteFile(), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Replay previous input without output. 
     * This is a dirty way to re-create the task list.
    */
    public void loadTasks() {
        try {
            Scanner scanner = new Scanner(this.file);
            this.replayInput(scanner);
        } catch (FileNotFoundException e) {}
    }

    /**
     * Append a new command to the file specified.
     */
    public void storeCommand(String command) {
        try {
            fw.write(command + "\n");
            fw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void replayInput(Scanner scanner) {
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            try {
                this.parser.parseCommand(input);
            } catch (UnknownCommandException e) {}
        }
    }
}
