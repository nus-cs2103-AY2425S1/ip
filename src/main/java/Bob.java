import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String LINE_PREFIX = "    ";

    private static String argument = "";
    private static final List<Task> list = new ArrayList<>();

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
                if (list.isEmpty()) {
                    say("You have not added any tasks yet.");
                } else {
                    StringBuilder text = new StringBuilder();
                    int i;
                    for (i = 0; i < list.size() - 1; ++i) {
                        text.append(i + 1).append(".").append(list.get(i)).append("\n");
                    }
                    text.append(i + 1).append(".").append(list.get(i));
                    say(text.toString());
                }
            }
        },
        TODO("todo") {
            @Override
            public void run() {
                Task task = new Todo(argument);
                list.add(task);
                say("added: " + task);
            }
        },
        DEADLINE("deadline") {
            @Override
            public void run() {
                int byIndex = argument.lastIndexOf(" /by ");
                if (byIndex == -1) {
                    say("Deadlines require a 'by' argument!");
                } else {
                    Task task = new Deadline(
                            argument.substring(0, byIndex),
                            argument.substring(byIndex + 5)
                    );
                    list.add(task);
                    say("added: " + task);
                }
            }
        },
        EVENT("event") {
            @Override
            public void run() {
                int fromIndex = argument.lastIndexOf(" /from ");
                int toIndex = argument.lastIndexOf(" /to ");
                if (fromIndex == -1 || toIndex == -1) {
                    say("Events require a 'from' and 'to' argument!");
                } else {
                    String desc = fromIndex < toIndex
                                    ? argument.substring(0, fromIndex)
                                    : argument.substring(0, toIndex);
                    String from = fromIndex < toIndex
                                    ? argument.substring(fromIndex + 7, toIndex)
                                    : argument.substring(fromIndex + 7);
                    String to = fromIndex < toIndex
                                    ? argument.substring(toIndex + 5)
                                    : argument.substring(toIndex + 5, fromIndex);
                    Task task = new Event(desc, from, to);
                    list.add(task);
                    say("added: " + task);
                }
            }
        },
        MARK("mark") {
            @Override
            public void run() {
                if (!argument.isBlank()) {
                    int i = Integer.parseInt(argument) - 1;
                    list.get(i).mark();
                    say("Nice! I've marked this task as done:\n  " +
                            list.get(i));
                }
            }
        },
        UNMARK("unmark") {
            @Override
            public void run() {
                if (!argument.isBlank()) {
                    int i = Integer.parseInt(argument) - 1;
                    list.get(i).unmark();
                    say("OK, I've marked this task as not done yet:\n  " +
                            list.get(i));
                }
            }
        },
        CATCH_ALL("") {
            @Override
            public void run() {
                say("Unknown command");
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
        say("Hey there! Bob at your service.\n" +
                "Let's roll up our sleeves and get to work!");
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
            String[] input = scanner.nextLine().split(" ", 2);
            String command = input[0];
            argument = input.length == 1 ? "" : input[1];
            for (Command c : Command.values()) {
                if (command.equals(c.CMD)) {
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
