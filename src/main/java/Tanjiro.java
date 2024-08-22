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
                int list_no = Character.getNumericValue(user_input.charAt(user_input.length() - 1));
                if (user_input.contains("unmark")) {
                    Task.unmark_task(list_no);
                } else {
                    Task.mark_task(list_no);
                }
            } else if (user_input.contains("todo")) {
                String name = user_input.substring(5);
                ToDos temp_todo = new ToDos(name);
                temp_todo.add_task(temp_todo);
            } else if (user_input.contains("deadline")) {
                String[] deadline_arr = user_input.split("/");
                String name = deadline_arr[0].substring(9);
                String day = deadline_arr[1].substring(3);
                Deadlines temp_deadline = new Deadlines(name, day);
                temp_deadline.add_task(temp_deadline);
            } else if (user_input.contains("event")) {
                String[] deadline_arr = user_input.split("/");
                String name = deadline_arr[0].substring(6);
                String start = deadline_arr[1].substring(5);
                String end = deadline_arr[2].substring(3);
                Events temp_event = new Events(name, start, end);
                temp_event.add_task(temp_event);
            }
//            else {
//                Task temp_task = new Task(user_input);
//                temp_task.add_task(temp_task);
//            }

            user_input = i.read().toLowerCase();
        }

        r.goodbye();

    }

}
