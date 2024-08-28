import java.util.Scanner;

public class Noosy {
    public static void main(String[] args) {
        String greeting = "Heyo! This is Noosy! \n" +
                "What do ya need from me?";
        String goodbye = "Alright, see ya!";

        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting);

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput); // this echos anything but "bye"
            userInput = scanner.nextLine(); // continue scanning for input
        }

        // after "bye"
        System.out.println(goodbye);
        scanner.close();
    }
}
