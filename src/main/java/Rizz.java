import java.util.Scanner;
public class Rizz {
    Scanner input = new Scanner(System.in);

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
        System.out.println("\t" + textInput + "\n");
    }

    private void exit() {
        String str =  "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(this.indent(str));
    }

    private void caseCheck() {
        String textInput = this.input.nextLine();
        if (textInput.equals("bye")) {
            this.exit();
        } else {
            this.echo(textInput);
            this.caseCheck();
        }
    }

    public static void main(String[] args) {
        Rizz rizz = new Rizz();
        rizz.greet();
        rizz.caseCheck();
    }
}
