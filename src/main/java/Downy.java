import java.util.Scanner;
public class Downy {

    public static void main(String[] args) {
        String divider = "________________________________________\n";
        Scanner scanner = new Scanner(System.in);
        InputList inputs = new InputList();
        int inputCount = 0;
        System.out.println(divider + "Hello! I'm Downy.\nHow can I help?\n" + divider);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                if (inputCount == 0) {
                    System.out.println(divider + "There is nothing in the list!\n" + divider);
                    continue;
                }
                System.out.printf(divider);
                for (int i = 0; i < inputCount; i++) {
                    System.out.println((i + 1) + ". " + inputs.getEntry(i));
                }
                System.out.println(divider);
                continue;
            }
            inputs.setEntry(userInput, inputCount);
            inputCount++;
            System.out.println(divider + "added: " + userInput + "\n" + divider);
        }
        scanner.close();
        System.out.println(divider + "Bye! Yippee!");
    }
}
