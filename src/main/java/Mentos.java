import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mentos
{
    private String MARKED = "mark";
    private String UNMARKED = "unmark";
    private String TODO = "todo";
    private String DEADLINE = "deadline";
    private String EVENT = "event";
    private Task[] tasks = new Task[100];
    private int noTasks = 0;

    public static void main(String[] args) {
        Mentos mentos = new Mentos();
        mentos.startConversation();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String input = scanner.nextLine();
            if (input.equals("bye")){
                break;
            }
            mentos.taskHandler(input);
        }
//        mentos.taskHandler(scanner);
        mentos.endConversation();
    }

    public void startConversation(){
        System.out.println("____________________________");
        System.out.println("Hello! I'm Mentos\nWhat can I do to help you?");
        System.out.println("____________________________");
    }

    public void endConversation(){
        System.out.println("____________________________");
        System.out.println("Pop a Mentos, stay fresh! See you next time!");
        System.out.println("____________________________");
    }

    public Matcher regexHandler(String input,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()){
            return matcher;
        }
        return null;
    }

    public void taskHandler(String input) {
        try {
            if (input.equals("list")) {
                displayTasks();
            } else if (input.startsWith(MARKED)) {
                Matcher match = regexHandler(input, "mark (\\d+)");
                if (match == null) {
                    throw new MentosException("Invalid input!");
                }
                String extracted = match.group(1);
                int index = Integer.parseInt(extracted);
                if (index > noTasks || index == 0) {
                    throw new MentosException("No Such Tasks!");
                }
                tasks[index - 1].markAsDone();
                System.out.println("Nicely done! This task is marked as done!");
                System.out.println(tasks[index - 1].toString());
                System.out.println("____________________________");

            } else if (input.startsWith(UNMARKED)) {
                Matcher match = regexHandler(input, "unmark (\\d+)");
                if (match == null) {
                    throw new MentosException("Invalid input!");
                }
                String extracted = match.group(1);
                int index = Integer.parseInt(extracted);
                if (index > noTasks || index == 0) {
                    throw new MentosException("No Such Tasks!");
                }
                tasks[index - 1].markAsNotDone();
                System.out.println("Holdup this task is not done!");
                System.out.println(tasks[index - 1].toString());
                System.out.println("____________________________");
            } else if (input.startsWith(TODO)) {
                Matcher match = regexHandler(input, "todo (.+)");
                if (match == null) {
                    throw new MentosException("Todo cannot be empty!");
                }
                String extracted = match.group(1);
                noTasks++;
                tasks[noTasks-1] = new ToDo(extracted);
                print_event(TODO,tasks[noTasks-1]);
            } else if (input.startsWith(DEADLINE)){
                Matcher match = regexHandler(input,"deadline (.+) \\/by (.+)$");
                if (match == null){
                    throw new MentosException("Invalid deadline input! usage:deadline <desc> /by <datetime>");
                }
                noTasks++;
                String deadline_desc = match.group(1);
                String by = match.group(2);
                tasks[noTasks-1] = new Deadline(deadline_desc,by);
                print_event(DEADLINE,tasks[noTasks-1]);
            } else if (input.startsWith(EVENT)){
                Matcher match = regexHandler(input,"event (.+) \\/from (.+) \\/to (.+)$");
                if (match == null){
                    throw new MentosException("Invalid Event input! usage:event <desc> /from <datetime> /to <datetime>" );
                }
                noTasks++;
                String eventDesc = match.group(1);
                String from = match.group(2);
                String to = match.group(3);
                tasks[noTasks-1] = new Event(eventDesc,from,to);
                print_event(EVENT,tasks[noTasks-1]);
            }

            else {
                System.out.println("____________________________");
                System.out.println("Sorry me no understand");
                System.out.println("____________________________");
            }
        } catch (MentosException err){
            System.out.println(err);
        }
    }

    public void displayTasks(){
        for (int i = 0; i < noTasks; i++) {
            String task_out = String.format("%d. %s",i+1,tasks[i].toString());
            System.out.println(task_out);
        }
        System.out.println("____________________________");
    }

    public void print_event(String event, Task task){
        System.out.println("____________________________");
        System.out.printf(event+" Added\n%s\n%d remaining tasks%n",task.toString(),noTasks);
        System.out.println("____________________________");
    }

}

