import java.util.Scanner;

public class Tecna {
    private Task[] todo;
    private int todoSize;

    public Tecna() {
        this.todo = new Task[100];
        this.todoSize = 0;
    }

    public void exitChatBot() {
        System.out.println("----------------------------------------------");
        System.out.println("Pleased to help you! See you again ^_^");
        System.out.println("----------------------------------------------");
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        if (input.equalsIgnoreCase("bye")) {
            this.exitChatBot();
        } else {
            System.out.println("----------------------------------------------");
            System.out.println(input);
            System.out.println("----------------------------------------------");
        }
        sc.close();
    }

    public void getRequest() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] input_words = input.split(" ");
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("----------------------------------------------");
            if (input.equalsIgnoreCase("list")) {
                this.listItems();
            } else if (input_words[0].equalsIgnoreCase("mark")) {
                int index = Integer.valueOf(input_words[1]);
                todo[index - 1].markAsDone();
                System.out.println("Nice job! I've mark this as done. You deserve a short break <3");
                System.out.println(todo[index - 1]);
            } else if (input_words[0].equalsIgnoreCase("unmark")) {
                int index = Integer.valueOf(input_words[1]);
                todo[index - 1].unMarkAsDone();
                System.out.println("I've mark this as undone. Keep going, my friend!");
                System.out.println(todo[index - 1]);
            } else {
                this.addItem(input);
            }

            System.out.println("----------------------------------------------");

            input = sc.nextLine();
            input_words = input.split(" ");
        }
        this.exitChatBot();
        sc.close();
    }

    public void addItem(String item) {
        Task task = new Task(item);
        this.todo[this.todoSize] = task;
        ++this.todoSize;
        System.out.println("added: " + item);
    }

    public void listItems() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.todoSize; ++i) {
            System.out.println(i + 1 + ". " + this.todo[i]);
        }
    }

    public static void main(String[] args) {
        String logo = " **          **\n" +
                      "*  *        *  *\n" +
                      "*   *      *   *\n" +
                      "*    *    *    *\n" +
                      "*     *  *     *\n" +
                      " *     **     *\n" +
                      "  *    **    *\n" +
                      "   *   **   *\n" +
                      "    *  **  *\n" +
                      "     ******\n" +
                      "      ****\n" +
                      "     * ** *\n" +
                      "    *  **  *\n" +
                      "    ***  ***\n";
        System.out.println(logo);
        System.out.println("I'm Tecna!\nHow can I help you?");
        System.out.println("----------------------------------------------");
        Tecna tecna = new Tecna();
        tecna.getRequest();
    }
}
