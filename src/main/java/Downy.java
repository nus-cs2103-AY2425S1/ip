import java.util.Scanner;
public class Downy {

    public static void main(String[] args) {
        String divider = "________________________________________\n";
        Scanner scanner = new Scanner(System.in);
        System.out.println(divider + "Hello! I'm Downy.\nHow can I help?\n" + divider);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(divider + userInput + "\n" + divider);
        }
        scanner.close();
        System.out.println(divider + "Bye! Yippee!");
    }
}
