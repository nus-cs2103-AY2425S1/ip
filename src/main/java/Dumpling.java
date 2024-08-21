import java.util.Scanner;

public class Dumpling {

    private static final String DIVIDER = "    ____________________________________________________________";

    private TaskList todoList;

    public Dumpling(){
        this.todoList = new TaskList();
    }

    private boolean commandRouter(String userInput) throws InvalidCommandException, EmptyDescriptionException, NumberFormatException, IndexOutOfBoundsException {
        String operationMessage = "";
        Command command = Command.getCommand(userInput.split(" ")[0]);
        switch (command) {
            case BYE:
                this.exit();
                return false;
            case LIST:
                operationMessage += this.todoList.list();
                break;
            case MARK:
                operationMessage += this.todoList.mark(
                        Integer.parseInt(userInput.split(" ")[1]));
                break;
            case UNMARK:
                operationMessage += this.todoList.unmark(
                        Integer.parseInt(userInput.split(" ")[1]));
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                operationMessage += this.todoList.add(userInput, command);
                break;
            case DELETE:
                operationMessage += this.todoList.delete(
                        Integer.parseInt(userInput.split(" ")[1]));
                break;
            default:
                // invalid commands would have been thrown when getting the command
        }
        if (!operationMessage.isEmpty()) {
            System.out.println(operationMessage);
        }
        return true;
    }

    private void greet() {
        String greetingMessage = "    Hello! I'm Dumpling\n" +
                "    What can I do for you?\n" +
                DIVIDER;
        System.out.println(greetingMessage);
    }

    private void exit() {
        String exitMessage = "    Bye. Hope to see you again soon!\n" +
                DIVIDER;
        System.out.println(exitMessage);
    }

    private void echo(String message) {
        String updatedMessage = "    " + message + "\n" +
                DIVIDER;
        System.out.println(updatedMessage);
    }

    public void run() {
        System.out.println(DIVIDER);
        this.greet();
        Scanner scanner = new Scanner(System.in);
        boolean toContinue = true;
        String userInput;
        while (toContinue) {
            userInput = scanner.nextLine();
            System.out.println(DIVIDER);
            try {
                toContinue = commandRouter(userInput);
            } catch (InvalidCommandException | EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(
                    "     There was an issue when marking / unmarking a task! The argument provided was not a number.");
            } catch (IndexOutOfBoundsException e) {
                if (this.todoList.getNumItems() == 0) {
                    System.out.println("     The task list is still empty! Fill it up with some tasks before marking / unmarking.");
                } else {
                    System.out.println(
                        String.format(
                                "     There was an issue with indexing a task. Please ensure the index is 1-based and <= %d.",
                                this.todoList.getNumItems()
                        )
                    );
                }
            } finally {
                if (toContinue) {
                    System.out.println(DIVIDER);
                }
            }
        }
        scanner.close();
    }
}
