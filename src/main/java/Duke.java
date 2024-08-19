import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        String greet = "Hello! I'm Bob \n"
                + "What can I do for you? \n";
        String bye = "Bye. Hope to see you again soon!";
        String input = "";

        boolean bool = true;

        System.out.println(greet);
        while (bool) {
            input = reader.nextLine();

            if(input.equals("bye")){
                System.out.println(bye);
                break;
            }

            System.out.println(input);

        }
    }
}
