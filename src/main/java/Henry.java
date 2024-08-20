import java.util.Scanner;

public class Henry {

    /**
     * Prints greetings
     *
     */
    public static void greetings() {
        String greetings = "Hello! I'm Henry\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
    }

    /**
     * Prints exit
     *
     */
    public static void bye() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
    }

    /**
     * Prints all the tasks recorded
     *
     * @param tasks array of tasks recorded
     * @param index number of tasks recorded
     */
    public static void printList(Task[] tasks, int index) {
        System.out.println("\nHere are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println(i + 1
                    + ". ["
                    + tasks[i].getStatusIcon()
                    + "] "
                    + tasks[i].getDescription());
        }
        System.out.println();
    }

    /**
     * Adds new task into the array of tasks recorded
     *
     * @param tasks array of tasks recorded
     * @param index number of tasks recorded
     * @param input name of task
     */
    public static void addTask(Task[] tasks, int index, String input) {
        tasks[index] = new Task(input);
        System.out.println("\n"
                + "added: "
                + input
                + "\n");
    }

    /**
     * Changes status of task
     *
     * @param tasks array of tasks recorded
     * @param words user input
     */
    public static void changeTaskStatus(Task[] tasks, String[] words){
        int number = Integer.parseInt(words[1]);
        if (words[0].equals("mark")) {
            tasks[number - 1].mark();
            System.out.println("\nNice! I've marked this task as done:\n"
                    + "[X] "
                    + tasks[number - 1].getDescription()
                    + "\n");
        } else {
            tasks[number - 1].unmark();
            System.out.println("\nOK, I've marked this task as not done yet:\n"
                    + "[ ] "
                    + tasks[number - 1].getDescription()
                    + "\n");
        }
    }

    public static void main(String[] args) {
        greetings();

        Task[] tasks = new Task[100];

        Scanner scanner = new Scanner(System.in);
        int index = 0;
        do {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println();
                bye();
                break;
            } else if (input.equals("list")) {
                printList(tasks, index);
            } else {
                String[] words = input.split(" ");
                if (words[0].equals("mark") || words[0].equals("unmark")) {
                    changeTaskStatus(tasks, words);
                } else {
                    addTask(tasks, index, input);
                    index++;
                }

            }
        } while (true);
    }
}
