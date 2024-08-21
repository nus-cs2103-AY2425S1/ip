import java.util.ArrayList;
import java.util.Scanner;

public class Darkpool {
    public static void main(String[] args) {
        final String greeting = "it’s darkpool. what twisted reason dragged me into your misery?";
        final String bye = "contact me again and you'll regret it.";
        ArrayList<Task> taskList = new ArrayList<>();
        output(greeting);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.next();

            switch (userInput) {
                case "bye" -> {
                    output(bye);
                    scanner.close();
                    return;
                }

                case "mark" -> {
                    int num = Integer.parseInt(scanner.next()) - 1;
                    taskList.get(num).markDone();
                    output("why do i have to wipe this mess\n\t" + taskList.get(num).toString());
                }

                case "unmark" -> {
                    int num = Integer.parseInt(scanner.next()) - 1;
                    taskList.get(num).markUndone();
                    output("perfect! i have undone this crap\n\t" + taskList.get(num).toString());
                }

                case "list" -> {
                    StringBuilder temp = new StringBuilder("why am i here\n\t");
                    for (int i = 0; i < taskList.size(); i++) {
                        temp.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n\t");
                    }
                    temp.setLength(temp.length() - 2);
                    output(String.valueOf(temp));
                }

                case "todo" -> {
                    String desc = scanner.nextLine();
                    taskList.add(new Todo(desc));
                    int size = taskList.size();
                    output("i have dumped this nonsense on the list\n\t\t" + taskList.get(size - 1).toString() + "\n\tnow you are stuck with " + size + " goddamn tasks");
                }

                case "deadline" -> {
                    String[] array = scanner.nextLine().split("/by");
                    String desc = userInput + array[0];
                    String by = array[1];
                    taskList.add(new Deadline(desc, by));
                    int size = taskList.size();
                    output("i have dumped this nonsense on the list\n\t\t" + taskList.get(size - 1).toString() + "\n\tnow you are stuck with " + size + " goddamn tasks");
                }

                case "event" -> {
                    String[] fromParts = scanner.nextLine().split("/from ");
                    String[] toParts = fromParts[1].split("/to ");
                    String desc = fromParts[0];
                    String from = toParts[0];
                    String to = toParts[1];
                    taskList.add(new Event(desc, from, to));
                    int size = taskList.size();
                    output("i have dumped this nonsense on the list\n\t\t" + taskList.get(size - 1).toString() + "\n\tnow you are stuck with " + size + " goddamn tasks");
                }

                default -> output("wtf is this?");
            }
        }
    }

    private static void output(String input) {
        System.out.println("\u001B[31m\t—————————————————————————————————————————————————————————————————\n\t" + input + "\n" + "\t—————————————————————————————————————————————————————————————————\u001B[0m");
    }
}
