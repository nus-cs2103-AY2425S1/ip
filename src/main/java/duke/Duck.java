package duke;

import java.util.Scanner;

public class Duck {
    public static void main(String[] args) throws DuckException {
        Scanner scan = new Scanner(System.in);
        TaskList cmds = new TaskList(Storage.load(), Storage.loadNum());

        System.out.println("Hello! I'm DUCK\n What can I do for you?");
        boolean shouldContinue = true;
        while (shouldContinue) {
            String userCmd = scan.nextLine();
            shouldContinue = Parser.parseCommand(cmds, userCmd);
        }
        scan.close();
    }

}

