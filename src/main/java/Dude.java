import java.util.Scanner;

public class Dude {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Dude!\nWhat can I do for you?");
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");
        System.out.println(line);
    }
}
