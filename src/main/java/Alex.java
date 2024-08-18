import java.util.Scanner;

public class Alex {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String line = "____________________________________________________________";
        String greeting =
                """
                        ____________________________________________________________
                         Hello! I'm Alex
                         What can I do for you?
                        ____________________________________________________________""";

        System.out.println(greeting);

        while(true) {
            String response = myObj.nextLine();
            if (response.equals("bye")) {
                break;
            }
            System.out.println(line + "\n" + response + "\n" + line);
        }

        String farewell =
                """
                        ____________________________________________________________
                        Bye. Hope to see you again soon!
                        ____________________________________________________________""";

        System.out.println(farewell);
    }
}
