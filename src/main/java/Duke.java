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
            } else {
                todo.addTask(userInput);
            }

        }

        scanner.close();
    }

}
