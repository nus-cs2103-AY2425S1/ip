import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        taskList = TaskList.init(ui);
        parser = new Parser(taskList, ui);
    }

    public void run() {
        ui.printGreeting();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                if (parser.parse(fullCommand)) {
                    break;
                }
            } catch (InvalidInputException | MissingTaskNameException |
                     MissingDateException | TaskNotFoundException |
                     InvalidDateException e) {
                ui.printMessage(e.toString());
            }
        }
        ui.close();
        ui.printGoodbye();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /*
    public static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = scanner.nextLine();
            try {
                if (userInput.equals("list")) {
                    Reply.printMessage(taskList.printList());
                } else if (userInput.equals("bye")) {
                    break;
                } else if (userInput.startsWith("mark")) {
                    handleMarkTask(userInput, true);
                } else if (userInput.startsWith("unmark")) {
                    handleMarkTask(userInput, false);
                } else if (userInput.startsWith("todo")) {
                    handleAddTodo(userInput);
                } else if (userInput.startsWith("deadline")) {
                    handleAddDeadline(userInput);
                } else if (userInput.startsWith("event")) {
                    handleAddEvent(userInput);
                } else if (userInput.startsWith("delete")) {
                    handleDeleteTask(userInput);
                } else {
                    throw new InvalidInputException();
                }
            } catch (InvalidInputException e) {
                Reply.printMessage(e.toString());
            } catch (MissingTaskNameException e) {
                Reply.printMessage(e.toString());
            } catch (MissingDateException e) {
                Reply.printMessage(e.toString());
            } catch (TaskNotFoundException e) {
                Reply.printMessage(e.toString());
            } catch (InvalidDateException e) {
            Reply.printMessage(e.toString());
            }

        }
        scanner.close();
    }
*/
}

