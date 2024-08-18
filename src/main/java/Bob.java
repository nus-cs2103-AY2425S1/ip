import java.util.Objects;
import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String greeting = """
                ____________________________________________________________
                 Hello! I'm Bob
                 What can I do for you?
                ____________________________________________________________
                """;
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            if (!Objects.equals(input, "bye")) {
                System.out.println("____________________________________________________________\n"
                        + " " + input + "\n"
                        + "____________________________________________________________\n"
                );
            } else {
                String exit = """
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
                System.out.println(exit);
                System.exit(0);
            }
        }
    }
}
