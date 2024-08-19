import java.util.Scanner;

public class Gray {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        say("""
                Hello! I'm Gray.
                What can I do for you?""");
        while (true) {
            String user = reader.next();
            if (user == null || user.equals("bye")) break;
            say(user);
        }
        say("Bye. Hope to see you again soon!");
    }

    private static void say(String text) {
        System.out.println("\t____________________________________________________________");
        System.out.print("\t");
        System.out.println(text.replace("\n", "\n\t"));
        System.out.println("\t____________________________________________________________");
    }
}
