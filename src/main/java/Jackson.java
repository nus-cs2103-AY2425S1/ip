import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.io.File;
import java.util.Base64;

/**
 * Main class for the chatbot.
 */
public class Jackson {

    /* chatbot settings */
    public static String name = "Jackson";
    private static final int EXPECTED_SIZE = 100;

    /* used for storing tasklist object and secret message */
    private static final TaskList taskList = new TaskList(EXPECTED_SIZE);
    private static String secret = "";

    /**
     * Reads secret text from secret file and prints it
     */
    public static void read_secret() {
        // get string builder to read line by line
        StringBuilder output = new StringBuilder();
        String out;
        if (secret.isEmpty()) {
            File f = new File("src/main/java/secret_text.txt");

            try {
                // read via scanner line by line
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    output.append(sc.nextLine()).append("\n");
                }
                out = output.toString().strip();

                // decode from base64 to utf8
                byte[] decoded = Base64.getDecoder().decode(out);
                out = new String(decoded);

            } catch (FileNotFoundException e) {
                // if file path not found
                System.out.println("Oops! Secret file not found...");
                return;
            }
        } else {
            out = secret;
        }

        // print secret msg!
        System.out.println(out);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Oi! I'm %s!\nWhat you want me do today ah?\n> ", name);
        String input = sc.nextLine().strip();

        // Variables for main loop
        Task t;
        Response r;
        Actions.ACTIONS a;
        Matcher m;

        // main loop starts
        while (true) {
            try {

                // first parse input and get response (or throw error here)
                r = Parser.parse(input);
                a = r.getAction();
                m = r.getMatcher();

                // decide what action to take based on response object received from parser
                if (a == Actions.ACTIONS.LIST) {
                    taskList.show_list();
                } else if (a == Actions.ACTIONS.TODO) {
                    t = new Todo(m.group(1));
                    taskList.add_list(t);
                } else if (a == Actions.ACTIONS.DEADLINE) {
                    t = new Deadline(m.group(1), m.group(2));
                    taskList.add_list(t);
                } else if (a == Actions.ACTIONS.EVENT) {
                    t = new Event(m.group(1), m.group(2), m.group(3));
                    taskList.add_list(t);
                } else if (a == Actions.ACTIONS.MARK) {
                    taskList.mark(Integer.parseInt(m.group(1)) - 1);
                } else if (a == Actions.ACTIONS.UNMARK) {
                    taskList.unmark(Integer.parseInt(m.group(1)) - 1);
                } else if (a == Actions.ACTIONS.DELETE) {
                    taskList.delete_list(Integer.parseInt(m.group(1)) - 1);
                } else if (a == Actions.ACTIONS.BYE) {
                    System.out.println("K k bye lah!");
                    return;
                } else if (a == Actions.ACTIONS.SECRET) {
                    read_secret();
                } else if (a == Actions.ACTIONS.INVALID){
                    // otherwise, throw error for unsupported command
                    throw new UnsupportedException(input);
                }

            } catch (UnsupportedException e) {
                // if user input not recognised
                System.out.println("Harh? What you talking about?");
                System.out.println("Try entering a recognized command:");
                System.out.println("""
                        1. todo [task-name]
                        2. deadline [task-name] /by [due-date]
                        3. event [task-name] /from [start-date] /to [end-date]
                        4. mark [index]
                        5. unmark [index]
                        6. list
                        7. bye""");

            } catch (SyntaxException e) {
                // if the user input is in the wrong format for the command
                System.out.println("Wrong format leh...");
                System.out.println("Try formatting your command as such:");
                switch (e.getMessage()) {
                    case "todo":
                        System.out.println("todo [task-name]");
                        break;
                    case "deadline":
                        System.out.println("deadline [task-name] /by [due-date]");
                        break;
                    case "event":
                        System.out.println("event [task-name] /from [start-date] /to [end-date]");
                        break;
                    case "list":
                        System.out.println("list");
                        break;
                    case "mark":
                        System.out.println("mark [index]");
                        break;
                    case "unmark":
                        System.out.println("unmark [index]");
                        break;
                    case "delete":
                        System.out.println("delete [index]");
                    case "bye":
                        System.out.println("bye");
                        break;
                    default:
                        System.out.println("Unknown error...");
                        break;
                }

            } catch (OutOfListException e) {
                // if user inputs an invalid index for mark/unmark/delete
                int size = Integer.parseInt(e.getMessage());
                System.out.printf("Alamak, you got %d items on the list only leh...\n", size);
                if (size == 0) {
                    System.out.println("You've got no items in the list! Add some stuff first!");
                } else if (size == 1) {
                    System.out.println("Enter 1 to mark/unmark/delete the task in the list!");
                } else {
                    System.out.printf("Enter a number between 1 and %d when marking/unmarking/deleting tasks!\n", size);
                }
            } catch (Exception e) {
                // some other error unaccounted for
                System.out.println("Oops! Something went wrong!");
                System.out.println(e.getMessage());
            }

            // get input again
            System.out.print("> ");
            input = sc.nextLine().strip();
        }
    }
}
