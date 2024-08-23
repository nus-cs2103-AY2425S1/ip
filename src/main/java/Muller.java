import java.util.ArrayList;
import java.util.Scanner;

public class Muller {
    public static void main(String[] args) {
        String logo = "____________________________________________________________";
        System.out.println(logo + "\nHello! I'm Muller\nWhat can I do for you?\n"
                                + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while(true){
            String in = sc.nextLine();
            if(in.equals("bye")){
                break;
            } else if (in.equals("list")) {
                System.out.println(logo);
                for(int i = 1; i <= list.size(); i++){
                    System.out.println(i + ": " + list.get(i-1));
                }
                System.out.println(logo);
            } else {
                System.out.println(logo + "\n" + "added: " + in+ "\n" + logo);
                list.add(in);
            }
        }

        System.out.println(logo + "\nBye. Hope to see you again soon!\n" + logo);
    }
}
