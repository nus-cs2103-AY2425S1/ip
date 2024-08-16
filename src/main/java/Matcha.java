import java.util.Scanner;
import java.util.ArrayList;

public class Matcha {
    public static void main(String[] args) {

        //scanner for user input
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> tasks = new ArrayList<>();

        String greet = "____________________________________________________________\n" +
                " Hi there! I am Matcha, your personal chatbot.\n" +
                " How can I help you today?\n" +
                "____________________________________________________________\n" ;
        System.out.println(greet);

        while (true) {

            //read user input
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                String exit = "____________________________________________________________\n" +
                        " Bye. Hope to see you again!\n" +
                        "____________________________________________________________\n";
                System.out.println(exit);
                break;
            }

            if (input.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < tasks.size(); i++) {
                    String task = (i + 1) + ". " + tasks.get(i);
                    System.out.println(task);
                }
                System.out.println("____________________________________________________________\n");
            } else {
                tasks.add(input);
                String addedTask = "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________\n";
                System.out.println(addedTask);
            }

        }
    }
}
