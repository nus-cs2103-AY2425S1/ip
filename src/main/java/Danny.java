import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class Danny {
    public static void main(String[] args) {
        String cat = """
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⠟⠋⠀⠀⠹⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡾⠟⠁⠀⠀⠀⠀⠀⢻⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣶⡿⠛
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⠋⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⡿⠋⠁⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣶⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣿⣿⠿⠿⣶⣶⣦⣤⣴⣿⠟⠁⠀⠀⠀⢀⡼
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡟⠿⠁⠀⠀⠀⠀⠀⠀⠀⠀⢠⠞⠁⡴⠋⠀⠀⢀⡴⠋⢈⡿⠛⠁⠀⠀⠀⠀⢀⠎⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⡧⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠋⠀⡜⠀⠀⠀⣠⠋⠀⢠⠞⠀⠀⠀⠀⠀⠀⠀⠼⠤⡄
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣄⡼⠁⠀⠀⢰⠃⠀⣠⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠔⠁
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣶⢿⣿⠟⠀⠀⠀⠀⠀⣠⣤⣄⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠘⠷⠴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢦
                ⠀⠀⠀⠀⠀⣰⡟⠛⢿⣶⣤⣼⡇⠀⠈⠀⠀⠀⠀⢠⣾⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠀⠀⠀
                ⠀⢀⣴⡶⠿⠿⠀⠀⠘⠿⣿⣿⣷⣦⡀⠀⠀⠀⠀⣿⣿⣿⣿⣿⠇⠀⣀⡤⠤⠤⠤⠤⣀⡀⠀⠀⠀⠀⢀⣶⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀
                ⣴⡿⠋⠀⠀⠀⠀⠀⠀⠀⠙⢦⣉⠻⢿⣷⣦⣀⠀⠘⠻⠿⠟⠋⡠⢾⠁⠀⠀⠀⠀⠀⠀⠈⠐⠄⠀⠀⢸⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀
                ⣿⡇⠀⢀⡾⢻⣿⣷⡀⠀⠀⢰⠌⣑⠦⣌⠛⠿⣷⣦⣀⠀⠀⢰⠁⠘⣄⠀⢀⠶⡀⠀⠀⢀⡄⠈⡆⠀⠘⢿⣿⣿⣿⣿⡿⠃⠀⠀⠀⠀
                ⣿⠃⠀⢸⣇⣿⣿⣿⡇⠀⠀⢸⠀⠈⠙⠺⢽⣦⣌⡙⠻⣷⣦⣄⠀⠀⠀⠉⠀⠀⠙⠒⠒⠉⠀⠀⢱⠀⠀⠀⠙⠛⠛⠉⠀⠀⠀⠀⠀⠀
                ⣿⠀⠀⠀⠙⠿⠿⠋⠀⠀⠀⢸⠀⠀⠀⠀⠀⠈⠙⠂⠀⠀⣿⠻⣿⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⢠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⣿⡄⠀⠀⠀⢀⣄⠀⢀⡤⠒⠺⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⢹⠉⡿⢿⣦⣤⣶⣶⣦⣤⡐⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⣿⡇⠀⣦⡀⠘⠛⢀⡼⠀⠀⢠⠀⠈⡗⣦⣄⡀⠀⠀⠀⠀⣿⠀⢸⠀⡇⢸⡏⢹⠿⣄⡉⠛⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⣿⣧⣀⠀⠉⠓⠚⠉⠀⣠⣤⣤⣀⠀⡇⣇⢸⠉⡗⠦⢄⣀⢻⣦⣼⡀⡇⢸⠀⢸⠀⣿⢉⠒⠾⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⢻⣿⣌⡙⠲⢤⣴⠶⠚⠧⣌⡛⠿⣿⣷⣷⣸⠀⡇⠀⠀⠈⠙⠺⣝⡻⢿⣾⣦⣸⠀⣿⠀⠉⠀⠸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠈⠙⠻⢿⣶⣬⣙⣶⣤⣈⠙⠳⢤⣉⡛⠿⣷⣷⣄⡀⠀⠀⠀⠀⠈⠙⠻⢿⣿⣷⣿⠀⠀⠀⠀⢿⡇⠀⠀⠀⠀⠠⡀⠀⠀⠀⢀⣀⣤
                ⠀⠀⠀⠀⠀⠈⠙⠻⢿⣿⣿⣧⣄⣀⡈⠙⠳⢦⣍⠛⢿⣷⣦⣄⠀⠀⠀⠀⠀⠈⠙⠻⢤⡀⠀⠀⢸⡇⠐⢦⡀⠀⢀⡌⠲⣄⣠⡼⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢹⣿⠟⠛⠛⠛⠷⢶⣌⣙⡶⠬⠷⠞⠛⠳⠶⠶⣦⣤⣀⠀⠀⠉⠓⣶⣾⣿⠀⠀⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡇⠀⠀⠀⠀⠀⠀⣿⠋⠀⠀⠀⣠⠔⠁⠀⠀⠀⠉⠻⢷⣦⡀⢀⣿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⢀⠜⠁⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣾⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⠿⠀⠀⠀⠀⠀⠀⠀⠻⠦⠠⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠮⠤⠈⠻⠷⠤⠤⠤⠤⠄⠀⠀⠀⠀⠀⠀⠀⠀
                """;
        String seperator = "____________________________________________________________";
        System.out.println(seperator);
        System.out.println("Hello! I'm Danny");
        System.out.println("What can I do for you?");
        System.out.println(seperator);
        Scanner input = new Scanner(System.in);
        List<String> arr = new ArrayList<String>();
        List<Boolean> tickArr = new ArrayList<Boolean>();
        String in = input.nextLine();
        String text;
        while(!Objects.equals(in, "bye")){
            System.out.println(seperator);
            switch (in.split(" ")[0]){
                case "list":
                    System.out.println("These are all your unfinished tasks:");
                    for (int i = 0; i < arr.size(); i++) {
                        text = (i+1) + ". ";
                        if(tickArr.get(i)){
                            text += "[X] ";
                        }
                        else{
                            text += "[ ] ";
                        }
                        text += arr.get(i);
                        System.out.println(text);
                    }
                    break;
                case "add":
                    arr.add(in);
                    tickArr.add(false);
                    System.out.println("added: " + in);
                    break;
                case "mark":
                    int i = 0;
                    try{
                        i = Integer.parseInt(in.split(" ")[1])-1;
                        if(i>=arr.size()){
                            throw new NumberFormatException("Out of index");
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println(cat);
                        System.out.println(e.getMessage());
                        break;
                    }
                    tickArr.set(i, true);
                    System.out.println("I have marked this task as done");
                    text = (i+1) + ". [X] " + arr.get(i);
                    System.out.println(text);
                    break;
                case "unmark":
                    i = 0;
                    try{
                        i = Integer.parseInt(in.split(" ")[1])-1;
                        if(i>=arr.size()){
                            throw new NumberFormatException("Out of index");
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println(cat);
                        System.out.println(e.getMessage());
                        break;
                    }
                    tickArr.set(i, false);
                    System.out.println("I have marked this task as undone");
                    text = (i+1) + ". [ ] " + arr.get(i);
                    System.out.println(text);
                    break;
                default:
                    System.out.println(in);
                    break;
            }
            System.out.println(seperator);
            in = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(seperator);
        return;
    }

}
