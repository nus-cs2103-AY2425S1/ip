public class Parse {
    private static void parseTask(String input) {
        if(input.startsWith("todo")) {
            String des = input.substring(5);
            TaskList.addTask(new Todo(des));
        }
        if(input.startsWith("deadline")) {
            int byIdx = input.indexOf("/by");
            String des = input.substring(9, byIdx-1);
            String end = input.substring(byIdx + 4);
            TaskList.addTask(new Deadline(des, end));
        }
        if(input.startsWith("event")) {
            int fromIdx = input.indexOf("/from");
            int toIdx = input.indexOf("/to");
            String des = input.substring(6, fromIdx - 1);
            String start = input.substring(fromIdx + 6, toIdx - 1);
            String end = input.substring(toIdx + 4);
            TaskList.addTask(new Event(des, start, end));
        }
    }
    public static void parseInput(String input) {
        if(input.equals("list")) {
            TaskList.listTask();
        }
        else if(input.startsWith("mark")) {
            int idx = Integer.parseInt(input.substring(5));
            TaskList.markTask(idx);
        }
        else if(input.startsWith("unmark")) {
            int idx = Integer.parseInt(input.substring(7));
            TaskList.unmarkTask(idx);
        } else {
            parseTask(input);
        }
    }
}
