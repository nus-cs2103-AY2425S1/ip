package Darkpool.util;

import java.util.Scanner;

public class Ui {

    final String greeting = "\tit’s darkpool. what twisted reason dragged me into your misery?";
    final String bye = "\tcontact me again and you'll regret it.";

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void greeting() {
        upperLine();
        output(greeting);
        lowerLine();
    }

    public void goodbye() {
        upperLine();
        output(bye);
        lowerLine();
    }

    public void list(TaskList taskList) {
        output(taskList.toString());
    }

    public void mark(String task) {
        output("\twhy do i have to mark this mess\n\t\t" + task);
    }

    public void unmark(String task) {
        output("\twhy do i have to unmark this mess\n\t\t" + task);
    }

    public void delete(String task) {
        output("\twhy do i have to delete this mess\n\t\t" + task);
    }

    public void add(String task, int size) {
        output("\ti have dumped this nonsense on the list\n\t\t" + task + "\n\tnow you are stuck with " + size + " goddamn tasks");
    }

    protected void output(String input) {
        System.out.println("\u001B[31m" + input + "\u001B[0m");
    }

    public void showError(String input) {
        System.out.println("\t\u001B[36m" + input + "\u001B[0m");
    }

    public void upperLine() {
        System.out.println("\u001B[34m\t—————————————————————————————————————————————————————————————————\t\u001B[0m");
    }

    public void lowerLine() {
        System.out.println("\u001B[34m\t—————————————————————————————————————————————————————————————————\u001B[0m");
    }
}
