import java.util.Scanner;

public class Atlas {
    // @@author patorjk.com
    // Reused from https://patorjk.com/software/taag/#p=display&f=Isometric1&t=Atlas
    // with minor modifications
    public static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        String logo = """
                      ___           ___           ___       ___           ___    \s
                     /\\  \\         /\\  \\         /\\__\\     /\\  \\         /\\  \\   \s
                    /::\\  \\        \\:\\  \\       /:/  /    /::\\  \\       /::\\  \\  \s
                   /:/\\:\\  \\        \\:\\  \\     /:/  /    /:/\\:\\  \\     /:/\\ \\  \\ \s
                  /::\\~\\:\\  \\       /::\\  \\   /:/  /    /::\\~\\:\\  \\   _\\:\\~\\ \\  \\\s
                 /:/\\:\\ \\:\\__\\     /:/\\:\\__\\ /:/__/    /:/\\:\\ \\:\\__\\ /\\ \\:\\ \\ \\__\\
                 \\/__\\:\\/:/  /    /:/  \\/__/ \\:\\  \\    \\/__\\:\\/:/  / \\:\\ \\:\\ \\/__/
                      \\::/  /    /:/  /       \\:\\  \\        \\::/  /   \\:\\ \\:\\__\\ \s
                      /:/  /     \\/__/         \\:\\  \\       /:/  /     \\:\\/:/  / \s
                     /:/  /                     \\:\\__\\     /:/  /       \\::/  /  \s
                     \\/__/                       \\/__/     \\/__/         \\/__/   \s
                """;

        System.out.println("Hello from\n" + logo);
        Atlas.greet();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "bye":
                    Atlas.exit();
                    return;
                default:
                    Atlas.print(command);
                    break;
            }
        }
    }

    // Prints messages with lines underneath them
    public static void print(String message) {
        System.out.println('\n' + Atlas.LINE);
        System.out.println(message);
        System.out.println(Atlas.LINE + '\n');
    }

    public static void greet() {
        Atlas.print("Hello! I'm Atlas \n" + "What can I do for you?");
    }

    public static void exit() {
        Atlas.print("Bye. Hope to see you again soon!");
    }
}
