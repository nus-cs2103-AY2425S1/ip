package atlas.ui;

import java.util.Scanner;

public class Ui {
    public static final String LINE = "____________________________________________________________";
    public final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void greet() {
        // @@author patorjk.com
        // Reused from https://patorjk.com/software/taag/#p=display&f=Isometric1&t=Ui
        // with minor modifications
        String logo = """
                     ___           ___           ___       ___           ___
                    /\\  \\         /\\  \\         /\\__\\     /\\  \\         /\\  \\
                   /::\\  \\        \\:\\  \\       /:/  /    /::\\  \\       /::\\  \\
                  /:/\\:\\  \\        \\:\\  \\     /:/  /    /:/\\:\\  \\     /:/\\ \\  \\
                 /::\\~\\:\\  \\       /::\\  \\   /:/  /    /::\\~\\:\\  \\   _\\:\\~\\ \\  \\
                /:/\\:\\ \\:\\__\\     /:/\\:\\__\\ /:/__/    /:/\\:\\ \\:\\__\\ /\\ \\:\\ \\ \\__\\
                \\/__\\:\\/:/  /    /:/  \\/__/ \\:\\  \\    \\/__\\:\\/:/  / \\:\\ \\:\\ \\/__/
                     \\::/  /    /:/  /       \\:\\  \\        \\::/  /   \\:\\ \\:\\__\\
                     /:/  /     \\/__/         \\:\\  \\       /:/  /     \\:\\/:/  /
                    /:/  /                     \\:\\__\\     /:/  /       \\::/  /
                    \\/__/                       \\/__/     \\/__/         \\/__/
                """;

        System.out.println("Hello from\n" + logo + '\n' + Ui.LINE);
        System.out.println("Hello! I'm Atlas\n" + "What can I do for you?");
        this.showLine();
    }

    public void print(String message) {
        System.out.println(Ui.LINE + '\n' + message);
    }

    public void showError(String message) {
        this.print(message);
    }

    public void exit() {
        this.print("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println(Ui.LINE + '\n');
    }
}
