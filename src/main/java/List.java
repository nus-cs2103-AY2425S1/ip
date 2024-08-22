public class List {

    private Task[] list;
    private int itemCount;
    public List() {
        this.list = new Task[100];
        this.itemCount = 0;
    }

    public void answer(String response) {
        String command = response.contains(" ")
                ? response.substring(0, response.indexOf(' '))
                : response;
        String name;
        String startTime;
        String endTime;
        int index;
        switch (command) {
            case "todo":
                name = response.substring(response.indexOf(' ') + 1);
                this.add(new Todo(name));
                break;
            case "deadline":
                name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                endTime = response.substring(response.indexOf("/by") + 4);
                this.add(new Deadline(name, endTime));
                break;
            case "event":
                name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                startTime = response.substring(response.indexOf("/from") + 6, response.indexOf("/to") - 1);
                endTime = response.substring(response.indexOf("/to") + 4);
                this.add(new Event(name, startTime, endTime));
                break;
            case "mark":
                index = Integer.parseInt(response.substring(response.indexOf(' ') + 1, response.indexOf(' ') + 2)) - 1;
                this.markTask(index);
                break;
            case "unmark":
                index = Integer.parseInt(response.substring(response.indexOf(' ') + 1, response.indexOf(' ') + 2)) - 1;
                this.unmarkTask(index);
                break;
            case "list":
                this.listOut();
                break;
            default:
                System.out.println("say smth");
        }
    }

    public void add(Task task) {
        this.list[itemCount] = task;
        this.itemCount++;
        System.out.println("I've added the task: ");
        System.out.println(task);
        System.out.println("Now you have " + this.itemCount + " tasks in the list");
    }

    public void listOut() {
        for (int i = 1; i <= itemCount; i++) {
            System.out.println(i + "." + this.list[i - 1].toString());
        }
    }

    public void printTask(int index) {
        System.out.println(this.list[index].toString());
    }

    public void markTask(int index) {
        this.list[index].mark();
        System.out.println("You have marked the following task as done!");
        System.out.println(this.list[index]);
    }

    public void unmarkTask(int index) {
        this.list[index].unmark();
        System.out.println("You have unmarked the following task!");
        System.out.println(this.list[index]);
    }
}
