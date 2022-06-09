package ua.edu.sumdu.j2se.kanunnikov.tasks;

public abstract class AbstractTaskList implements Iterable, Cloneable {

    /**
     * It's a field to keep a container's type for the incoming method.
     */
    private ListTypes.types type;

    /**
     * It increments container by the provided task.
     *
     * @param task - element to add
     */
    public abstract void add(Task task);

    /**
     * It deletes a task from the TaskList and
     * returns boolean depending on there is an appropriate one.
     * It removes any appropriate task if there are more than one.
     *
     * @param task - task to be deleted from
     * @return - true when task found and false in another case
     */
    public abstract boolean remove(Task task);

    /**
     * It shows an amount of Task in the container.
     *
     * @return - amount of the container elements
     */
    public abstract int size();

    /**
     * It shall give you an element under the index provided.
     * Indexing starts from zero.
     *
     * @param index - search key
     * @return - searched element
     */
    public abstract Task getTask(int index);

    /**
     * It provides tasks sublist for the defined period.
     * ARRAY is a default value to avoid NPE and keep running.
     *
     * @param from - sublist start
     * @param to   - sublist end
     * @return - sublist of tasks
     */
    public AbstractTaskList incoming(int from, int to) {
        if (getType() == null) {
            setType(ListTypes.types.ARRAY);
        }
        AbstractTaskList incomingTasks = TaskListFactory.createTaskList(getType());
        if (from < 0) {
            throw new IllegalArgumentException("No negative, please");
        } else if (from > to) {
            throw new IllegalArgumentException("From parameter should not exceed to");
        } else {
            for (int i = 0; i < size(); i++) {
                if (getTask(i).nextTimeAfter(from) >
                        from && getTask(i).nextTimeAfter(from) < to) {
                    incomingTasks.add(getTask(i));
                }
            }
        }
        return incomingTasks;
    }

    public ListTypes.types getType() {
        return type;
    }

    public void setType(ListTypes.types type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractTaskList that = (AbstractTaskList) o;

        return getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return getType() != null ? getType().hashCode() : 0;
    }

    @Override
    public AbstractTaskList clone() {
        try {
            return (AbstractTaskList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
