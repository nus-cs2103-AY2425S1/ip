import java.util.Scanner;

public class Dumpling {

    private static final String DIVIDER = "    ____________________________________________________________";

    private TaskList todoList;

    private static final String TERMINATE_STRING = "bye";
    private static final String LIST_COMMAND_STRING = "list";
    private static final String MARK_COMMAND_STRING = "mark";
    private static final String UNMARK_COMMAND_STRING = "unmark";
    private static final String TODO_COMMAND_STRING = "todo";
    private static final String DEADLINE_COMMAND_STRING = "deadline";
    private static final String EVENT_COMMAND_STRING = "event";
    private static final String DELETE_COMMAND_STRING = "delete";

    public Dumpling(){
        this.todoList = new TaskList();
    }

    private boolean commandRouter(String userInput) throws InvalidCommandException, EmptyDescriptionException, NumberFormatException, IndexOutOfBoundsException {
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
            case TODO_COMMAND_STRING:
            case DEADLINE_COMMAND_STRING:
            case EVENT_COMMAND_STRING:
                operationMessage += this.todoList.add(userInput);
                break;
            case DELETE_COMMAND_STRING:
                operationMessage += this.todoList.delete(
                        Integer.parseInt(userInput.split(" ")[1]));
                break;
            default:
                throw new InvalidCommandException(
                        String.format(
                                "     %s is not a valid command!\n" +
                                        "     To list items, use 'list'.\n" +
                                        "     To mark or unmark an item as done, use '<mark/unmark> <item index>'.\n" +
                                        "     To add a new item, use '<todo/deadline/event> <task name> <args>'.",
                                command
                        )
                );
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
