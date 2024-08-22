import java.util.Objects;
import java.util.Scanner;

public class Edith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String horizontal = "____________________________________________________________";
        String greeting = " heyyy im edith! \n what can I do for you?";
        String farewell = " bye!! see you soon love <3";
        String linebreak = "\n";

        ToDo todoList = new ToDo();

        System.out.println(horizontal + linebreak +
                greeting + linebreak +
                horizontal);

        String task = scanner.nextLine();

        while (!Objects.equals(task, "bye")) {
            if (Objects.equals(task, "list")) {
                System.out.println(horizontal + linebreak +
                        todoList.toString() +
                        horizontal);
                task = scanner.nextLine();
            } else {
                todoList.add(task);
                System.out.println(horizontal + linebreak +
                        " " + "added: " + task + linebreak +
                        horizontal);
                task = scanner.nextLine();
            }
        }

        System.out.println(horizontal + linebreak + farewell + linebreak + horizontal);
    }
}
