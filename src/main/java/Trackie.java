import java.util.Scanner;

public class Trackie {

    public void greet() {
        System.out.println("Hello, I'm Trackie. Nice to meet you =)");
        System.out.println("Note: type \"bye\" to exit.");
    }

    public void exit() {
        System.out.println("Seeya!");
    }

    public void echo(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) {
        Trackie bot = new Trackie();
        bot.greet();
        while (true) {
            Scanner s = new Scanner(System.in);
            System.out.print("Type something: ");
            String userInput = s.nextLine();
            if (userInput.equals("bye")) {
                bot.exit();
                break;
            }
            bot.echo(userInput);
        }




    }
}
