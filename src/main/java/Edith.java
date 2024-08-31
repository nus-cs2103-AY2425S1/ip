import java.util.Objects;
import java.util.Scanner;


public class Edith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList todoList = new ToDoList();
        Storage.loadTasks(todoList);

        // print out greeting when bot first starts up
        Ui.greetUser();

        String userInput = scanner.nextLine();

        // break out of loop when user inputs bye
        while (!Objects.equals(userInput, "bye")) {

            Ui.handleUserInput(userInput, todoList);
            userInput = scanner.nextLine();

        }

        Ui.bidFarewellToUser();
    }
}
