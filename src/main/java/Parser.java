import java.util.Scanner;

public class Parser {
    Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    public String retrieveNextString() {
        return scanner.next();
    }

    public int retrieveNextInt() {
        return scanner.nextInt();
    }

    public void close() {
        scanner.close();
    }

    public String scanUntil(String pattern) {
        String item = "";
        String temp = scanner.next();
        while (!temp.equals(pattern)) {
            item += temp + " ";
            temp = scanner.next();
        }
        return item;
    }
}
