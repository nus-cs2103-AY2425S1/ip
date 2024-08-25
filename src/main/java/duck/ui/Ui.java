package duck.ui;

import duck.data.exception.DuckException;

import java.util.Scanner;

public class Ui {
    Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void showStartUpMessage() {
        System.out.println("Initializing Duck...");
    }

    public void showStartUpCompleteMessage() {
        System.out.println("Quack. Duck is up!");
    }

    public void sayHi() {
        String logo = """
                        ,---,                                  ,-.
                      .'  .' `\\                            ,--/ /|
                    ,---.'     \\          ,--,           ,--. :/ |
                    |   |  .`\\  |       ,'_ /|           :  : ' /
                    :   : |  '  |  .--. |  | :    ,---.  |  '  /
                    |   ' '  ;  :,'_ /| :  . |   /     \\ '  |  :
                    '   | ;  .  ||  ' | |  . .  /    / ' |  |   \\
                    |   | :  |  '|  | ' |  | | .    ' /  '  : |. \\
                    '   : | /  ; :  | : ;  ; | '   ; :__ |  | ' \\ \\
                    |   | '` ,/  '  :  `--'   \\'   | '.'|'  : |--'
                    ;   :  .'    :  ,      .-./|   :    :;  |,'
                    |   ,.'       `--`----'     \\   \\  / '--'
                    '---'                        `----'
                """;


        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duck.");
        System.out.println("What can I do for you, QUACK?\n");
    }

    public String readCommand() {
        if (in.hasNextLine()) {
            return(in.nextLine().trim());
        }
        return "bye";

    }

    public void showGoodbyeMessage() {
        System.out.println("Quack! Duck is going to sleep now. Goodbye!");
    }

    public void displayDukeExceptionMessage(DuckException de) {
        System.out.println(de.getMessage());
    }

    public void displayIllegalArgumentMessage(IllegalArgumentException iae) {
        System.out.println("Quack, that's not a valid instruction! I don't know how to respond to that.");
    }
}
