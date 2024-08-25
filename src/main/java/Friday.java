import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Friday\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!";
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in); 
        String line = "______________________________________";
        System.out.println(greeting + farewell);
        System.out.println(line);

        while (true) {
            String inputString = scanner.nextLine();
            System.out.println(line);
            if (inputString.equals("bye")) {
                System.out.println(farewell);
                System.out.println(line);
                break;
            } else {
                System.out.println(inputString);
                System.out.println(line);
            }
        }
    }
}
