import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }
    public void showList() {
        System.out.println("-----------------------------------------------");
        System.out.println("Displaying ListBot:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, list.get(i));
        }
        System.out.println("-----------------------------------------------");
    }

    public void markTaskByIndex(int index) {
        list.get(index - 1).setIsDone(true);
        System.out.println("-----------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index - 1));
        System.out.println("-----------------------------------------------");
    }

    public void unmarkTaskByIndex(int index) {
        list.get(index - 1).setIsDone(false);
        System.out.println("-----------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index - 1));
        System.out.println("-----------------------------------------------");
    }

    public void deleteTaskByIndex(int index) {
        System.out.println("-----------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(index - 1));
        list.remove(index - 1);
        System.out.printf("Now you have %d tasks in the list\n", list.size());
        System.out.println("-----------------------------------------------");
    }

    public void addDeadlineTask(String deadlineDesc, String due) {
        list.add(new Deadline(deadlineDesc, due));
        System.out.println("-----------------------------------------------");
        System.out.printf("Got it. I've added this task: \n%s\n", list.get(list.size() - 1));
        System.out.printf("Now you have %d tasks in the list\n", list.size());
        System.out.println("-----------------------------------------------");
    }

    public void addEventTask(String eventDesc, String from, String to){
        list.add(new Event(eventDesc, from, to));
        System.out.println("-----------------------------------------------");
        System.out.printf("Got it. I've added this event: \n%s\n", list.get(list.size() - 1));
        System.out.printf("Now you have %d tasks in the list\n", list.size());
        System.out.println("-----------------------------------------------");
    }

    public void addToDoTask(String todoDesc) {
        list.add(new ToDo(todoDesc));
        System.out.println("-----------------------------------------------");
        System.out.printf("Got it. I've added this task: \n%s\n", list.get(list.size() - 1));
        System.out.printf("Now you have %d tasks in the list\n", list.size());
        System.out.println("-----------------------------------------------");
    }

}
