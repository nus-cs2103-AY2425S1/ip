import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Darkpool {
    public static void main(String[] args) {
        final String greeting = "it’s darkpool. what twisted reason dragged me into your misery?";
        final String bye = "contact me again and you'll regret it.";
        ArrayList<Task> taskList = new ArrayList<>();
        output(greeting);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String[] userInput = scanner.nextLine().split(" ", 2);

            if (userInput.length == 2) {
                userInput[1] = userInput[1].trim();
            }

            try {
                switch (userInput[0]) {
                    case "bye" -> {
                        output(bye);
                        scanner.close();
                        return;
                    }

                    case "list" -> {
                        StringBuilder temp = new StringBuilder("why am i here\n\t\t");
                        for (int i = 0; i < taskList.size(); i++) {
                            temp.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n\t\t");
                        }
                        temp.setLength(temp.length() - 3);
                        output(String.valueOf(temp));
                    }

                    case "mark" -> {
                        int num = taskAction(taskList, userInput);
                        taskList.get(num).markDone();
                        output("why do i have to mark this mess\n\t\t" + taskList.get(num).toString());
                    }

                    case "unmark" -> {
                        int num = taskAction(taskList, userInput);
                        taskList.get(num).markUndone();
                        output("why do i have to unmark this mess\n\t\t" + taskList.get(num).toString());
                    }


                    case "todo" -> {
                        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
                            throw new DarkpoolException("you missed the task number bruh");
                        }
                        String desc = userInput[1];
                        output(desc.length() + "");
                        taskList.add(new Todo(desc));
                        int size = taskList.size();
                        output("i have dumped this nonsense on the list\n\t\t" + taskList.get(size - 1).toString() + "\n\tnow you are stuck with " + size + " goddamn tasks");
                    }

                    case "deadline" -> {
                        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
                            throw new DarkpoolException("wheres the deadline of the task, and whats the task of the deadline??");
                        }
                        String input = userInput[1];
                        if (!input.contains("/by")) {
                            throw new DarkpoolException("bruh might as well use todo");
                        }
                        String[] array = getStrings(input);
                        String desc = array[0].trim();  // Task description
                        String by = array[1].trim();    // Deadline date
                        if (desc.isEmpty()) {
                            throw new DarkpoolException("whats the task of the deadline??");
                        }
                        taskList.add(new Deadline(desc, by));
                        int size = taskList.size();
                        output("i have dumped this nonsense on the list\n\t\t" + taskList.get(size - 1).toString() + "\n\tNow you are stuck with " + size + " goddamn tasks");
                    }


                    case "event" -> {
                        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
                            throw new DarkpoolException("the event syntax is [task_desc] /from [from_date] /to [to_date]");
                        }

                        String[] fromParts = getParts(userInput);

                        String[] toParts = getToParts(fromParts);
                        String desc = fromParts[0];
                        String from = toParts[0];
                        String to = toParts[1];
                        System.out.println(from);
                        taskList.add(new Event(desc, from, to));
                        int size = taskList.size();
                        output("i have dumped this nonsense on the list\n\t\t" + taskList.get(size - 1).toString() + "\n\tnow you are stuck with " + size + " goddamn tasks");
                    }

                    default -> throw new DarkpoolException("what???");

                }

            } catch (DarkpoolException e) {
                output(e.getMessage());
            }

        }
    }

    private static String[] getToParts(String[] fromParts) throws DarkpoolException {
        String[] toParts = fromParts[1].split("/to ");
        if (toParts.length > 3) {
            throw new DarkpoolException("only one to pls");
        }
        if (toParts.length < 2 || toParts[1].trim().isEmpty()) {
            throw new DarkpoolException("to where???");
        }
        if (toParts[0].trim().isEmpty()) {
            throw new DarkpoolException("from where???");
        }
        return toParts;
    }

    private static String[] getParts(String[] userInput) throws DarkpoolException {
        String[] fromParts = userInput[1].split("/from");
        if (fromParts.length > 2) {
            throw new DarkpoolException("only one from pls");
        }
        if (fromParts.length < 2 || fromParts[1].trim().isEmpty()) {
            throw new DarkpoolException("where is from bruh");
        }
        if (fromParts[0].trim().isEmpty()) {
            throw new DarkpoolException("task where???");
        }
        return fromParts;
    }

    private static String[] getStrings(String input) throws DarkpoolException {
        if ((input.length() - input.replace("/by", "").length()) / "/by".length() > 1) {
            throw new DarkpoolException("everything good? there cant be multiple deadlines");
        }
        String[] array = input.split("/by");
        if (array.length > 2) {
            throw new DarkpoolException("everything good? there cant be multiple deadlines");
        } else if (array.length == 0) {
            throw new DarkpoolException("wheres the deadline of the task, and whats the task of the deadline??");
        } else if (array.length == 1) {
            throw new DarkpoolException("so close! you forgot to enter the deadline");
        }
        return array;
    }

    private static int taskAction(ArrayList<Task> taskList, String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("you missed the task number bruh");
        }
        int num;
        try {
            num = Integer.parseInt(userInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DarkpoolException("do you know what a number is?");
        }

        if (num < 0 || num >= taskList.size()) {
            throw new DarkpoolException("do you know how to count? the task number is out of range");
        }

        return num;
    }

    private static void output(String input) {
        System.out.println("\t—————————————————————————————————————————————————————————————————\n\t" + input + "\n" + "\t—————————————————————————————————————————————————————————————————");
    // u001B[31m is the ANSI code for red text
        // u001B[0m
    }
}
