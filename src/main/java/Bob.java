import java.util.Objects;
import java.util.Scanner;
public class Bob {
    public static void main(String[] args) throws CommandNotFoundException, MissingParamsException {
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
            String cmd = input.split(" ", 2)[0];
            String rest = input.split(" ", 2).length > 1 ? input.split(" ", 2)[1] : "";
            String[] splitString;

            String formatReply = String.format(" Got it. I've added this task:" + "\n"
                    + taskList[position] + "\n"
                    + "Now you have %s tasks in the list", position + 1) + "\n" + blankline;

            // check for the input case
            try {
                switch (cmd.toLowerCase()) {
                    case "bye":
                        exit = true;
                        break;

                    case "list":
                        String retString = "";
                        for (int i = 0; i < position; i++) {
                            retString += (i + 1) + ". " + taskList[i] + "\n";
                        }
                        System.out.println("Here are the tasks in your list:" + "\n"
                                + retString + blankline);
                        break;

                    case "mark":
                        markPos = Integer.parseInt(rest) - 1;
                        taskList[markPos].setDone();
                        System.out.println("Nice! I've marked this task as done:" + "\n"
                                + taskList[markPos] + "\n" + blankline);
                        break;

                    case "unmark":
                        markPos = Integer.parseInt(rest) - 1;
                        taskList[markPos].setUndone();
                        System.out.println("OK, I've marked this task as not done yet:" + "\n"
                                + taskList[markPos] + "\n" + blankline);
                        break;

                    case "todo":
                        if (Objects.equals(rest, "")) throw new MissingParamsException("todo");
                        taskList[position] = new Todo(rest);
                        System.out.println(String.format(" Got it. I've added this task:" + "\n"
                                + taskList[position] + "\n"
                                + "Now you have %s tasks in the list", position + 1) + "\n" + blankline);
                        position++;
                        break;

                    case "event":
                        splitString = rest.split("/from|/to");
                        if (Objects.equals(splitString[0], "") || Objects.equals(splitString[1], "")) throw new MissingParamsException("event");
                        taskList[position] = new Event(splitString[0], "from:" + splitString[1]
                                + "to:" + splitString[2]);
                        System.out.println(String.format(" Got it. I've added this task:" + "\n"
                                + taskList[position] + "\n"
                                + "Now you have %s tasks in the list", position + 1) + "\n" + blankline);
                        position++;
                        break;

                    case "deadline":
                        splitString = rest.split("/by");
                        if (Objects.equals(splitString[0], "") || Objects.equals(splitString[1], "")) throw new MissingParamsException("deadline");
                        taskList[position] = new Deadline(splitString[0], splitString[1]);
                        System.out.println(String.format(" Got it. I've added this task:" + "\n"
                                + taskList[position] + "\n"
                                + "Now you have %s tasks in the list", position + 1) + "\n" + blankline);
                        position++;
                        break;

                    default:
                        throw new CommandNotFoundException();
                }
            } catch (CommandNotFoundException | MissingParamsException c) {
                System.out.println(c + "\n" + blankline);
            }

        }
        System.out.println(blankline + "\n" + "Bye. Hope to see you again soon!");

    }
}
