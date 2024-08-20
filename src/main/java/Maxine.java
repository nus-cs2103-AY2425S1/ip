import java.util.Scanner;
import java.util.HashMap;

public class Maxine {

    static HashMap<Integer, Task> list = new HashMap<>();

    /**
     * This method prompts the user to ask for their answer
     * @return The user's input
     */
    public static Task answer() {
        Scanner scanner = new Scanner(System.in);
        Task answer;
        System.out.print("What can I do for you today? : ");
        answer = new Task(scanner.nextLine());
        return answer;
    }

    /**
     * This is the main method
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi! Nice to meet you :) I am Maxine");
        String answer;
        do {
            System.out.print("What can I do for you today? : ");
            answer = scanner.nextLine().toLowerCase();
            if (answer.equals("list")) {
                for (int i = 1; i < (list.size() + 1); i++) {
                    System.out.println(i + ". " + list.get(i));
                }
            } else if (answer.contains("mark") || answer.contains("unmark")) {
                String[] arr = answer.split(" ");
                int mark = Integer.parseInt(arr[1]);
                Task curr = list.get(mark);
                curr.changeStatus();
            } else {
                Task task = new Task(answer);
                list.put(task.num, task);
            }

        }
        while (!answer.equals("bye max!"));

        System.out.println("\nBye! I have been maxxed out and am going to sleep. Hope to see you again soon!");
    }



}
