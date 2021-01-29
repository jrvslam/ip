package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private FileWriter fio;
    private final File file;
    private final ArrayList<Task> arrL;

    public Storage() {
        ArrayList<Task> arr = new ArrayList<>();
        this.file = initiateFile();
        populateList(arr);
        this.arrL = arr;
    }

    private File initiateFile () {
        String home = System.getProperty("user.home");

        // inserts correct file path separator on *nix and Windows
        // works on *nix
        // works on Windows
        Path path = java.nio.file.Paths.get(home, "iP", "data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        File file = null;
        try {
            if (!directoryExists) {
                java.nio.file.Files.createDirectories(path);
                System.out.println("   Path Created: " + path);
            } else {
                System.out.println("   Path exits");
            }
            String filePath = path + File.separator + "Duke.txt";
            file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("   File created: " + file.getName());
            } else {
                System.out.println("   File exits");
            }
        } catch (IOException e) {
            System.out.println("Failed to create File");
            e.printStackTrace();
        }
        return file;
    }

    private void populateList (ArrayList<Task> arr) {
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                int commandEnd = data.indexOf(" | ");
                String task = data.substring(0, commandEnd);
                data = data.substring(commandEnd + 3);
                int stateEnd = data.indexOf(" | ");
                String state = data.substring(0, stateEnd);
                data = data.substring(stateEnd + 3);
                int inputEnd = data.indexOf(" | ");
                switch (task) {
                case "T":
                    arr.add(new ToDo(data, Integer.parseInt(state)));
                    break;
                case "D":
                    arr.add(new Deadline(data.substring(0, inputEnd), data.substring(inputEnd + 3),
                            Integer.parseInt(state)));
                    break;
                case "E":
                    arr.add(new Event(data.substring(0, inputEnd), data.substring(inputEnd + 3),
                            Integer.parseInt(state)));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initialiseFW() {
        try {
            this.fio = new FileWriter(file);
        } catch (IOException e) {
            System.out.println("Unable to create FileWriter");
            e.printStackTrace();
        }
    }

    public void writeToFile(Task task) {
        try {
            this.fio.write(task.taskSave() + "\n");
        } catch (IOException e) {
            System.out.println("Unable to write to file");
            e.printStackTrace();
        }
    }

    public void beginClose() {
        this.initialiseFW();
        for (Task task: this.arrL) {
            writeToFile(task);
        }
    }

    public void closeFile () {
        try {
            this.fio.close();
        } catch (IOException e) {
            System.out.println("Unable to close file");
            e.printStackTrace();
        }

    }

    public void add(Task task) {
        this.arrL.add(task);
    }

    public Task remove(int index) {
        return this.arrL.remove(index);
    }

    public int getArrSize() {
        return arrL.size();
    }

    public Task get(int index) {
        return this.arrL.get(index);
    }
}
