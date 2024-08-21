import java.util.Scanner;

public class Dumpling {

    private static final String DIVIDER = "    ____________________________________________________________";

    private TaskList todoList;

    private static final String TERMINATE_STRING = "bye";
    private static final String LIST_COMMAND_STRING = "list";
    private static final String MARK_COMMAND_STRING = "mark";
    private static final String UNMARK_COMMAND_STRING = "unmark";

    public Dumpling(){
        this.todoList = new TaskList();
    }

    private boolean commandRouter(String userInput) {
        String operationMessage = "";
        String command = userInput.split(" ")[0];
        switch (command) {
            case TERMINATE_STRING:
                this.exit();
                return false;
            case LIST_COMMAND_STRING:
                operationMessage += this.todoList.list();
                break;
            case MARK_COMMAND_STRING:
                operationMessage += this.todoList.mark(
                        Integer.parseInt(userInput.split(" ")[1]));
                break;
            case UNMARK_COMMAND_STRING:
                operationMessage += this.todoList.unmark(
                        Integer.parseInt(userInput.split(" ")[1]));
                break;
            default:
                operationMessage += this.todoList.add(userInput);
        }
        operationMessage += DIVIDER;
        System.out.println(operationMessage);
        return true;
    }

    private void greet() {
        String greetingMessage = "    Hello! I'm Dumpling\n" +
                "    What can I do for you?\n" +
                DIVIDER + "\n";
        System.out.println(greetingMessage);
    }

    private void exit() {
        String exitMessage = "    Bye. Hope to see you again soon!\n" +
                DIVIDER;
        System.out.println(exitMessage);
    }

    private void echo(String message) {
        String updatedMessage = "    " + message + "\n" +
                DIVIDER + "\n";
        System.out.println(updatedMessage);
    }

    public void run() {
        System.out.println(DIVIDER);
        this.greet();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println(DIVIDER);
        while (this.commandRouter(userInput)) {
            userInput = scanner.nextLine();
            System.out.println(DIVIDER);
        }
        scanner.close();
    }
}
