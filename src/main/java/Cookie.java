import java.util.Scanner;
public class Cookie {
    public String printLogo() {
        return "    o      o    \n"
                + " ____\\____/____\n"
                + "|   _      _   |\n"
                + "|  / \\    / \\  |   /\n"
                + "|  \\_/    \\_/  |  /\n"
                + "|              | /\n"
                + "|______________|/\n"
                + "\n";
    }
    public String printGreet() {
        return "Hello! I'm Cookie\n"
                + "How can I help you?\n"
                + "\n";
    }
    public String printQuit() {
        return "Bye. See you soon!";
    }
    public static void main(String[] args) {
        Cookie cookie = new Cookie();
        System.out.println(cookie.printLogo() + cookie.printGreet());

        Scanner scanner = new Scanner(System.in);
        String echoText = scanner.nextLine();

        while(!echoText.equals("bye")) {
            System.out.println(echoText);
            echoText = scanner.nextLine();
        }

        System.out.println(cookie.printQuit());

    }
}
