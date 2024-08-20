import java.util.Scanner;

public class Maxine {
    /**
     * This is the main method
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) {
        System.out.println("Hi! Nice to meet you :) I am Maxine");
        String answer;
        do {
            answer = answer();
        }
        while (!answer.equals("Bye Max!"));

        System.out.println("\nBye! It was nice chatting with you. Hope to see you again soon!");
    }

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


}
