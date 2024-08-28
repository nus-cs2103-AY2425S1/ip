import java.io.IOException;
import java.time.LocalDateTime;

public class Tanjiro {
    public static void main(String[] args) throws IOException {
        Response r = new Response();
        InputProcessor i = new InputProcessor();
        FileSystem fs = new FileSystem();
        r.greet();
        fs.createFile();
        Task.init_list();
        DateTimeSystem ds = new DateTimeSystem();

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
                try {
                    String name = user_input.substring(5);
                    ToDos temp_todo = new ToDos(name);
                    temp_todo.add_task(temp_todo);
                } catch (StringIndexOutOfBoundsException e) {
                    r.empty_todo();
                }
            } else if (user_input.contains("deadline")) {
                try {
                    String[] deadline_arr = user_input.split("/");
                    String name = deadline_arr[0].substring(9);
                    String date = deadline_arr[1].substring(3);

                    String[] tokens = date.split(" ");
                    String[] dateTokens = tokens[0].split("-");
                    String time = tokens[1];
                    String year = dateTokens[0];
                    String month = dateTokens[1];
                    String day = dateTokens[2];
                    LocalDateTime ldt = ds.createDate(year,month,day,time.substring(0,2),time.substring(2));

                    Deadlines temp_deadline = new Deadlines(name, ldt);
                    temp_deadline.add_task(temp_deadline);
                } catch (StringIndexOutOfBoundsException e) {
                    r.empty_deadline();
                }
            } else if (user_input.contains("event")) {
                try {
                    String[] deadline_arr = user_input.split("/");
                    String name = deadline_arr[0].substring(6);
                    String start = deadline_arr[1].substring(5);
                    String end = deadline_arr[2].substring(3);

                    String[] full_date_token_start = start.split(" ");
                    String[] date_token_start = full_date_token_start[0].split("-");

                    System.out.println("Start Date: " + date_token_start[0] + " / " + date_token_start[1] + " / " + date_token_start[2]);

                    LocalDateTime ldt_start = ds.createDate(date_token_start[0],date_token_start[1],date_token_start[2],full_date_token_start[1].substring(0,2),full_date_token_start[1].substring(2));

                    String[] full_date_token_end = end.split(" ");
                    String[] date_token_end = full_date_token_end[0].split("-");

                    LocalDateTime ldt_end = ds.createDate(date_token_end[0],date_token_end[1],date_token_end[2],full_date_token_end[1].substring(0,2),full_date_token_end[1].substring(2));

                    Events temp_event = new Events(name, ldt_start, ldt_end);
                    temp_event.add_task(temp_event);
                } catch (StringIndexOutOfBoundsException e) {
                    r.empty_event();
                }
            } else if (user_input.contains("delete")) {
                int list_no = Character.getNumericValue(user_input.charAt(user_input.length() - 1));
                Task.delete_task(list_no);

            } else {
                r.invalid_input();
            }

            user_input = i.read().toLowerCase();
        }

        r.goodbye();

    }

}
