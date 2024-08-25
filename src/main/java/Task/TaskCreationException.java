package Task;

public class TaskCreationException extends Exception {
    public TaskCreationException() {
        super("There was an error while creating the task");
    }

    public TaskCreationException(String message) {
        super(message);
    }
}
