import java.util.Scanner;
import java.util.ArrayList;
public class Bing {
    public static void main(String[] args) {
        String message = "______________________________\n"
                + "Hi! My name is Bing\n"
                + "How can I help you?\n"
                + "______________________________\n";
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<String> tasks = new ArrayList<String>();
        while (true) {
            input = scanner.nextLine();
            if (input.equals("Bye") || input.equals("bye")) {
                System.out.println("______________________________\n"
                        + "Bye. Have a good day.\n"
                        + "______________________________\n");
                break;
            } else if (input.equals("list")) {
                System.out.println("______________________________\n");
                for (int i=0 ; i<tasks.size() ; i++) {
                    System.out.println((i + 1)+". "+tasks.get(i));
                }
                System.out.println("______________________________\n");
            }
            else {
                tasks.add(input);
                System.out.println("______________________________\n"
                        + "Added: "+ input + "\n"
                        + "______________________________\n");
            }
        }
    }
}

