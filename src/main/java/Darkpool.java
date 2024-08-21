import java.util.ArrayList;
import java.util.Scanner;

public class Darkpool {
    public static void main(String[] args) {
        final String greeting = "it’s darkpool. what twisted reason dragged me into your misery?";
        final String bye = "contact me again and you'll regret it.";
        ArrayList<String> todoList = new ArrayList<String>();
        output(greeting);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "bye" -> {
                    output(bye);
                    scanner.close();
                    return;
                }
                case "list" -> {
                    StringBuilder temp = new StringBuilder();
                    for (int i = 0; i < todoList.size(); i++) {
                        temp.append((i + 1)).append(". ").append(todoList.get(i)+"\n\t");
                    }
                    temp.setLength(temp.length() - 2);
                    output(String.valueOf(temp));
                    break;
                }

                default -> {
                    todoList.add(userInput);
                    output("added: " + userInput);
                }
            }
        }

    }

    private static void output(String input) {
        System.out.println("\u001B[31m\t—————————————————————————————————————————————————————————————————\n\t" + input + "\n" + "\t—————————————————————————————————————————————————————————————————\u001B[0m");
    }
}
