import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class Danny {
    static String cat = """
                  |\\      _,,,---,,_
            ZZZzz /,`.-'`'    -.  ;-;;,_
                 |,4-  ) )-,_. ,\\ (  `'-'
                '---''(_/--'  `-'\\_)
            """;
    static String seperator = "____________________________________________________________";

    public static int handleIntInput(String in, int size) throws Exception{
        int i = -1;
        i = Integer.parseInt(in.split(" ")[1])-1;
        if(i>=size){
            throw new Exception("Out of index");
        }
        return i;
    }

    public static void checkTodo(String in) throws Exception{
        if(in.length()<=4){
            throw new Exception("Please Enter Something for Todo!");
        }
    }
    public static void checkDeadline(String in) throws Exception{
        if(in.split("/by ").length < 2){
            throw new Exception("Wrong Syntax! Use /by for Deadlines!");
        }
    }
    public static void checkEvent(String in) throws Exception{
        String[] eventSplit = in.split("/from ");
        if(eventSplit.length < 2 || eventSplit[1].split("/to ").length<2){
            throw new Exception("Wrong Syntax! Use /from and /to for Events!");
        }

    }

    public static void main(String[] args) {
        System.out.println(seperator);
        System.out.println("Hello! I'm Danny");
        System.out.println("What can I do for you?");
        System.out.println(seperator);
        Scanner input = new Scanner(System.in);
        List<Task> arr = new ArrayList<Task>();
        String in = input.nextLine();
        String text;

        while(!Objects.equals(in, "bye")){
            System.out.println(seperator);
            switch (in.split(" ")[0]){
                case "list":
                    System.out.println("These are all your unfinished tasks:");
                    for (int i = 0; i < arr.size(); i++) {
                        text = (i+1) + "." + arr.get(i).toString();
                        System.out.println(text);
                    }
                    break;
                case "todo":
                    try{
                        checkTodo(in);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    String description = in.substring(4);
                    Task add = new ToDos(description);
                    arr.add(add);
                    System.out.println("added: " + add.toString());
                    break;
                case "deadline":
                    try{
                        checkDeadline(in);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    String[] deadlineSplit = in.split("/by ");
                    description = deadlineSplit[0].substring(8);
                    String by = deadlineSplit[1];
                    add = new Deadlines(description,by);
                    arr.add(add);
                    System.out.println("added: " + add.toString());
                    break;
                case "event":
                    try{
                        checkEvent(in);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    String[] eventSplit = in.split("/from ");
                    description = eventSplit[0].substring(5);
                    String from = eventSplit[1].split(" /to ")[0];
                    by = eventSplit[1].split("/to ")[1];
                    add = new Events(description,by,from);
                    arr.add(add);
                    System.out.println("added: " + add.toString());
                    break;
                case "mark":
                    int i = 0;
                    try{
                        i = handleIntInput(in,arr.size());
                    }
                    catch (Exception e) {
                        System.out.println(cat);
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("I have marked this task as done");
                    arr.get(i).setDone();
                    text = (i+1) + "." + arr.get(i).toString();
                    System.out.println(text);
                    break;
                case "unmark":
                    try{
                        i = handleIntInput(in,arr.size());
                    }
                    catch (Exception e) {
                        System.out.println(cat);
                        System.out.println(e.getMessage());
                        break;
                    }
                    arr.get(i).setUnDone();
                    System.out.println("I have marked this task as undone");
                    text = (i+1) + "." + arr.get(i).toString();
                    System.out.println(text);
                    break;
                case "delete":
                    try{
                        i = handleIntInput(in,arr.size());
                    }
                    catch (Exception e) {
                        System.out.println(cat);
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("I have removed this task from the list");
                    text = (i+1) + "." + arr.get(i).toString();
                    System.out.println(text);
                    arr.remove(i);
                    break;

                default:
                    System.out.println("Unknown Command Detected!");
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
