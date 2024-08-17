import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Tars {

    private List<String> tasks;

    public Tars() {
        this.tasks = new ArrayList<>();
    }
    public String echo(String input) {
        return switch (input) {
            case "bye" -> ("Bye. Hope to see you again soon!");
            case "list" -> listAllTasks();
            default -> {
                addTask(input);
                yield ("added: " + input);
            }
        };
    }

    private String listAllTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return result.toString().trim();
    }

    private void addTask(String input) {
        this.tasks.add(input);
    }
    public static void main(String[] args) {
        System.out.println("____________________________________");
        System.out.println("Hello! I'm TARS");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________");

        Tars tars = new Tars();
        Scanner scanner = new Scanner(System.in);
        String input;
        String output;
        do {
            input = scanner.nextLine();
            output = tars.echo(input);
            System.out.println("____________________________________");
            System.out.println(output);
            System.out.println("____________________________________");
        } while (!input.equals("bye"));

        scanner.close();


    }
}
