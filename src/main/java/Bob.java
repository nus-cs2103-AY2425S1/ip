import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String LINE_PREFIX = "    ";
    private static String input = "";
    private static final ArrayList<String> list = new ArrayList<>();

    private enum Command {
        BYE("bye") {
            @Override
            public void run() {
                exit();
            }
        },
        LIST("list") {
            @Override
            public void run() {
                StringBuilder text = new StringBuilder();
                int i;
                for (i = 0; i < list.size() - 1; ++i) {
                    text.append(i + 1).append(". ").append(list.get(i)).append("\n");
                }
                text.append(i + 1).append(". ").append(list.get(i));
                say(text.toString());
            }
        },
        CATCH_ALL("") {
            @Override
            public void run() {
                list.add(input);
                say("added: " + input);
            }
        };

        public final String CMD;
        public abstract void run();

        Command(String cmd) {
            CMD = cmd;
        }
    }

    private static void say(String text) {
        String t = SEPARATOR + "\n " + text.replace("\n", "\n ") + "\n" + SEPARATOR;
        System.out.println(LINE_PREFIX + t.replace("\n", "\n" + LINE_PREFIX) + "\n");
    }

    private static void greet() {
        say("Hey there! Bob at your service.  \n" +
                "Let’s roll up our sleeves and get to work!");
    }

    private static void exit() {
        say("I'll be here if you need me. Catch you later!");
        System.exit(0);
    }

    public static void main(String[] args) {
        String logo = """
         .----------------. .----------------. .----------------.
        | .--------------. | .--------------. | .--------------. |
        | |   ______     | | |     ____     | | |   ______     | |
        | |  |_   _ \\    | | |   .'    `.   | | |  |_   _ \\    | |
        | |    | |_) |   | | |  /  .--.  \\  | | |    | |_) |   | |
        | |    |  __'.   | | |  | |    | |  | | |    |  __'.   | |
        | |   _| |__) |  | | |  \\  `--'  /  | | |   _| |__) |  | |
        | |  |_______/   | | |   `.____.'   | | |  |_______/   | |
        | |              | | |              | | |              | |
        | '--------------' | '--------------' | '--------------' |
        '----------------' '----------------' '----------------'
        """;
        Scanner scanner = new Scanner(System.in);

        System.out.println(logo);
        greet();
        while (true) {
            boolean executed = false;
            input = scanner.nextLine();
            for (Command c : Command.values()) {
                if (input.equals(c.CMD)) {
                    c.run();
                    executed = true;
                    break;
                }
            }
            if (!executed) {
                Command.CATCH_ALL.run();
            }
        }
    }
}
