import java.util.Scanner;
import java.util.ArrayList;

public class Alex {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String line = "____________________________________________________________";
        ArrayList<String> list = new ArrayList<>();
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
            } else if (response.equals("list")) {
                System.out.println(line);
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                System.out.println(line);
            } else {
                list.add(response);
                System.out.println(line + "\nadded: " + response + "\n" + line);
            }
        }

        String farewell =
                """
                        ____________________________________________________________
                        Bye. Hope to see you again soon!
                        ____________________________________________________________""";

        System.out.println(farewell);
    }
}
