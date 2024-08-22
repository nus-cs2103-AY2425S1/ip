import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String blankline = "____________________________________________________________ \s";
        String skeleton = """
                ____________________________________________________________ \s
                 Hello! I'm Bob \s
                 What can I do for you? \s
                ____________________________________________________________ \s
                """;
        System.out.println(skeleton);

        Task[] taskList = new Task[100];
        Scanner scanner = new Scanner(System.in);
        int position = 0;
        int markPos = 0;

        boolean exit = false;
        while (!exit) {
            System.out.print("Text: ");
            String input = scanner.nextLine();

            // check for the input case
            switch (input.split(" ")[0].toLowerCase()) {
                case "bye":
                    exit = true;
                    break;
                case "list":
                    String retString = "";
                    for (int i = 0; i < position; i++) {
                        retString += (i + 1) + ". " + taskList[i].getString() + "\n";
                    }
                    System.out.println("Here are the tasks in your list:" + "\n"
                            + retString + blankline);
                    break;
                case "mark":
                    markPos = Integer.parseInt(input.split(" ")[1]) - 1;
                    taskList[markPos].setDone();
                    System.out.println("Nice! I've marked this task as done:" + "\n"
                            + taskList[markPos].getString() + "\n" + blankline);
                    break;
                case "unmark":
                    markPos = Integer.parseInt(input.split(" ")[1]) - 1;
                    taskList[markPos].setUndone();
                    System.out.println("OK, I've marked this task as not done yet:" + "\n"
                            + taskList[markPos].getString() + "\n" +blankline);
                    break;
                default:
                    taskList[position] = new Task(input);
                    System.out.println("added: " + input + "\n" + blankline);
                    position++;
                    break;
            }
        }
        System.out.println(blankline + "\n" + "Bye. Hope to see you again soon!");

    }
}
