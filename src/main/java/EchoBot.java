import java.util.Scanner;

public class EchoBot {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        UI ui = new UI();
        ui.greeting();

        String command = scan.next();
        while (!"bye".equals(command)) {
            ui.echo(command);
            command = scan.next();
        }
        ui.exit();
    }
}
