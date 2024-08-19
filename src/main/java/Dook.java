import java.util.Scanner;
public class Dook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String separator = "____________________________________________________________";
        String greeting = "Hello! I'm Dook\nWhat can I do for you?\n" + separator;
        String exit = "Bye. Hope to see you again soon!\n" + separator;

        System.out.println(separator);
        System.out.println(greeting);

        String response;

        while (true) {
            response = scanner.nextLine();
            if (response.equals("bye")) {
                System.out.println(separator);
                System.out.println(exit);
                break;
            } else {
                System.out.println(separator);
                System.out.println(response);
                System.out.println(separator);
            }
        }
    }
}
