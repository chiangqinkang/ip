package Task;

import CommandLine.Line;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import Save.Save;

import static java.lang.Integer.parseInt;

public class TaskList {
    private ArrayList<Task> taskList;
    private final Line line = new Line();
    private int taskListLength;
    private int startPointer;
    private Save save;

    public TaskList() {
        this.save = new Save();
        this.taskList = new ArrayList<Task>();
        this.startPointer = 0;
        this.taskListLength = 0;
        loadTasksFromSave();
    }

    private String formatTaskForFile(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getTaskTypeAsString()).append(" | ");
        sb.append(task.getStatus().equals("X") ? "1" : "0").append(" | ");
        sb.append(task.readTask());
        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            sb.append(" | ").append(((Event) task).getStart());
            sb.append(" | ").append(((Event) task).getEnd());
        }
        return sb.toString();
    }

    private void saveTasks() {
        File file = new File(save.getPath());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                writer.write(formatTaskForFile(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasksFromSave() {
        File file = new File(save.getPath());
        if (!file.exists()) {
            return;  // No file to load from, start with an empty list
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                taskList.add(task);
                taskListLength++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }


    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task.TaskType taskType = Task.TaskType.valueOf(parts[0]);
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;
        list();
        switch (taskType) {
        case T:
            task = ToDo.of(description, taskType);
            break;
        case D:
            try {
                task = Deadline.of(description + " /by " + parts[3], taskType);
            } catch (TaskCreationException e) {
                throw new IllegalArgumentException("Invalid task type: " + taskType);
            }
            break;
        case E:
            try {
                task = Event.of(description + "/from " + parts[3] + " /to " + parts[4], taskType);
            } catch (TaskCreationException e) {
                System.out.println("Error occurred while parsing task");
                throw new IllegalArgumentException("Invalid task type: " + taskType);
            }
            break;
        default:
            throw new IllegalArgumentException("Invalid task type: " + taskType);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    public void add(String s, Task.TaskType taskType) {
        try {
            Task newTask;
            switch (taskType) {
                case T:
                    newTask = ToDo.of(s, taskType);
                    break;
                case D:
                    newTask = Deadline.of(s, taskType);
                    break;
                case E:
                    newTask = Event.of(s, taskType);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid task type");
            }
            this.taskList.add(newTask);
            this.taskListLength += 1;
            line.drawLine();
            System.out.println("    Got it. I've added this task: ");
            System.out.println("      [" + newTask.getTaskTypeAsString() + "][ ] " + newTask.readTask());
            if (this.taskListLength == 1) {
                System.out.println("    Now you have 1 task in the list.");
            } else {
                System.out.println("    Now you have " + this.taskListLength + " tasks in the list.");
            }
            line.drawLine();
        } catch (TaskCreationException e) {
            line.drawLine();
            System.out.println("    " + e.getMessage());
            line.drawLine();
        }
    }

    public void delete(int idx) {
        idx = idx - 1;
        if (idx < 0 || idx >= taskListLength) {
            line.drawLine();
            System.out.println("    Enter a valid index");
            line.drawLine();
        } else {
            Task removedTask = taskList.remove(idx);
            line.drawLine();
            taskListLength--;
            System.out.println("    Noted. I've removed this task: ");
            System.out.println("      [" + removedTask.getTaskTypeAsString() + "]" + "["+ removedTask.getStatus() + "] " + removedTask.readTask());
            if (taskListLength == 1) {
                System.out.println("    Now you have 1 task in the list.");
            } else {
                System.out.println("    Now you have " + this.taskListLength + " tasks in the list.");
            }
            line.drawLine();
        }
    }

    public void markAsDone(String s) {
        int idx = parseInt(s);
        idx -= 1;
        if ((idx < 0) || (idx >= taskListLength)) {
            return;
        }
        Task currentTask = taskList.get(idx);
        currentTask.markAsDone();
        line.drawLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      [" + currentTask.getStatus() + "] " + currentTask.readTask());
        line.drawLine();
    }


    public void markAsUndone(String s) {
        int idx = parseInt(s);
        idx -= 1;
        if ((idx < 0) || (idx >= taskListLength)) {
            return;
        }
        Task currentTask = taskList.get(idx);
        currentTask.markAsUndone();
        line.drawLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      [" + currentTask.getStatus() + "] " + currentTask.readTask());
        line.drawLine();
    }

    public void list() {
        if (taskListLength == 0) {
            return;
        }

        line.drawLine();
        for (int i = startPointer; i < taskListLength; i++) {
            int counter = i + 1;
            Task currentTask = this.taskList.get(i);
            System.out.println("    " + counter + ". [" + currentTask.getTaskTypeAsString() + "] [" + currentTask.getStatus()  + "] " + currentTask.readTask());
        }
        line.drawLine();
    }
}