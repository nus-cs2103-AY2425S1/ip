import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Gray {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<String> userList = new ArrayList<>();
        say("""
                Hello! I'm Gray.
                What can I do for you?""");
        while (true) {
            String user = reader.nextLine();
            if (user == null) break;
            else if (user.equals("bye")) break;
            else if (user.equals("list")) {
                StringJoiner joiner = new StringJoiner("\n");
                for (int i = 0; i < userList.size(); i++) {
                    joiner.add(String.format("%d. %s", i + 1, userList.get(i)));
                }
                say(joiner.toString());
            }
            else {
                userList.add(user);
                say(String.format("added: %s", user));
            }
        }
        say("Bye. Hope to see you again soon!");
    }

    private static void say(String text) {
        System.out.println("\t____________________________________________________________");
        System.out.print("\t");
        System.out.println(text.replace("\n", "\n\t"));
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }
}
