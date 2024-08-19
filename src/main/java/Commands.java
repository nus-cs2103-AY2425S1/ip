public class Commands {

    public static void botIntro(String botName) {
        System.out.println("------------------------------------------");
        System.out.println("Hello I'm " + botName);
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
    }

    public static void returnTaskList(String[] tasks, int tasksIndex) {
        System.out.println("------------------------------------------");
        for(int i =0; i < tasksIndex; i++) {
            int taskListCount = i + 1;
            System.out.print(taskListCount + ". " + tasks[i] + "\n");
        }
        System.out.println("------------------------------------------");
    }

    public static void addTask(String[] tasksArr, int currIndex, String inputString) {
        System.out.println("------------------------------------------");
        tasksArr[currIndex] = inputString;
        System.out.println( "added: " + inputString);
        System.out.println("------------------------------------------");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }


}