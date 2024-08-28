import java.util.Scanner;

public class Parser {
    private Scanner sc;

    public Parser() {
        this.sc = new Scanner(System.in);
    }

    public String[] parseLine() throws YapperException {
        String input = "";
        if (this.sc.hasNextLine()) {
            input = this.sc.nextLine();
        } else {
            throw new YapperException("Invalid input");
        }
        return input.split("\\s+");
    }
}
