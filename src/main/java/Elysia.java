import java.util.ArrayList;
import java.util.Scanner;

public class Elysia {
    static String line = "____________________________________________________________";
    static String welcomeMessage = "Hello! I'm Elysia\nWhat can I do for you?";
    static String exitMessage = "Bye. Hope to see you again soon!";
    private static ArrayList<String> arrayList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        arrayList = new ArrayList<>();

        System.out.println(line);
        System.out.println(welcomeMessage);
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                int n = arrayList.size();
                for (int i = 1; i <= n; i++) {
                    System.out.println(i + ". " + arrayList.get(i - 1));
                }
                continue;
            }

            arrayList.add(input);

            System.out.println(line);
            System.out.println("added: " + input);
            System.out.println(line);
        }
        System.out.println(line);
        System.out.println(exitMessage);
        System.out.println(line);
    }
}
