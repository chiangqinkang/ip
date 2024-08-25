package Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void readTaskTest() {
        ToDo task = ToDo.of("test", Task.TaskType.T);
        assertEquals("test", task.readTask());
    }

    @Test
    public void getStatusTest() {
        ToDo task = ToDo.of("test", Task.TaskType.T);
        assertEquals(" ", task.getStatus());
    }
}