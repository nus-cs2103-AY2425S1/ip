import java.util.Scanner;
import java.util.ArrayList;

public class Jackson {

    private static int EXPECTED_SIZE = 10;
    private static ArrayList<String> tasks = new ArrayList<>(EXPECTED_SIZE);

    public static void add_list(String task) {
        System.out.printf("Adding '%s' to list!\n", task);
        tasks.add(task);
    }

    public static void show_list() {
        if (tasks.isEmpty()) {
            System.out.println("Nothing in list!");
        } else {
            System.out.println("Here's your list!");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        }
    }

    public static void main(String[] args) {
        String name = "Jackson";
        Scanner sc = new Scanner(System.in);

        System.out.printf("Hello! My name is %s!\nWhat can I do for you today?\n> ", name);
        String response = sc.nextLine().strip();

        while (!response.equals("bye")) {
            switch (response) {
                case "list":
                    show_list();
                    break;
                case "mark":

                    break;
                case "unmark":
                    break;
                default:
                    add_list(response);
            }
            System.out.print("> ");
            response = sc.nextLine();
        }

        System.out.println("Goodbye! See you soon!");
    }
}
