import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("---------------");
        System.out.println("Hello I am Schedulo!");
        System.out.println("What can I do for you?");
        System.out.println("---------------");
    }

    public void showLine() {
        System.out.println("---------------");
    }

    public void showLoadingError() {
        System.out.println("File not found, please create a new file data.txt under data folder outside src");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void close() {
        sc.close();
    }
}
