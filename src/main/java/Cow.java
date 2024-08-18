import java.util.Scanner;
public class Cow {
    // solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    private static final Scanner scanner = new Scanner(System.in);
    private static final TodoList todoList = new TodoList();
    public static void main(String[] args) {
        Message.print(
                " Hello! I'm COW\n"
                        + " What can I do for you?"
        );

        while (true) {
            String command = scanner.nextLine();
            new Command(command, todoList).action();
        }
    }
}
