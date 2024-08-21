import java.util.ArrayList;
import java.util.Scanner;

public class Darkpool {
    public static void main(String[] args) {
        final String greeting = "it’s darkpool. what twisted reason dragged me into your misery?";
        final String bye = "contact me again and you'll regret it.";
        ArrayList<Task> todoList = new ArrayList<>();
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
                    todoList.get(num).markDone();
                    output("why do i have to wipe this mess\n\t" + todoList.get(num).toString());
                }

                case "unmark" -> {
                    int num = Integer.parseInt(scanner.next()) - 1;
                    todoList.get(num).markUndone();
                    output("perfect! i have undone this crap\n\t" + todoList.get(num).toString());
                }

                case "list" -> {
                    StringBuilder temp = new StringBuilder("why am i stuck here\n\t");
                    for (int i = 0; i < todoList.size(); i++) {
                        temp.append((i + 1)).append(". ").append(todoList.get(i).toString()).append("\n\t");
                    }
                    temp.setLength(temp.length() - 2);
                    output(String.valueOf(temp));
                }

                default -> {
                    String desc = userInput + scanner.nextLine();
                    todoList.add(new Task(desc));
                    output("added: " + desc);
                }
            }
        }
    }

    private static void output(String input) {
        System.out.println("\u001B[31m\t—————————————————————————————————————————————————————————————————\n\t" + input + "\n" + "\t—————————————————————————————————————————————————————————————————\u001B[0m");
    }
}
