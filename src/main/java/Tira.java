import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tira {
    public static void main(String[] args) {
        // variable declarations
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        PrintWriter printer = new PrintWriter(System.out);
        String logo = "TIRAMISU THE CAFE CAT (TIRA)";
        ArrayList<String> userInputs = new ArrayList<String>();
        // printing
        System.out.println("MIAO (Hello) from\n" + logo + "\n" +
                "What can I do for you, miao?\n");
        // check the user input
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                printer.print("added: " + command + "\n");
                printer.flush();
                if (command.equals("list")) {
                    for (int i = 0; i < userInputs.size(); i++) {
                        printer.print((i + 1) + ". " + userInputs.get(i) + "\n");
                        printer.flush();
                    }
                } else {
                    userInputs.add(command);
                }
            }
        }
       printer.println("Bye. Come back with treats, miao!");
        printer.close();
        // add scanner to take inputs (in string)
        // change all the commands into a lower case
        // have a list of words that could be a command
    }
}
