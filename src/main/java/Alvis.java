import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alvis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> tracker = new ArrayList<>();
        System.out.println("So you require my assistance?");

        while (true) {
            String userInput = sc.nextLine().toLowerCase();
            if (userInput.equals("bye")) {
                System.out.println("Understood.");
                break;
            } else if (userInput.equals("list")) {
                for (String s: tracker) {
                    System.out.println(s);
                }
            } else {
                tracker.add(userInput);
                System.out.println("Added " + userInput);
            }
        }
    }
}
