package gui;


import enumeration.CommandType;
import exception.InvalidIndexException;
import exception.InvalidInputFormatException;
import parser.Parser;
import storage.Storage;
import task.TaskList;

/**
 * @author A0272287W
 * Chatbot
 */
public class Friday {

    private final static String FILE_PATH = "data/Friday.txt";

    private static void save(String filepath, TaskList tasks) {
        Storage storage = new Storage(filepath);
        storage.write(tasks);
    }

    private static TaskList read(String filepath) {
        Storage storage = new Storage(filepath);
        return storage.read();
    }


    public static String getIntro() {
        return "Hello! I'm Friday\n"
                + "What can I do for you?\n\n"
                + "Commands:\nlist\nbye\ntodo\ndeadline\nevent\nmark\nunmark\ndelete\nfind";
    }
    /**
     * The main method serves as the entry point for the application.
     * It initiates the program by calling the {@code start} method.
     *
     * @param args command-line arguments passed to the program (not used in this implementation).
     */
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        String intro = "____________________________________________________________\n"
                + getIntro()
                + "____________________________________________________________\n";

        boolean exited = false;
        TaskList tasks = read(FILE_PATH);
        assert tasks != null : "tasks cannot be null!";
        System.out.println(intro);


        while (!exited) {
            System.out.println("____________________________________________________________\n");
            String input = Parser.scan();
            try {
                System.out.println("____________________________________________________________\n");
                System.out.println(Parser.performActionOnTaskList(input, tasks));
                assert Parser.createCommandFromInput(input) != null: "command created cannot be null!";
                if (Parser.createCommandFromInput(input) == CommandType.BYE) {
                    exited = true;
                    save(FILE_PATH, tasks);
                }
            } catch (InvalidIndexException | InvalidInputFormatException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________\n");

        }

    }

    public String getResponse(String input) {
        TaskList tasks = read(FILE_PATH);
        assert tasks != null : "tasks cannot be null!";

        try {
            String response = Parser.performActionOnTaskList(input, tasks);
            save(FILE_PATH, tasks);
            return response;
        } catch (InvalidIndexException | InvalidInputFormatException e) {
            return e.getMessage();
        }

    }

}
