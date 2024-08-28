import java.util.ArrayList;
import java.util.Scanner;

public class Noosy {
    public static void main(String[] args) {
        String greeting = "Heyo! This is Noosy! \n" +
                "What do ya need from me?";
        String goodbye = "Alright, see ya!";
        ArrayList<String> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting);

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (!userInput.equals("list")) {
                System.out.println("added: " + userInput); // this echos anything but "bye"
                list.add(userInput);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            }
            userInput = scanner.nextLine(); // continue scanning for input
        }

        // after "bye"
        System.out.println(goodbye);
        scanner.close();
    }
}
