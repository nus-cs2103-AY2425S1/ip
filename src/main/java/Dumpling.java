import java.util.Scanner;

public class Dumpling {

    private static final String DIVIDER = "    ____________________________________________________________";

    private Todo todoList;

    private static final String TERMINATE_STRING = "bye";
    private static final String LIST_COMMAND_STRING = "list";


    public Dumpling(){
        this.todoList = new Todo();
    }

    public boolean commandRouter(String userInput) {
        String operationMessage = "";
        switch (userInput) {
            case TERMINATE_STRING:
                this.exit();
                return false;
            case LIST_COMMAND_STRING:
                operationMessage += this.todoList.list();
                break;
            default:
                operationMessage += this.todoList.add(userInput);
        }
        operationMessage += DIVIDER;
        System.out.println(operationMessage);
        return true;
    }

    public void greet() {
        String greetingMessage = "    Hello! I'm Dumpling\n" +
                "    What can I do for you?\n" +
                DIVIDER + "\n";
        System.out.println(greetingMessage);
    }

    public void exit() {
        String exitMessage = "    Bye. Hope to see you again soon!\n" +
                DIVIDER;
        System.out.println(exitMessage);
    }

    public void echo(String message) {
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
