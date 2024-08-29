import java.util.Scanner;

public class Ui {

    final String greeting = "\tit’s darkpool. what twisted reason dragged me into your misery?";
    final String bye = "\tcontact me again and you'll regret it.";

    protected String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    protected void greeting() {
        upperLine();
        output(greeting);
        lowerLine();
    }

    protected void goodbye() {
        upperLine();
        output(bye);
        lowerLine();
    }

    protected void list(TaskList taskList) {
        output(taskList.toString());
    }

    protected void mark(String task) {
        output("\twhy do i have to mark this mess\n\t\t" + task);
    }

    protected void unmark(String task) {
        output("\twhy do i have to unmark this mess\n\t\t" + task);
    }

    protected void delete(String task) {
        output("\twhy do i have to delete this mess\n\t\t" + task);
    }

    protected void add(String task, int size) {
        output("\ti have dumped this nonsense on the list\n\t\t" + task + "\n\tnow you are stuck with " + size + " goddamn tasks");
    }

    protected void output(String input) {
        System.out.println("\u001B[31m" + input + "\u001B[0m");
    }

    protected void showError(String input) {
        System.out.println("\t\u001B[36m" + input + "\u001B[0m");
    }

    protected void upperLine() {
        System.out.println("\u001B[34m\t—————————————————————————————————————————————————————————————————\t\u001B[0m");
    }

    protected void lowerLine() {
        System.out.println("\u001B[34m\t—————————————————————————————————————————————————————————————————\u001B[0m");
    }
}
