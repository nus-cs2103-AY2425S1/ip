import java.util.Scanner;

public class Mahesh {

    private final static String PATH = "../../../data/mahesh.txt";

    /**
     * List to store tasks.
     */
    private static TaskList list;

    public static void main(String[] args) {
        Storage store = new Storage(Mahesh.PATH);
        Mahesh.list = store.retrieveData();
        Ui.printStartupMessage();

        
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            String originalInput = scan.nextLine();
            Parser parserObj = new Parser(list, store);
            try {
                Command command = parserObj.parse(originalInput);
                if (command != null) {
                    command.execute();
                    exit = command.isExit();
                }
            } catch (MaheshException err) {
                System.out.println(err.getMessage());
            }
            store.updateData(Mahesh.list);
        }

        scan.close();
    }
}
