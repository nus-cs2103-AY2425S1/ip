import java.util.ArrayList;
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
    private String DELETE = "delete";
    private ArrayList<Task> tasks = new ArrayList<>();
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
                tasks.get(index-1).markAsDone();
                System.out.println("Nicely done! This task is marked as done!");
                System.out.println(tasks.get(index-1).toString());
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
                tasks.get(index-1).markAsNotDone();
                System.out.println("Holdup this task is not done!");
                System.out.println(tasks.get(index-1).toString());
                System.out.println("____________________________");
            } else if (input.startsWith(DELETE)){
                Matcher match = regexHandler(input,"delete (\\d+)$");
                if (match == null){
                    throw new MentosException("Invalid Delete input!");
                }
                String extracted = match.group(1);
                int index = Integer.parseInt(extracted);
                if (index > noTasks || index ==0){
                    throw new MentosException("No Such Tasks!");
                }
                System.out.println("____________________________");
                System.out.printf("Alrights I have removed the following task!\n%s%n",tasks.get(index-1).toString());
                tasks.remove(index-1);
                noTasks--;
                System.out.printf("%d remaining tasks%n",noTasks);
            }
            else if (input.startsWith(TODO)) {
                Matcher match = regexHandler(input, "todo (.+)");
                if (match == null) {
                    throw new MentosException("Todo cannot be empty!");
                }
                String extracted = match.group(1);
                noTasks++;
                Task newTodo = new ToDo(extracted);
                tasks.add(newTodo);
                print_event(TODO,newTodo);
            } else if (input.startsWith(DEADLINE)){
                Matcher match = regexHandler(input,"deadline (.+) \\/by (.+)$");
                if (match == null){
                    throw new MentosException("Invalid deadline input! usage:deadline <desc> /by <datetime>");
                }
                noTasks++;
                String deadline_desc = match.group(1);
                String by = match.group(2);
                Task newDeadline = new Deadline(deadline_desc,by);
                tasks.add(newDeadline);
                print_event(DEADLINE,newDeadline);
            } else if (input.startsWith(EVENT)){
                Matcher match = regexHandler(input,"event (.+) \\/from (.+) \\/to (.+)$");
                if (match == null){
                    throw new MentosException("Invalid Event input! usage:event <desc> /from <datetime> /to <datetime>" );
                }
                noTasks++;
                String eventDesc = match.group(1);
                String from = match.group(2);
                String to = match.group(3);
                Task newEvent = new Event(eventDesc,from,to);
                tasks.add(newEvent);
                print_event(EVENT,newEvent);
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
            String task_out = String.format("%d. %s",i+1,tasks.get(i).toString());
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

