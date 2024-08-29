package duke;

import java.util.Scanner;

public class Duke {

    public static void run() {
        Storage.loadFile();
        Ui.greet();
        Scanner sc = new Scanner(System.in);
        String command;
        while (sc.hasNext()) {
            command = sc.nextLine();
            if ("bye".equals(command.split(" ", 2)[0])) {
                Ui.bye();
                break;
            }
            Parser.parseCommand(command);
        }
        sc.close();
    }

    public static void main(String[] args) throws DukeException {
        Duke.run();
    }
}