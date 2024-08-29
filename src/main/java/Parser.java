import java.util.Scanner;

public class Parser {
    Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    public String[] requestInput() {
        System.out.print("Text: ");
        String input = scanner.nextLine();
        String cmd = input.split(" ", 2)[0];
        String rest = input.split(" ", 2).length > 1 ? input.split(" ", 2)[1] : "";
        String[] retStrArr = new String[]{cmd, rest};
        return retStrArr;
    }
}
