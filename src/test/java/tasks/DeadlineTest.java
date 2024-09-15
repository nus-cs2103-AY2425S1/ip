package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.EchoException;

public class DeadlineTest {
    @Test
    public void test_creat_deadline() {
        try {
            Task expectedDeadline = new Deadline("dA", "2023/03/23 23:59");
            Task actualDeadline = Task.createTask("deadline dA /by 2023/03/23 23:59");
            assertEquals(expectedDeadline.toString(), actualDeadline.toString());
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_mark_deadline() {
        try {
            Task expectedDeadline = new Deadline("dA", "2023/03/23 23:59");
            Task actualDeadline = Task.createTask("deadline dA /by 2023/03/23 23:59");
            expectedDeadline.setMark();
            actualDeadline.setMark();
            assertEquals(expectedDeadline.toString(), actualDeadline.toString());
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }
    }
}
