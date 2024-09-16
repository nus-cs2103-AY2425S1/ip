package milutrock;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.OutputStream;
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

    public Storage(String path, Parser parser) {
        this.file = new File("./data.txt");
        this.parser = parser;
    }

    /**
     * Replay previous input without output. 
     * This is a dirty way to re-create the task list.
    */
    public void loadTasks() {
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            Scanner scanner = new Scanner(this.file);
            this.replayInput(scanner);
        } catch (FileNotFoundException e) {
        }

        System.setOut(stdout);
    }

    /**
     * Write the contents of the parser's stdin to the file specified.
     */
    public void storeTasks() {
        try {
            FileWriter fw = new FileWriter(this.file.getAbsoluteFile());
            fw.write(this.parser.getStdin());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void replayInput(Scanner scanner) {
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            try {
                this.parser.parseCommand(input);
            } catch (UnknownCommandException e) {
            }
        }
    }
}
