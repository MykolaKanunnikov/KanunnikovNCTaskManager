package ua.edu.sumdu.j2se.kanunnikov.tasks;

import java.util.*;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList {

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

    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        ensureCapacity();
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == null) {
                taskArray[i] = task;
                break;
            }
        }
    }

    public boolean remove(Task task) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i].equals(task)) {
                int numMoved = size() - i;
                System.arraycopy(taskArray, i + 1, taskArray, i,
                        numMoved);
                return true;
            }
        }
        return false;
    }

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

    public Task getTask(int index) {
        if (index > taskArray.length) {
            throw new IndexOutOfBoundsException("Wrong index");
        }
        return taskArray[index];
    }

    @Override
    public Stream<Task> getStream() {
        return Stream.of(taskArray).filter(Objects::nonNull);
    }


    @Override
    public String toString() {
        return "ArrayTaskList{"
                + "taskArray="
                + Arrays.toString(taskArray)
                + '}';
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator(getArrayTaskList());
    }

    public ArrayTaskList getArrayTaskList() {
        return this;
    }

    @Override
    public AbstractTaskList clone() {
        ArrayTaskList newAtl = (ArrayTaskList) super.clone();
        newAtl.taskArray = taskArray.clone();
        return newAtl;
    }

    private class ArrayIterator implements Iterator {
        ArrayTaskList atl;
        int i = -1;

        ArrayIterator(ArrayTaskList atl) {
            this.atl = atl.getArrayTaskList();
        }

        @Override
        public boolean hasNext() {
            return i < atl.size();
        }

        @Override
        public Task next() {
            if (i >= atl.size()) {
                throw new NoSuchElementException("You reached the array end");
            }
            if (i == -1) {
                i = 0;
            }
            Task task = atl.getTask(i);
            i++;
            return task;
        }

        @Override
        public void remove() {
            if (i == -1 || i >= size() + 1) {
                throw new IllegalStateException();
            }
            atl.remove(getTask(i - 1));
            i = 0;
        }
    }
}
