import java.util.Scanner;

public class Oliver {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Oliver.");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            }
            System.out.println("\t" + input);
        }
        sc.close();
    }
}
