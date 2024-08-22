import java.util.Scanner;

public class Bob {
    private static final String SEPARATOR = "____________________________________________________________";
    private static String input = "";

    private enum Command {
        BYE("bye") {
            @Override
            public void run() {
                exit();
            }
        };

        public final String CMD;
        public abstract void run();

        Command(String cmd) {
            CMD = cmd;
        }
    }

    private static void say(String text) {
        System.out.println("\n " + text.replace("\n", "\n ") + "\n" + SEPARATOR);
    }

    private static void greet() {
        System.out.print(SEPARATOR);
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
        '----------------' '----------------' '----------------'""";
        Scanner scanner = new Scanner(System.in);

        System.out.println(logo);
        greet();
        while (true) {
            input = scanner.nextLine();
            for (Command c : Command.values()) {
                if (input.equals(c.CMD)) {
                    c.run();
                    break;
                }
            }
        }
    }
}
