import java.util.Scanner;
import java.util.StringTokenizer;

public class Mahesh {

    private final static String PATH = "../../../data/mahesh.txt";

    /**
     * List to store tasks.
     */
    private static TaskList list;

    public static void main(String[] args) {
        Storage store = new Storage(Mahesh.PATH);
        Mahesh.list = store.retrieveData();
        Ui.printStartupMessage();

        
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            String originalInput = scan.nextLine();
            StringTokenizer tokenizedInput = new StringTokenizer(originalInput);
            String commandString = tokenizedInput.nextToken();
            try {
                Command command = Command.fromString(commandString);
                switch (command) {
                case LIST:
                    Mahesh.list.printList();
                    break;
                case BYE:
                    Ui.printExitMessage();
                    exit = true;
                    break;
                case MARK:
                    Mahesh.list.markTaskAsDone(Integer.parseInt(tokenizedInput.nextToken()) - 1);
                    break;
                case UNMARK:
                    Mahesh.list.unmarkTaskAsDone(Integer.parseInt(tokenizedInput.nextToken()) - 1);
                    break;
                case TODO:
                    try {
                        Mahesh.list.addToList(Todo.parseTodo(tokenizedInput));
                    } catch (MaheshException err) {
                        Ui.printIncompleteCommandErr(err);
                    }
                    break;
                case DEADLINE:
                    try { 
                        Mahesh.list.addToList(Deadline.parseDeadline(tokenizedInput));
                    } catch (MaheshException err) {
                        Ui.printIncompleteCommandErr(err);
                    }
                    break;
                case EVENT: 
                    try {
                        Mahesh.list.addToList((Event.parseEvent(tokenizedInput)));
                    } catch (MaheshException err) {
                        Ui.printIncompleteCommandErr(err);
                    }
                    break;
                case DELETE:
                    Mahesh.list.deleteFromList(Integer.parseInt(tokenizedInput.nextToken()) - 1);
                    break;
                }
            } catch (MaheshException err) {
                System.out.println(err.getMessage());
            }
            store.updateData(Mahesh.list);
        }

        scan.close();
    }
}
