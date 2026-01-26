import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    @Override
    public String toString() {
        StringBuilder formatBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            formatBuilder.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return formatBuilder.toString();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int i) {
        tasks.remove(i);
    }

    public void mark(int i) {
        tasks.get(i).mark(true);
    }

    public void unMark(int i) {
        tasks.get(i).mark(false);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
