import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Maxine {

    static Set<String> list = new HashSet<>();

    /**
     * This method prompts the user to ask for their answer
     * @return The user's input
     */
    public static String answer() {
        Scanner scanner = new Scanner(System.in);
        String answer;
        System.out.print("What can I do for you today? : ");
        answer = scanner.nextLine();
        return answer;
    }

    /**
     * This is the main method
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) {
        System.out.println("Hi! Nice to meet you :) I am Maxine");
        String answer;
        do {
            answer = answer();
            answer = answer.toLowerCase();
            if (answer.equals("list")) {
                int count = 1;
                for (String item : list) {
                    System.out.println(count + ". " + item);
                    count++;
                }
            } else {
                list.add(answer);
            }

        }
        while (!answer.equals("bye max!"));

        System.out.println("\nBye! It was nice chatting with you. Hope to see you again soon!");
    }



}
