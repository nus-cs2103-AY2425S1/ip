import java.util.*;

public class Count {
    public final String spacer = "\n____________________________________________________________\n";
    public void reply(String output) {
        System.out.println(spacer + output + spacer);
    }
    public void greet() {
        reply("Hello! I'm Count!\nWhat can I do for you?");
    }

    public void farewell() {
        reply("Bye. Hope to see you again soon!");
    }

    // Default case just echoes
    public void parser(String input) {
        switch (input.toLowerCase()) {
            case "hello":
                greet();
                break;
            case "bye":
                farewell();
                break;
            default:
                reply(input);
        }
    }

    public static void main(String[] args) {
        Count c = new Count();
        Scanner sc = new Scanner(System.in);
        c.greet();

        while (true) {
            String input = sc.nextLine();
            c.parser(input);
        }
    }
}
