import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Deez {
    static void say(String... msgs) {
        System.out.println("____________________________________________________________");
        for (String msg: msgs) {
            System.out.println("> " + msg);
        }
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String CMD_bye = "bye";
        String CMD_list = "list";
        String logo = """
                    ____  _________________
                   / __ \\/ ____/ ____/__  /
                  / / / / __/ / __/    / /\s
                 / /_/ / /___/ /___   / /__
                /_____/_____/_____/  /____/
                                          \s
                """;
        System.out.println(logo);
        ArrayList<String> items = new ArrayList<String>();
        Deez.say("Hello! I'm Deez!", "What can I do you for?");
        while (1 == 1) {
            String str = br.readLine();
            if (str.isEmpty()) {
                // Don't do anything
                continue;
            }
            if (str.equals(CMD_bye)) {
                // Exit
                break;
            }
            if (str.equals(CMD_list)) {
                // Print list
                int cnt = 1;
                System.out.println("____________________________________________________________");
                for (String s: items) {
                    System.out.println(cnt + ". " + s);
                    cnt+=1;
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            // Add item to items list
            items.add(str);
            Deez.say("Added " + str);
        }
        Deez.say("Bye. Hope to see you soon!");
    }

}
