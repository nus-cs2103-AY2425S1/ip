import java.util.Scanner;

public class MGTOW {
    public static void printMsg(String str){
        System.out.println("\t" + str);
    }
    public static void main(String[] args) {
        String logo = "";
        String line = "____________________________________________";
        Scanner sc = new Scanner(System.in);

        printMsg(line);
        printMsg("Hello! I'm MGTOW");
        printMsg("What can I do for you?");
        printMsg(line);

        Boolean finished = false;

        while (!finished){
            String input = sc.nextLine();
            printMsg(line);

            if (input.equals("bye")){
                finished = true;
            } else {
                printMsg(input);
                printMsg(line);
            }
        }

        printMsg("OK bye time to MGTOW");
        printMsg(line);
    }
}
