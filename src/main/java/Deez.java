import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        String logo = """
                    ____  _________________
                   / __ \\/ ____/ ____/__  /
                  / / / / __/ / __/    / /\s
                 / /_/ / /___/ /___   / /__
                /_____/_____/_____/  /____/
                                          \s
                """;
        System.out.println(logo);
        Deez.say("Hello! I'm Deez!", "What can I do you for?");
        while (1 == 1) {
            String str = br.readLine();
            if (str.isEmpty()) {
                continue;
            }
            if (str.equals(CMD_bye)) {
                break;
            }
            Deez.say(str);
        }
        Deez.say("Bye. Hope to see you soon!");
    }

}
