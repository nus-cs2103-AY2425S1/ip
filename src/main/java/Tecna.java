import java.util.Scanner;

public class Tecna {
    private Task[] taskList;
    private int todoSize;

    public Tecna() {
        this.taskList = new Task[100];
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
                int index = Integer.parseInt(input_words[1]);
                taskList[index - 1].markAsDone();
                System.out.println("Nice job! I've mark this as done. You deserve a short break <3");
                System.out.println(taskList[index - 1]);
            } else if (input_words[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(input_words[1]);
                taskList[index - 1].unMarkAsDone();
                System.out.println("I've mark this as undone. Keep going, my friend!");
                System.out.println(taskList[index - 1]);
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
        int boundary = item.indexOf(" ");
        String category = item.substring(0, boundary);
        Task task;
        if (category.equalsIgnoreCase("todo")) {
            task = new ToDo(item.substring(boundary + 1));
        } else if (category.equalsIgnoreCase("deadline")) {
            String[] description = item.substring(boundary + 1).split("/by");
            task = new Deadline(description[0].trim(), description[1].trim());
        } else {
            String[] description = item.substring(boundary + 1).split("/from | /to ");
            task = new Event(description[0].trim(), description[1].trim(), description[2].trim());
        }
        this.taskList[this.todoSize] = task;
        ++this.todoSize;
        System.out.println("Sure! I've added this task:");
        System.out.println(task);
        System.out.println(">> Now you have " + this.todoSize + (todoSize > 1 ? " tasks" : " task") + " in the list." );
    }

    public void listItems() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.todoSize; ++i) {
            System.out.println(i + 1 + ". " + this.taskList[i]);
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
