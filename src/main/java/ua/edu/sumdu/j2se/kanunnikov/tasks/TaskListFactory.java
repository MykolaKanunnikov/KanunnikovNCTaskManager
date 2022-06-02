package ua.edu.sumdu.j2se.kanunnikov.tasks;

import com.sun.istack.internal.NotNull;

public class TaskListFactory {
    /**
     * It's a fork providing lists depending on key (type)
     *
     * @param type - key for choose between lists
     * @return - list associated with a key
     */
    @NotNull
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type.equals(ListTypes.types.ARRAY)) {
            return new ArrayTaskList();
        } else if (type.equals(ListTypes.types.LINKED)) {
            return new LinkedTaskList();
        } else {
            throw new IllegalArgumentException("Only ARRAY or LINKED allowed");
        }
    }
}
