import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
//merging

public class Duck {
    public static void main(String[] args) throws DuckException {
        Scanner scan = new Scanner(System.in);
        TaskList cmds = new TaskList(Storage.load(), Storage.loadNum());

        System.out.println("Hello! I'm DUCK\n What can I do for you?");
        boolean cont = true;
        while (cont) {
            System.out.println(cmds.cmdNum);
            String userCmd = scan.nextLine();
            cont = Parser.parseCmd(cmds, userCmd);
        }
        scan.close();
    }

}

