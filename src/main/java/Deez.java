import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Deez {
    static void say(String msg) {
        System.out.println("> " + msg);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String CMD_bye = "bye";
        String seperator = "____________________________________________________________";
        String logo = """
                    ____  _________________
                   / __ \\/ ____/ ____/__  /
                  / / / / __/ / __/    / /\s
                 / /_/ / /___/ /___   / /__
                /_____/_____/_____/  /____/
                                          \s
                """;
        System.out.println(logo);
        System.out.println(seperator);
        Deez.say("Hello! I'm Deez!");
        Deez.say("What can I do you for?");
        while (1 == 1) {
            String str = br.readLine();
            if (str.equals(CMD_bye)) {
                break;
            }
            Deez.say(str);
        }
        System.out.println(seperator);
        Deez.say("Bye. Hope to see you soon!");
        System.out.println(seperator);
    }

}
