import java.util.Scanner;

public class Ui {
    Scanner myObj = new Scanner(System.in);
    public void welcomeMsg(String welcome) {
        System.out.println(" Hello! I'm BuddyBot" + "\n" +
                " What can I do for you?");
    }

    public void goodbyeMsg() {
        System.out.println(" Bye. Hope to see you again soon!");
    }
    public void showLoadingError(String msg) {
        System.out.println(msg);
    }

    public void showSuccessMsg(int i) {
        System.out.println("Now you have " + i + " tasks in the list.");
    }

    public String getUserInput() {
        return myObj.nextLine();
    }

    public void showLine() {
        myObj.nextLine();
    }

}
