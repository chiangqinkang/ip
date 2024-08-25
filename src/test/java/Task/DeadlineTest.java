import org.junit.jupiter.api.Test;
import Task.TaskCreationException;
import Task.Deadline;
import Task.Task.TaskType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void readTaskTest() {
        try {
            String expectedFormattedDate = "Feb 12 2019, 6:00 PM";
            Deadline task = Deadline.of("return book /by Feb 12 2019 6:00 PM", TaskType.D);
            assertEquals(expectedFormattedDate, task.getBy());
        } catch (TaskCreationException e) {
            fail("TaskCreationException was thrown during the test.", e);
        }
    }

    @Test
    public void getStatusTest() {
        try {
            Deadline task = Deadline.of("return book /by Feb 12 2019 6:00 PM", TaskType.D);
            assertEquals("return book (by: Feb 12 2019 6:00 PM)", task.readTask());
        } catch (TaskCreationException e) {
            fail("TaskCreationException was thrown during the test.", e);
        }
    }
}