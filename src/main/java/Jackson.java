import java.util.Scanner;
import java.util.ArrayList;

public class Jackson {

    private static ArrayList<String> list = new ArrayList<String>(10);

    public static void add_list(String task) {
        System.out.printf("Adding '%s' to list!\n", task);
        list.add(task);
    }

    public static void show_list() {
        if (list.isEmpty()) {
            System.out.println("Nothing in list!");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, list.get(i));
            }
        }
    }

    public static void main(String[] args) {
        String name = "Jackson";
        Scanner sc = new Scanner(System.in);

        System.out.printf("Hello! My name is %s!\nWhat can I do for you today?\n> ", name);
        String response = sc.nextLine().strip();

        while (!response.equals("bye")) {
            if (response.equals("list")) {
                show_list();
            } else {
                add_list(response);
            }
            System.out.print("> ");
            response = sc.nextLine();
        }

        System.out.println("Goodbye! See you soon!");
    }
}
