import java.util.ArrayList;
import java.util.Scanner;

public class Alice {
    private static final ArrayList<String> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Hello! I am Alice! \nWhat can I do for you?");
        System.out.println("------------------------------------------");

        // get commands from the user while response is not "bye"
        String response = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            response = scanner.nextLine();

            // if user says bye, exit the loop
            if (response.equals("bye")) {
                break;
            }
            // if user wants list, print out all the tasks added
            else if (response.equals("list")) {
                System.out.println("------------------------------------------");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, tasks.get(i));
                }
                System.out.println("------------------------------------------");
                continue;
            }
            
            // all other strings are tasks that should be added
            tasks.add(response);
            System.out.println("------------------------------------------");
            System.out.println("added: " + response);
            System.out.println("------------------------------------------");
        }

        System.out.println("------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
    }
}
