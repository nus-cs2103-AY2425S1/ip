import java.util.Scanner;

public class Bobby {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Hello, I'm Bobby");
        System.out.println("What can I do for you?");
        while (true) {
            String s = input.nextLine();
            if (s.equals("bye")) {
                break;
            }
            System.out.println(s);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
