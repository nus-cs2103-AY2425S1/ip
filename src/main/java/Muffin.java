import java.util.Scanner;

public class Muffin {
    enum Command {
        BYE, LIST, MARK, UNMARK, ADD
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        String logo = " __  __       __  __ _\n" +
                "|  \\/  |_  _ / _|/ _(_)_ _  \n" +
                "| |\\/| | || |  _|  _| | ' \\ \n" +
                "|_|  |_|\\_,_|_| |_| |_|_||_|\n";

        String helloMsg = "Hello~ I'm Muffin \n" +
                "What can I do for you?";

        System.out.println(logo + "\n" + helloMsg);
        command(sc, 0, list);
    }

    public static void command(Scanner sc, int count, Task[] list) {
        String userInput = sc.nextLine();
        String[] words = userInput.split(" ");

        Command command;
        try {
            String firstWord = words[0].trim().toUpperCase();
            command = Command.valueOf(firstWord);
        } catch (IllegalArgumentException e) {
            command = Command.ADD;
        }

        switch(command) {
            case BYE:
                System.out.println("Goodbye~ Hope to see you again soon!");
                break;

            case LIST:
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < count; i++) {
                    System.out.println((i+1) + "." + list[i]);
                }
                command(sc, count, list);
                break;

            case MARK:
                Task t = list[Integer.parseInt(words[1]) - 1];
                t.isDone = true;
                System.out.println("Yay! Marked as done:\n" + t);
                command(sc, count, list);
                break;

            case UNMARK:
                Task m = list[Integer.parseInt(words[1]) - 1];
                m.isDone = false;
                System.out.println("Ok. Marked as not done yet:\n" + m);
                command(sc, count, list);
                break;

            case ADD:
                list[count] = new Task(userInput);
                System.out.println("added: " + userInput);
                command(sc, ++count, list);
                break;
        }
    }
}
