import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {

        String line = "____________________________________________________________";
        String name = "Bob";
        System.out.println(line);
        System.out.printf("Hello! I'm %s!\n", name);
        System.out.println("What can I do for you?");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        while (!word.equals("bye")) {
            System.out.println(word);
            word = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
