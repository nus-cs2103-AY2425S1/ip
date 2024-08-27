import java.util.Scanner;
public class UserInteraction {




    public void interact() {
        Scanner scannerObject = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Parser parser = new Parser();



        while (true) {
            String instruction = scannerObject.nextLine();
            System.out.println(instruction);
            System.out.println(Meeju.LINE_BREAK);
            int result = parser.parse(taskList, instruction);
            if (result == -1) {
                break;
            }
            System.out.println(Meeju.LINE_BREAK);
        }
    }
}
