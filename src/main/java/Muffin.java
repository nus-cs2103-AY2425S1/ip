import java.sql.SQLOutput;
import java.util.Scanner;

public class Muffin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " __  __       __  __ _\n" +
                "|  \\/  |_  _ / _|/ _(_)_ _  \n" +
                "| |\\/| | || |  _|  _| | ' \\ \n" +
                "|_|  |_|\\_,_|_| |_| |_|_||_|\n";

        String helloMsg = "Hello~ I'm Muffin \n" +
                "What can I do for you?";

        System.out.println(logo + "\n" + helloMsg);
        command(sc);
    }

    public static void command(Scanner sc) {
        String userInput = sc.nextLine();

        if (userInput.equalsIgnoreCase("bye")) {
            System.out.println("Goodbye~ Hope to see you again soon!");
        } else {
            System.out.println(userInput);
            command(sc);
        }
    }
}
