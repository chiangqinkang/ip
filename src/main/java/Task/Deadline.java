package Task;

import CommandLine.Line;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private static Line line = new Line();

    private Deadline(String name, TaskType taskType, LocalDateTime deadline) {
        super(name, taskType);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeAsString(){
            return "D";
    }

    public static Deadline of(String name, TaskType taskType) throws TaskCreationException {
        try {
            String[] parts = name.split("/by", 2);
            String taskName = parts[0].trim();
            String taskDeadline = parts[1].trim();
            System.out.println(taskDeadline);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

            LocalDateTime deadline = LocalDateTime.parse(taskDeadline, formatter);

            return new Deadline(taskName, taskType, deadline);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            line.drawLine();
            System.out.println("      Error ");
            line.drawLine();
            throw new TaskCreationException();
        } catch (DateTimeParseException e) {
            line.drawLine();
            System.out.println("      Invalid deadline format. Expected format: 'task description /by date/time' ");
            line.drawLine();
            throw new TaskCreationException();
        }
    }

    public String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return this.deadline.format(formatter);
    }

    @Override
    public String readTask() {
        return super.readTask() + " (by: " + this.getBy() + ")";
    }
}
