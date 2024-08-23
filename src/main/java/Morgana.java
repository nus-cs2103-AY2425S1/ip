import java.util.Scanner;

public class Morgana {
    private static final String NAME = "Morgana";
    private static final String HORIZONTAL_LINE = "============================================================\n";

    public static void main(String[] args) {
        System.out.print(HORIZONTAL_LINE);
        System.out.printf("Hello! I'm %s.%n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner sc = new Scanner(System.in);
        String line;
        while (!(line = sc.nextLine()).equals("bye")) {
            System.out.print(HORIZONTAL_LINE);
            System.out.println(line);
            System.out.println(HORIZONTAL_LINE);
        }

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(HORIZONTAL_LINE);
    }
}
