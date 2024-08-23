import java.util.Scanner;
import java.util.ArrayList;

public class Rizz {
    private Scanner input = new Scanner(System.in);
    private ArrayList<String> arrList = new ArrayList<>();


    private String indent(String str) {
        String[] lines = str.split("\n");
        StringBuilder indentedText = new StringBuilder();
        for (String line : lines) {
            indentedText.append("\t").append(line).append("\n");
        }
        return indentedText.toString();
    }

    private void greet() {
        String str = "____________________________________________________________\n" +
                "Hello! I'm Rizz\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(this.indent(str));
    }

    private void echo(String textInput) {
        arrList.add(textInput);
        System.out.println("\tadded: " + textInput + "\n");
    }

    private void exit() {
        String str =  "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(this.indent(str));
    }

    private void outputList() {
        if (arrList.isEmpty()) {
            System.out.println("\tNo items in the list.\n");
        } else {
            System.out.println("\t____________________________________________________________");
            for (int i = 0; i < arrList.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + arrList.get(i));
            }
            System.out.println("\t____________________________________________________________\n");
        }
    }

    private void caseCheck() {
        while (true) {
            String textInput = this.input.nextLine();
            if (textInput.equals("bye")) {
                this.exit();
                break;
            } else if (textInput.equals("list")) {
                this.outputList();
            } else {
                this.echo(textInput);
            }
        }
    }

    public static void main(String[] args) {
        Rizz rizz = new Rizz();
        rizz.greet();
        rizz.caseCheck();
    }
}
