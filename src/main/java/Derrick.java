import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Derrick {

    private final ArrayList<String> toDo = new ArrayList<>();
    public void greetings() {
        System.out.println("Hello, I am Derrick");
        System.out.println("What can I do for you?");
    }


    public void addTodo() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            }
            else if (input.equals("list")) {
                for (int i = 0; i < this.toDo.size(); i++) {
                    System.out.println((i + 1) + "." + this.toDo.get(i));
                }
            } else {
                this.toDo.add(input);
                System.out.println("added:" + input);
            }
        }
    }


    public static void exit() {
        System.out.println("Goodbye!");
    }
    public static void main(String[] args) {
        Derrick chatbot = new Derrick();
        chatbot.greetings();
        chatbot.addTodo();
    }
}
