package jane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final File file;

    Storage(String filepath) {
        // "./data/janeeyre.txt"
        this.file = new File(filepath);
    }

    public void updateData(TaskList tasks) throws JaneException {
        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : tasks.getTasks()) {
                fw.write(task.format() + "\n");
            }
            //fw.close();
        } catch (IOException e) {
            throw new JaneException("The file could not be updated\n");
        }

    }

    public void storeTask(Task task, TaskList tasks) throws RuntimeException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            fw.write(task.format() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTaskArray(TaskList arr) throws JaneException {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] argsArr = s.nextLine().split(",");
                switch (argsArr[0]) {
                    case "T":
                        arr.addTask(Todo.loadTodo(argsArr));
                        break;
                    case "E":
                        arr.addTask(Event.loadEvent(argsArr));
                        break;
                    case "D":
                        arr.addTask(Deadline.loadDeadline(argsArr));
                        break;
                    default:
                        throw new JaneException("Oh dear, an error has occurred\n");
                }
            }
        } catch (FileNotFoundException e){
            throw new JaneException("Oh dear, an error has occurred\n");
        }
    }

    public TaskList loadData() throws JaneException {
        TaskList tasks = new TaskList();
        try {
            if (!file.createNewFile()) {
                loadTaskArray(tasks);
            }
            return tasks;
        } catch (IOException e) {
            throw new JaneException("An error occurred\n");
        }
    }
}
