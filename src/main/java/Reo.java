import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Reo {
    public static void main(String[] args) {
        // Create a new Scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------\n" +
                "Hello! I'm Reo.\nWhat can I do for you?\n"
                + "----------------------");

        String currInput;
        currInput = "placeholder";

        // ArrayList<Task> todos = new ArrayList<>();
        while (!currInput.toLowerCase().equals("bye")) {
            String toPrint = "";
            toPrint = "----------------------\n";
            currInput = scanner.nextLine().trim();
            toPrint += currInput + "\n";
            toPrint += "----------------------";
            System.out.println(toPrint);
        }

        System.out.println("Bye!");
        scanner.close();
    }
}
