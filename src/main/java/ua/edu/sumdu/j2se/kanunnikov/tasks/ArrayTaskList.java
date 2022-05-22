package ua.edu.sumdu.j2se.kanunnikov.tasks;

import java.util.Arrays;

public class ArrayTaskList {

    private static final int DEFAULT_CAPACITY = 10;
    private Task[] taskArray;

    public ArrayTaskList() {
        this.taskArray = new Task[DEFAULT_CAPACITY];
    }

    private void grow() {
        int oldCapacity = taskArray.length;
        int newCapacity = oldCapacity + DEFAULT_CAPACITY;
        taskArray = Arrays.copyOf(taskArray, newCapacity);
    }

    private void ensureCapacity() {
        if (taskArray[taskArray.length - taskArray.length / 5] != null) {
            grow();
        }
    }

    /**
     * It adds a task to the ArrayTaskList.
     *
     * @param task - task to be added.
     */
    public void add(Task task) {
        ensureCapacity();
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == null) {
                taskArray[i] = task;
                break;
            }
        }
    }

    /**
     * It deletes a task from the ArrayTaskList and
     * returns boolean depending on there is an appropriate one.
     * It removes any appropriate task if there are more than one.
     *
     * @param task - task to be deleted from
     * @return - true when task found and false in another case
     */
    public boolean remove(Task task) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == task) {
                int numMoved = size() - i;
                System.arraycopy(taskArray, i + 1, taskArray, i,
                        numMoved);
                return true;
            }
        }
        return false;
    }

    /**
     * It shows an amount of Task in the container.
     *
     * @return - amount of the container elements
     */
    public int size() {
        int i = 0;
        int j = 0;
        while (taskArray.length != i) {
            if (taskArray[i] != null) {
                j++;
            }
            i++;
        }
        return j;
    }

    /**
     * It shall give you an element under the index provided.
     * Indexing starts from zero.
     *
     * @param index - search key
     * @return - searched element
     */
    public Task getTask(int index) {
        return taskArray[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList incomingTasks = new ArrayTaskList();
        for (int i = 0; i <= size(); i++) {
            if (taskArray[i] != null) {
                int temp = taskArray[i].getTime();
                if (temp > from && temp <= to && from != 0) {
                    incomingTasks.add(taskArray[i]);
                }
            }
        }
        return incomingTasks;
    }

    @Override
    public String toString() {
        return "ArrayTaskList{"
                + "taskArray=" + Arrays.toString(taskArray)
                + '}';
    }
}
