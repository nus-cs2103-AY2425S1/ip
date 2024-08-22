public class Tanjiro {
    public static void main(String[] args) {
        Response r = new Response();
        InputProcessor i = new InputProcessor();

        r.greet();

        String user_input = i.read().toLowerCase();
        while (!(user_input.contains("bye"))) {
            if (user_input.equalsIgnoreCase("list")) {
                Task.list_task();
            } else if (user_input.contains("mark")) {
                int list_no  = Character.getNumericValue(user_input.charAt(user_input.length() - 1));
                if (user_input.contains("unmark")) {
                    Task.unmark_task(list_no);
                } else {
                    Task.mark_task(list_no);
                }
            } else {
                Task task = new Task(user_input);
                task.add_task(task);
            }

            user_input = i.read().toLowerCase();
        }

        r.goodbye();

    }

}
