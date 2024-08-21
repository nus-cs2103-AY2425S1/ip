import java.util.Scanner;

public class Sam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(getHorizontalLine());
        System.out.println("Hello! I'm Sam\nWhat can I do for you?");
        System.out.println(getHorizontalLine());

        while (true) {
            String input = scanner.nextLine();
            System.out.println(getHorizontalLine());

            if (input.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(getHorizontalLine());
                break;
            } else {
                System.out.println("   "+input);
                System.out.println(getHorizontalLine());
            }
        }

        scanner.close();
    }

    private static String getHorizontalLine() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            line.append("-");
        }
        return line.toString();
    }
}