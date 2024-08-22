import java.util.Scanner;

public class Donk {
    public static void main(String[] args) {
        String greeting = " ____________________________________________________________\n" +
                " Hello! I'm Donk, the super intelligent chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMsg = "    Bye bro, catch 'ya later\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(byeMsg);
                break;
            }
            System.out.println("    " + userInput);
        }

    }
}
