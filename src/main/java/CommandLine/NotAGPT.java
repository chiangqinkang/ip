package CommandLine;
import Task.Task;

import java.util.NoSuchElementException;
import java.util.Scanner;
import CommandLine.Line;
import Task.TaskList;
import CommandLine.Parser;

import static java.lang.Integer.parseInt;

public class NotAGPT {

    public static void main(String[] args) {
        Line line = new Line();
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        String logo = " _   _       _      _    ____ ____ _____ \n" +
                "| \\ | | ___ | |_   / \\  / ___|  _ \\_   _|\n" +
                "|  \\| |/ _ \\| __| / _ \\| |  _| |_) || |  \n" +
                "| |\\  | (_) | |_ / ___ \\ |_| |  __/ | |  \n" +
                "|_| \\_|\\___/ \\__/_/   \\_\\____|_|    |_|  ";

        System.out.println("Hello from\n" + logo);
        line.drawLine();
        System.out.println("    Hello! I'm NotAGPT");
        System.out.println("    What can I do for you?");
        line.drawLine();
        while (true) {
            try {
                String next = scanner.nextLine();
                parser.parse(tasks, next);
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
}