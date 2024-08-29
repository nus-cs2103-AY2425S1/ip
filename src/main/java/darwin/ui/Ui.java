package darwin.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    private static void reply(String txt) {
        String line = "-".repeat(50);
        System.out.println(line);
        System.out.println(txt);
        System.out.println(line);
    }

    public void send(String txt) {
        Ui.reply(txt);
    }

    public String read() {
        return this.scanner.nextLine();
    }

}
