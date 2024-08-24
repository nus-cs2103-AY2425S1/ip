import exceptions.InvalidTaskException;
import exceptions.MissingParametersException;
import exceptions.UnknownCommandException;

import java.io.IOException;
import java.util.Scanner;

public class Cow {
    // solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    private static final Scanner scanner = new Scanner(System.in);
    private static final TodoList todoList = new TodoList();
    private static FileSaver fs;

    public static void main(String[] args) throws IOException, UnknownCommandException {
        Message.print(
                " Hello! I'm COW\n"
                        + " What can I do for you?"
        );

        try {
            fs = new FileSaver(todoList);
            fs.loadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnknownCommandException | MissingParametersException e) {
            Message.print(e.getMessage());
        }

        while (true) {
            try {
                String command = scanner.nextLine();
                new Command(command, todoList, fs).action();
            } catch (UnknownCommandException | MissingParametersException | InvalidTaskException e) {
                Message.print(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
