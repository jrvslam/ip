package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Ui Class that handles passing output
 */
public class Ui {
    private final static String CHATBOT_NAME = "Rawrz";

    /**
     * Constructor for Ui. Creates a Scanner with System.in
     */
    public Ui() {
    }

    /**
     * Shows the introduction message to the user.
     */
    public String getIntroResponse() {
        return "Hello there! I'm " + CHATBOT_NAME + ", always here for you!\n   How can I help you today?";
    }

    /**
     * Shows the goodbye message to the user.
     */
    public String getGoodbyeResponse() {
        return "Bye! Hope to see you again! " + CHATBOT_NAME + "! :)";
    }

    /**
     * Shows the error message to the user.
     * @param error This is the String error message.
     * @return This is Rawrz's response
     */
    public String getErrorResponse(String error) {
        return error;
    }


    /**
     * Shows the remove message to the user.
     * @param task This is the task that is removed
     * @param noTasks This is the number of tasks left after removal.
     * @return This is Rawrz's response
     */
    public String getRemoveResponse(Task task, int noTasks) {
        return "Okay! I've removed this task:\n" + task +"\nNow you have " + noTasks + " tasks in the list";
    }

    /**
     * Shows the list of tasks stored in the chatbot to the user
     * @param arrL This is the Storage that contains the list of Tasks stored.
     * @return This is Rawrz's response
     */
    public String getListResponse(ArrayList<Task> arrL) {
        String output;
        if (arrL.isEmpty()) {
            output = "No Tasks found!";
        } else {
            output = "Here are the results!\n";
            for (int i = 0; i < arrL.size(); i++) {
                output = output.concat("   " + (i + 1) + ". " + arrL.get(i) + "\n");
            }
        }
        return output;
    }

    /**
     * Shows the done message to the user.
     * @param task This is the task that has been completed.
     * @return This is Rawrz's response
     */
    public String getDoneResponse(Task task) {
        return "   Nice! I've marked this task as done:\n   " + task;
    }

    /**
     * Shows the add task message to the user.
     * @param task This is the task that is added.
     * @param noTasks This is the number of tasks that are now in the system.
     * @return This is Rawrz's response
     */
    public String getAddResponse(Task task, int noTasks) {
        return "Got it! I've added this task:\n   " + task + "\nNow you have " + noTasks + " tasks in the list";
    }

    /**
     * Shows the list of reminders in the chatbot to the user
     * @param reminders This is the Storage that contains the list of Reminders stored.
     * @return This is Rawrz's response
     */
    public String getReminders(ArrayList<Task> reminders) {
        String output;
        if (reminders.isEmpty()) {
            output = "No reminders so far! :)";
        } else {
            output = "You have these reminders!\n";
            for (int i = 0; i < reminders.size(); i++) {
                output = output.concat("   " + (i + 1) + ". " + reminders.get(i).printWithReminder() + "\n");
            }
        }
        return output;
    }

    public String getAddReminderResponse(Task task) {
        return "Got it! I've added this reminder for:\n   " + task + "\nI'll remind you on " +
                task.getReminder().getReminderDateOnly() + "! :)";
    }
}
