package trackie.ui;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    public void greet() {
        System.out.println("Wassup m8 how's your day =)");
    }

    public void sayGoodbyeMessage() {
        System.out.println("Seeya!");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void displayErrorMessage(TrackieException e) {
        System.out.println(e.getMessage());
    }
}
