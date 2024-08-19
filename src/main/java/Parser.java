/**
 * This class parses user input for Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
import java.util.Scanner;
public class Parser {

    public static String getMessage() {
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        return msg;
    }
}
