import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.io.File;
import java.util.Base64;

/**
 * Main class for the chatbot.
 */
public class Jackson {

    /* Chatbot name */
    public static String name = "Jackson";

    /* Expected number of tasks to store */
    private static final int EXPECTED_SIZE = 100;

    /* Path to save list data */
    private static final String PATH = "src/main/java/data.txt";

    /* Stores secret text for greedy loading */
    private static String secret = "";

    /* Instance variables for main loop */
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private Scanner sc;

    public Jackson(int expectedSize, String path) {
        this.taskList = new TaskList(expectedSize);
        this.ui = new Ui();
        this.storage = new Storage(path);
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads secret text from secret file and prints it.
     * If secret file not found, handles exception and prints error message
     */
    public void readSecret() {
        // get string builder to read line by line
        StringBuilder temp = new StringBuilder();
        String output;
        if (secret.isEmpty()) {
            File f = new File("src/main/java/secret_text.txt");

            try {
                // read via scanner line by line
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    temp.append(sc.nextLine()).append("\n");
                }
                output = temp.toString().strip();

                // decode from base64 to utf8
                byte[] decoded = Base64.getDecoder().decode(output);
                output = new String(decoded);

                // close scanner
                sc.close();
            } catch (FileNotFoundException e) {
                // if file path not found
                System.out.println("Oops! Secret file not found...");
                return;
            }
        } else {
            output = secret;
        }
        // print secret msg!
        System.out.println(output);
    }

    /**
     * Runs the main loop of the chatbot
     */
    public void run() {
        // Variables for main loop
        String input;
        Task task;
        Response response;
        Actions.ACTIONS action;
        Matcher matcher;
        int index;

        // determine whether to terminate loop
        boolean isQuit = false;

        // load in tasks from save file (if any)
        this.storage.load(this.taskList);

        // Welcome message
        this.ui.printWelcome();

        // main loop starts
        while (!isQuit) {
            try {
                // print the input marker > and take in input from user
                this.ui.printMarker();
                input = this.sc.nextLine();

                // first parse input and get response (or throw error here)
                response = Parser.parse(input);
                action = response.getAction();
                matcher = response.getMatcher();

                // decide what action to take based on action object received from parser
                switch (action) {
                case LIST:
                    this.ui.printList(this.taskList.toString());
                    break;
                case TODO:
                    task = new Todo(matcher.group(1));
                    this.taskList.addTask(task);
                    this.ui.printAddList(task, this.taskList);
                    break;
                case DEADLINE:
                    task = new Deadline(matcher.group(1), matcher.group(2));
                    this.taskList.addTask(task);
                    this.ui.printAddList(task, this.taskList);
                    break;
                case EVENT:
                    task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
                    this.taskList.addTask(task);
                    this.ui.printAddList(task, this.taskList);
                    break;
                case MARK:
                    index = Integer.parseInt(matcher.group(1)) - 1;
                    task = this.taskList.mark(index);
                    this.ui.printMark(task);
                    break;
                case UNMARK:
                    index = Integer.parseInt(matcher.group(1)) - 1;
                    task = this.taskList.unmark(index);
                    this.ui.printUnmark(task);
                    break;
                case DELETE:
                    index = Integer.parseInt(matcher.group(1)) - 1;
                    task = this.taskList.deleteTask(index);
                    this.ui.printDeleteList(task, this.taskList);
                    break;
                case BYE:
                    isQuit = true;
                    break;
                case SECRET:
                    this.readSecret();
                    break;
                case INVALID:
                    throw new UnsupportedException(input);
                }
            } catch (UnsupportedException e) {
                // if user input not recognised, print command list
                this.ui.printCommands();
            } catch (SyntaxException e) {
                // if the user input is in the wrong format for the command, print format guide
                this.ui.printFormatGuide(e.getMessage());
            } catch (OutOfListException e) {
                // if user inputs an invalid index for mark/unmark/delete, print index guide
                this.ui.printIndexGuide(this.taskList);
            } catch (Exception e) {
                // some other error unaccounted for, print generic warning
                this.ui.printUnknownError(e);
            }
            // save task list to storage after every command
            this.storage.save(this.taskList);
        }
        System.out.println("K k bye lah!");
        this.sc.close();
    }

    /**
     * Main function
     * @param args command line args
     */
    public static void main(String[] args) {
        Jackson jackson = new Jackson(EXPECTED_SIZE, PATH);
        jackson.run();
    }
}
