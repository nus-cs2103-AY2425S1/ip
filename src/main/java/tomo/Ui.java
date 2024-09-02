package tomo;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
        open();
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void println(Object obj) {
        System.out.println(obj);
    }

    private void open() {
        println("What's up, it's ToMo here!");
    }

    public void help() {
        println("How can I help you?");
    }

    public void close() {
        println("Bye, see you later!");
        scanner.close();
    }
}
