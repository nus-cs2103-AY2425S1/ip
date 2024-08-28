package meeju;

import java.util.Scanner;

/**
 * Handles user interactions, including receiving inputs and redirecting the input message for parsing.
 */
public class UserInteraction {
    /**
     * Starts an interactive loop with the user, where instructions are read from the keyboard entry,
     * and sent for parsing and executing the instruction.
     * The loop continues until the user provides the command "bye".
     */
    public void interact() {
        Scanner scannerObject = new Scanner(System.in);
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage);
        Parser parser = new Parser();
        while (true) {
            String instruction = scannerObject.nextLine();
            System.out.println(instruction);
            System.out.println(Meeju.LINE_BREAK);
            int result = parser.parse(taskList, instruction);
            if (result == -1) {
                break;
            }
            System.out.println(Meeju.LINE_BREAK);
        }
    }
}
