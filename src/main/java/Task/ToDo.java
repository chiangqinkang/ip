package Task;

public class ToDo extends Task {

    private ToDo(String name, TaskType taskType) {
        super(name, taskType);
    }

    public String getTaskTypeAsString(){
        return "T";
    }

    public static ToDo of(String name, TaskType taskType) {
        return new ToDo(name, TaskType.T);
    }
}
