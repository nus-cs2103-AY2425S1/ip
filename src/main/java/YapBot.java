import java.util.Scanner;

public class YapBot {
    public static void main(String[] args) {
        String templateLine = "\n-------------------------------------------\n";
        System.out.println(templateLine + "Hello, I am YapBot. \nHow can I help?" + templateLine);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            System.out.println(templateLine + input + templateLine);
            input = in.nextLine();

        }

        in.close();
        System.out.println(templateLine + "Alright, enough yapping for one day." + templateLine);
    }
}
