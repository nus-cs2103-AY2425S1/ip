package Talky;

import java.util.Scanner;

public class Ui {
    Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public String getCommand() {
        return this.sc.nextLine();
    }
    public void printSeperator(String content) {
        String SEPERATOR = "---------------------------------------";
        System.out.println(SEPERATOR);
        System.out.println(content);
        System.out.println(SEPERATOR);
    }
}
