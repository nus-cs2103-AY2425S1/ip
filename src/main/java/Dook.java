import java.util.Scanner;
public class Dook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        String separator = "____________________________________________________________";
        String greeting = "Hello! I'm Dook\nWhat can I do for you?\n" + separator;
        String exit = "Bye. Hope to see you again soon!\n" + separator;

        System.out.println(separator);
        System.out.println(greeting);

        String response;
        int count = 0;

        while (true) {
            response = scanner.nextLine();
            if (response.equals("bye")) {
                System.out.println(separator);
                System.out.println(exit);
                break;
            } else if (response.equals("list")) {
                System.out.println(separator);
                for (int i = 1; i <= count; i++) {
                    System.out.println(i + ". " + tasks[i - 1]);
                }
                System.out.println(separator);
            } else {
                tasks[count] = response;
                count++;
                System.out.println(separator);
                System.out.println("added: " + response);
                System.out.println(separator);
            }
        }
    }
}
