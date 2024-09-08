package Papagu.Ui;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Main class for Papagu chatbot
 */
public class Papagu {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private File file;

    /**
     * Constructor for Papagu
     * Reads off any tasks from tasks.txt file
     */
    public Papagu(String filePath) {
        ui = new Ui();
        this.file = new File(filePath);
        this.tasks = new TaskList();
        this.storage = new Storage(filePath, tasks, file);
        try {
            Parser.parseFile(file, tasks);
        } catch (Exception e) {
            Ui.printLoadingError();
        }
    }

    /**
     * Main function to run Papagu
     */
    public void run() {
        Ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Parser.parseInput(tasks, input, this.storage);
            input = sc.nextLine();
        }
        Ui.printBye();
        sc.close();
    }

    /**
     * Function to get response from Papagu
     * @param input
     * @return response
     */
    public String getResponse(String input) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        if (input.equals("bye")) {
            Ui.printBye();
            System.setOut(originalOut);
            String capturedOutput = outputStream.toString();
            return capturedOutput;
        } else {
            Parser.parseInput(tasks, input, this.storage);
            System.setOut(originalOut);
            String capturedOutput = outputStream.toString();
            return capturedOutput;
        }
    }

    public static void main(String[] args) {
        new Papagu("data/tasks.txt").run();
    }

}


