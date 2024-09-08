package echo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import tasks.Deadline;
import tasks.Task;

public class DeadlineTest {
    @Test
    public void test_creat_deadline() {
        try {
            Task deadlineA = new Deadline("dA", "2023/03/23 23:59");
            Task deadlineB = Task.createTask("deadline dA /by 2023/03/23 23:59");
            assertEquals(deadlineA.toString(), deadlineB.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_mark_deadline() {
        try {
            Task deadlineA = new Deadline("dA", "2023/03/23 23:59");
            Task deadlineB = Task.createTask("deadline dA /by 2023/03/23 23:59");
            deadlineA.setMark();
            deadlineB.setMark();
            assertEquals(deadlineA.toString(), deadlineB.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
