package FlyChat.Core;

import java.util.Scanner;

public class Ui {
    private static final String lineBreak = "\n--------------------\n";
    private static Scanner userInput = new Scanner(System.in);


    public void greetUser() {
        System.out.println(lineBreak);
        System.out.println("Hello! I'm FlyChat\nWhat can I do for you?");
        System.out.println(lineBreak);
    }

    public void bye() {
        System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
        userInput.close();
    }

    public String getNextLine() {
        String inputString = userInput.nextLine();
        return inputString;
    }

    public void announceString(String str) {
        System.out.println(lineBreak);
        System.out.println(str);
        System.out.println(lineBreak);
    }
}
