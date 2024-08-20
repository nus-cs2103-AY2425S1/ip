import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Blitz {
    private static void printInDivider(String divider, String cont) {
        System.out.print(divider + cont + divider);
    }

    private static void printTaskAddedWithDivider(String divider, String tab, String type, int size, Task task) {
        String toPrint = tab + "Got it. I've added this task:\n" +
                tab + "  [" + type + "][ ] " + task + "\n" +
                tab + "Now you have " + size + " tasks in the list.\n";
        printInDivider(divider, toPrint);
    }

    private static void printError(String divider, String tab, String msg) {
        String toPrint = tab + msg + "\n";
        printInDivider(divider, toPrint);
    }

    private static boolean regexChecker(String reg, String inp) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(inp);

        return matcher.find();
    }

    public static void main(String[] args) {
        ArrayList<Task> db = new ArrayList<>();
        String tab = "    ";
        String divider = tab + "-----------------------------------------------\n";
        String greet = tab + "Hello! I'm Blitz.\n" +
                tab + "What can I do for you?\n";
        String end = tab + "Bye. Hope to see you again soon!\n";

        printInDivider(divider, greet);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inp = sc.nextLine();

            if (inp.equals("bye")) {
                break;
            }

            if (inp.equals("list")) {
                System.out.println(divider +
                        tab + "Here are the tasks in your list:");
                for (int i = 0; i < db.size(); i++) {
                    Task curr = db.get(i);
                    System.out.println(tab + (i + 1) + ".[" + curr.getType() + "]" + "[" + (curr.getStatus() ? "X" : " ") + "] " + curr);
                }
                System.out.print(divider);
            } else {
                String[] cont = inp.split(" ", 2);
                String command = cont[0];

                if (cont.length == 1 || cont[1].isBlank()) {
                    printError(divider, tab, "Command without parameter!");
                    continue;
                }

                switch (command) {
                    case "mark", "unmark" -> {
                        String[] param = cont[1].split(" ");
                        if (param.length > 1) {
                            printError(divider, tab, "Only ONE parameter is required!");
                            continue;
                        }

                        try {
                            int ind = Integer.parseInt(param[0]) - 1;

                            if (db.isEmpty()) {
                                printError(divider, tab, "There is nothing in the list!");
                                continue;
                            }

                            Task task = db.get(ind);

                            String toPrint;
                            if (command.equals("mark")) {
                                task.markDone();
                                toPrint = tab + "Nice! I've marked this task as done:\n" +
                                        tab + "  [" + task.getType() + "]" + "[X] " + task + "\n";
                            } else {
                                task.unmarkDone();
                                toPrint = tab + "Ok, I've marked this task as not done yet:\n" +
                                    tab + "  [" + task.getType() + "]" + "[ ] " + task + "\n";
                            }

                            printInDivider(divider, toPrint);
                        } catch (IndexOutOfBoundsException e) {
                            printError(divider, tab, "There is no such task in the list!");
                        } catch (NumberFormatException e) {
                            printError(divider, tab, "Integer value is expected for parameter!");
                        }
                    }
                    case "todo" -> {
                        Task temp = new Todo(cont[1], "T", false);

                        db.add(temp);
                        printTaskAddedWithDivider(divider, tab, "T", db.size(), temp);
                    }
                    case "deadline" -> {
                        if (!regexChecker(".+ /by .+", cont[1])) {
                            printError(divider, tab, "Wrong parameter! Please use this format \"[Task name] /by [Deadline]!\"");
                            continue;
                        }

                        String[] param = cont[1].split(" /by ");

                        if (param[0].contains("/by") || param[1].contains("/by")) {
                            printError(divider, tab, "/by can only use ONCE! Please use this format \"[Task name] /by [Deadline]!\"");
                            continue;
                        }

                        Task temp = new Deadline(param[0], "D", param[1], false);

                        db.add(temp);
                        printTaskAddedWithDivider(divider, tab, "D", db.size(), temp);
                    }
                    case "event" -> {
                        if (!regexChecker(".+ /from .+ /to .+", cont[1])) {
                            printError(divider, tab, "Wrong parameter! Please use this format \"[Task name] /from [Start date/time] /to [End date/time]!\"");
                            continue;
                        }

                        String[] param1 = cont[1].split(" /from ");
                        String[] param2 = param1[1].split(" /to ");

                        if (param1[0].contains("/from") || param1[1].contains("/from")) {
                            printError(divider, tab, "/from can only use ONCE! Please use this format \"[Task name] /from [Start date/time] /to [End date/time]!\"");
                            continue;
                        } else if (param2[0].contains("/to") || param2[1].contains("/to")) {
                            printError(divider, tab, "/to can only use ONCE! Please use this format \"[Task name] /from [Start date/time] /to [End date/time]!\"");
                            continue;
                        }

                        Task temp = new Event(param1[0], "E", param2[0], param2[1], false);

                        db.add(temp);
                        printTaskAddedWithDivider(divider, tab, "E", db.size(), temp);
                    }
                    default -> {
                        String toPrint = tab + "Invalid command\n";

                        printInDivider(divider, toPrint);
                    }
                }
            }
        }

        printInDivider(divider, end);
    }
}
