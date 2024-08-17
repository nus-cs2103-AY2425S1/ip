import java.util.Scanner;

public class Rizzler {
    public static void main(String[] args) {
        String separator = "_______________________________________________________\n";
        String greeting = separator
                + "Hello! I'm the Rizzler.\n"
                + "What can I do for you today?\n"
                + separator;
        String goodbye = separator
                + "Bye! Rizz you later!\n"
                + separator;
        Scanner sc = new Scanner(System.in);

        System.out.println(greeting);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(goodbye);
                break;
            } else {
                System.out.println(separator + input + "\n" + separator);
            }
        }
    }
}
