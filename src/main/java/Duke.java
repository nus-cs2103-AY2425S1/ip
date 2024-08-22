import java.util.Scanner;

public class Duke {
    private static TodoList todo = new TodoList();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Reply.printGreeting();

        handleUserInput();

        Reply.printGoodbye();

    }

    public static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("list")){
                Reply.printMessage(todo.printTodo());
            }
            else if (userInput.equals("bye")) {
                break;
            } else if (userInput.startsWith("mark")) {
                handleMarkTask(userInput, true);
            } else if (userInput.startsWith("unmark")){
                handleMarkTask(userInput, false);
            } else {
                todo.addTask(userInput);
            }

        }

        scanner.close();
    }

    public static void handleMarkTask(String message, boolean mark) {
        String[] split = message.split(" ");
        if (split.length > 2) {
            Reply.printMessage(" Invalid input");
            return;
        }

        try {
            if (mark) {
                todo.markTask(Integer.parseInt(split[1]));
            } else {
                todo.unmarkTask(Integer.parseInt(split[1]));
            }
        } catch (NumberFormatException e ) {
            Reply.printMessage(" Invalid number");
        } catch (IndexOutOfBoundsException e) {
            Reply.printMessage(" Index number does not exist");
        }
    }
}
