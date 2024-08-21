import java.util.ArrayList;
import java.util.Scanner;

public class Lict {
    private final static String name = "Lict";
    private final static String horizontal_line = "__________________________________";
    private final static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(horizontal_line);
        System.out.println("Hello! I'm "+ name);
        System.out.println("What can I do for you?");
        System.out.println(horizontal_line);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(horizontal_line);
            if (input.equals("list")) {
                int counter = 1;
                for (String task : tasks) {
                    System.out.println(counter + ". " + task);
                    counter+=1;
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
            System.out.println(horizontal_line);
            input = sc.nextLine();
        }
        sc.close();
        System.out.println(horizontal_line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal_line);
    }
}
