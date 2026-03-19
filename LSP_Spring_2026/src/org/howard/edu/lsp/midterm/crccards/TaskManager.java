package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Task objects.
 * Supports adding tasks, finding tasks by ID, and retrieving tasks by status.
 * Uses a LinkedHashMap internally to prevent duplicate task IDs and support efficient lookup.
 *
 * @author Gerald Amoafo
 */
public class TaskManager {

    private Map<String, Task> tasks;

    /**
     * Constructs a new TaskManager with an empty task collection.
     */
    public TaskManager() {
        this.tasks = new LinkedHashMap<>();
    }

    /**
     * Adds a task to the manager. Throws an exception if a task with the same ID already exists.
     *
     * @param task the Task to add
     * @throws IllegalArgumentException if a task with the same taskId already exists
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Task with ID " + task.getTaskId() + " already exists.");
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds and returns the task with the given task ID.
     * Returns null if the taskId is null or no task with that ID is found.
     *
     * @param taskId the ID of the task to find
     * @return the Task with the given ID, or null if not found
     */
    public Task findTask(String taskId) {
        if (taskId == null) {
            return null;
        }
        return tasks.getOrDefault(taskId, null);
    }

    /**
     * Returns a list of all tasks whose status matches the specified value.
     *
     * @param status the status to filter tasks by
     * @return a List of Task objects with the matching status
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                result.add(task);
            }
        }
        return result;
    }
}
