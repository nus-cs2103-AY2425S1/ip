import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Ynch chatbot = new Ynch();
        System.out.println(chatbot.greet());
        while (true) {
            userInput = scanner.nextLine();
            
            if (userInput.equals("bye")) {
                System.out.println(chatbot.exit());
                break;
            } 

            if (userInput.equals("list")) {
                System.out.println(chatbot.list());
            } else if (userInput.startsWith("mark")) {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                System.out.println(chatbot.mark(i));
            } else if (userInput.startsWith("unmark")) {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                System.out.println(chatbot.unmark(i));
            } else if (userInput.startsWith("todo")) {
                System.out.println(chatbot.addTodo(userInput.split(" ", 2)[1]));
            } else if (userInput.startsWith("deadline")) {
                userInput = userInput.split(" ", 2)[1];
                String task = userInput.split("/by")[0];
                String deadline = userInput.split("/by")[1];
                System.out.println(chatbot.addDeadline(task, deadline));
            } else if (userInput.startsWith("event")) {
                userInput = userInput.split(" ", 2)[1];
                String task = userInput.split("/from")[0];
                String fromAndTo = userInput.split("/from")[1];
                String from = fromAndTo.split("/to")[0];
                String to = fromAndTo.split("/to")[1];
                System.out.println(chatbot.addEvent(task, from, to));
            }
            
            
        }
        scanner.close();  
    }
}