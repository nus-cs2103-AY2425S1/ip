package Yapper;import java.util.Scanner;public class UI {    private final Scanner sc;    private final Parser parser;    public UI(Parser parser) {        this.sc = new Scanner(System.in);        this.parser = parser;    }    public void readInput() {        while(true) {            if (sc.hasNextLine()) {                String text = sc.nextLine();                this.parser.readCommand(text);            }        }    }}