package Yapper;import java.util.Scanner;public class UI {    private final Parser parser;    public UI(Parser parser) {        this.parser = parser;    }    public void readInput() {        while (true) {            Scanner sc = new Scanner(System.in);            String text = sc.nextLine();            parser.readCommand(text);        }    }}