import java.util.Scanner;

public class Maxine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi! Nice to meet you :) I am Maxine");
        String answer;
        do {
            System.out.print("What can I do for you today? : ");
            answer = scanner.nextLine();

            if (!answer.equals("Bye Max!")) {
                System.out.println(answer);
            }
        }
        while (!answer.equals("Bye Max!"));

        System.out.println("\nBye! It was nice chatting with you. Hope to see you again soon!");
    }
}
