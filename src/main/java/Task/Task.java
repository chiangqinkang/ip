package Task;

public class Task {
    public enum TaskType {
        T,
        D,
        E,
    }
    private String taskName;
    private boolean completed;
    private TaskType taskType;
    public Task(String name, TaskType taskType) {
        this.taskName = name;
        this.completed = false;
        this.taskType = taskType;

    }

    public void markAsDone() {
        this.completed = true;
    }

    public void markAsUndone() {
        this.completed = false;
    }

    public static Task of(String name, TaskType taskType) throws TaskCreationException {
        return new Task(name, taskType);
    }

    public String readTask() {
        return this.taskName;
    }

    public String getStatus(){
        return this.completed ? "X": " ";
    }

    public String getTaskTypeAsString(){
        if (this.taskType == Task.TaskType.T) {
            return "T";
        } else if (this.taskType == Task.TaskType.D) {
            return "D";
        } else {
            return "E";
        }
    }
}