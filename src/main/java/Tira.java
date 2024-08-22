import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Tira {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        PrintWriter printer = new PrintWriter(System.out);
        String logo = "TIRAMISU THE CAFE CAT (TIRA)";
        System.out.println("MIAO (Hello) from\n" + logo + "\n" +
                "What can I do for you, miao?\n");
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                printer.print(command + "\n");
                printer.flush();
            }
        }
       printer.println("Bye. Come back with treats, miao!");
        printer.close();
        // add scanner to take inputs (in string)
        // change all the commands into a lower case
        // have a list of words that could be a command
    }
}
