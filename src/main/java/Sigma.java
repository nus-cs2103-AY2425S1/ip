import java.util.Scanner;

import sigma.TaskList;
import sigma.Ui;
import sigma.Parser;
import sigma.Storage;
@SuppressWarnings("FieldMayBeFinal")
/**
 * The main class from where the program is run
 *
 * @author Qiao Yi
 */
public class Sigma {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructor for Sigma
     *
     * @param filePath The path to the file where past data is stored
     */
    public Sigma(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        parser = new Parser();
    }

    /**
     * Opens and runs the program
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        storage.readTasksFromFile();

        System.out.println(ui.welcome());
        System.out.println(TaskList.toPrettyList()); // kiv

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("list")) {
                System.out.println(TaskList.toPrettyList());
                continue;
            }

            if (userInput.equals("greet")) {
                System.out.println(parser.greet());
                break;
            }

            if (userInput.equals("bye")) {
                parser.goodbye();
                break;
            }

            if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                parser.handleMarkUnmark(userInput);
                continue;
            }
            if (userInput.startsWith("delete")) {
                parser.handleDelete(userInput);
                continue;
            }
            if (userInput.startsWith("todo")) {
                parser.handleTodo(userInput);
                continue;
            }
            if (userInput.startsWith("deadline")) {
                parser.handleDeadline(userInput);
                continue;
            }
            if (userInput.startsWith("event")) {
                parser.handleEvent(userInput);
                continue;
            }

            if (userInput.startsWith("find")) {
                parser.handleFind(userInput);
                break;
            }
            ui.dontRecognise();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Sigma sigma = new Sigma("C:\\Users\\limqi\\OneDrive\\Desktop\\uni\\CS2103T\\ip\\data\\sigma.txt");
        sigma.run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        if (input.equals("list")) {
            response.append(TaskList.toPrettyList());
        } else if (input.equals("greet")) {
            response.append(parser.greet());
        } else if (input.equals("bye")) {
            response.append(parser.goodbye());
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            response.append(parser.handleMarkUnmark(input));
        } else if (input.startsWith("delete")) {
            response.append(parser.handleDelete(input));
        } else if (input.startsWith("todo")) {
            response.append(parser.handleTodo(input));
        } else if (input.startsWith("deadline")) {
            response.append(parser.handleDeadline(input));
        } else if (input.startsWith("event")) {
            response.append(parser.handleEvent(input));
        } else if (input.startsWith("find")) {
            response.append(parser.handleFind(input));
        } else {
            // assert that input is not any of the commands
            assert !(input.equals("list") || input.equals("greet") || input.equals("bye") ||
                    input.startsWith("mark") || input.startsWith("unmark") ||
                    input.startsWith("delete") || input.startsWith("todo") ||
                    input.startsWith("deadline") || input.startsWith("event") ||
                    input.startsWith("find")) : "Input should not be any of the recognised commands";
            response.append(ui.dontRecognise());
        }
        return response.toString();
    }
}