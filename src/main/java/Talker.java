import java.util.Scanner;

public class Talker {
    public static void main(String[] args) {
        String name = "Talker";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.printf("Hello! I'm %s\n", name);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner reader = new Scanner(System.in);

        while (true) {
            String input = reader.next();

            System.out.println(line);
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(input);
                System.out.println(line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(line);
    }
}
