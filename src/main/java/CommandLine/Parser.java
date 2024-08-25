package CommandLine;

import Task.TaskList;
import Task.Task;

import static java.lang.Integer.parseInt;

public class Parser {
    public Parser(){

    };
    public static void parse(TaskList taskList, String s) {
        Line line = new Line();
        String[] parts = s.split(" ", 2);
        String command = parts[0].toLowerCase();
        switch(command) {
        case "bye":
            NotAGPTExit();
            break;

        case "list":
            taskList.list();
            break;

        case "mark":
            if (parts.length > 1) {
                String idx = parts[1];
                taskList.markAsDone(idx);
            } else {
                responseHelper("Enter a task number");
            }
            break;

        case "unmark":
            if (parts.length > 1) {
                String idx = parts[1];
                taskList.markAsUndone(idx);
            } else {
                responseHelper("Enter a task number");
            }
            break;

        case "todo":
            if (parts.length > 1) {
                taskList.add(parts[1], Task.TaskType.T);
            } else {
                responseHelper("Enter a name for the To Do Task");
            }
            break;
        case "deadline":
            if (parts.length > 1) {
                taskList.add(parts[1], Task.TaskType.D);
            } else {
                responseHelper("Incomplete command. Enter a deadline");
            }
            break;
        case "event":
            if (parts.length > 1) {
                taskList.add(parts[1], Task.TaskType.E);
            } else {
                responseHelper("Incomplete command. Enter a start and end time");
            }
            break;
        case "delete":
            if (parts.length == 2) {
                try {
                    int idx = parseInt(parts[1]);
                    taskList.delete(idx);
                } catch (NumberFormatException e) {
                    responseHelper("Enter a valid index to delete");
                }
            } else {
                responseHelper("Enter a valid argument");
            }
            break;
        default:
            responseHelper("Unknown command, type help for a list of available commands");

        }
    }
    public static void responseHelper(String s) {
        Line line = new Line();
        line.drawLine();
        System.out.println("    " + s);
        line.drawLine();
    }


    public static void NotAGPTExit() {
        Line line = new Line();
        line.drawLine();
        System.out.println("Bye. Hope to see you again soon!");
        line.drawLine();
        System.exit(0);
    }
}
