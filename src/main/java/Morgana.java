import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Morgana {
    private static final String NAME = "Morgana";
    private static final String HORIZONTAL_LINE = "============================================================\n";

    private static final List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print(HORIZONTAL_LINE);
        System.out.printf("Hello! I'm %s.%n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner sc = new Scanner(System.in);
        String line;
        while (!(line = sc.nextLine()).equals("bye")) {
            if (line.equals("list")) {
                System.out.print(HORIZONTAL_LINE);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, tasks.get(i));
                }
                System.out.println(HORIZONTAL_LINE);
            } else {
                tasks.add(line);
                System.out.print(HORIZONTAL_LINE);
                System.out.printf("added: %s%n", line);
                System.out.println(HORIZONTAL_LINE);
            }
        }

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(HORIZONTAL_LINE);
    }
}
