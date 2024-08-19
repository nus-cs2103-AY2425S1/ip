import java.util.Scanner;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        Janet janet = new Janet();
        System.out.println(janet.greet());

        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                String exitMessage = janet.exit();
                System.out.println(exitMessage);
                break;
            }
            String echoedMessage = janet.echo(command);
            System.out.println(echoedMessage);
        }
    }
}
