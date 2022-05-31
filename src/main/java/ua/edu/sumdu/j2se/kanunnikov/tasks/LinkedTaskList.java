package ua.edu.sumdu.j2se.kanunnikov.tasks;

public class LinkedTaskList {

    private Node head;
    private int size;

    public LinkedTaskList() {
    }

    /**
     * It adds a task to the LinkedTaskList.
     *
     * @param task - task to be added.
     */
    public void add(Task task) {
        if (head == null) {
            head = new Node(task);
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(task);
        }
        size++;
    }

    /**
     * It deletes a task from the LinkedTaskList and
     * returns boolean depending on there is an appropriate one.
     * It removes any appropriate task if there are more than one.
     *
     * @param task - task to be deleted from
     * @return - true when task found and false in another case
     */
    public boolean remove(Task task) {
        if (task == null || head.element == null) {
            throw new NullPointerException();
        }
        if (task.equals(head.element) && head.next != null) {
            head = head.next;
            size--;
            return true;
        } else if (task.equals(head.element) && head.next == null) {
            head = null;
            size--;
            return true;
        }
        Node node = head;
        Node nextAfterDeleted = null;
        Node beforeDeleted = null;
        int i = 0;

        while (i != size) {
            if (nextAfterDeleted == null && task.equals(node.element)) {
                nextAfterDeleted = node.next;
            }
            if (beforeDeleted == null && node.next.element.equals(task)) {
                beforeDeleted = node;
            }
            node = node.next;
            i++;
        }
        if (beforeDeleted != null) {
            beforeDeleted.next = nextAfterDeleted;
            size--;
            return true;
        }
        return false;
    }

    /**
     * It shows an amount of Task in the container.
     *
     * @return - amount of the container elements
     */
    public int size() {
        return size;
    }

    /**
     * It shall give you an element under the index provided.
     * Indexing starts from zero.
     *
     * @param index - search key
     * @return - searched element
     */
    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Give me another index");
        }
        Node node = head;
        int i = 0;

        while (i != index) {
            node = node.next;
            i++;
        }
        return node.element;
    }

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList incomingTasks = new LinkedTaskList();
        if (from < 0) {
            throw new IllegalArgumentException("No negative, please");
        } else if (from > to) {
            throw new IllegalArgumentException("From parameter should not exceed to");
        } else {
            for (int i = 0; i < size; i++) {
                if (getTask(i).nextTimeAfter(from) > from && getTask(i).nextTimeAfter(from) < to) {
                    incomingTasks.add(getTask(i));
                }
            }
        }
        return incomingTasks;
    }

    @Override
    public String toString() {
        return "LinkedTaskList{"
                + "head="
                + head
                + '}';
    }

    private static class Node {
        private Task element;
        private Node next;

        private Node(Task task) {
            element = task;
            next = null;
        }

        @Override
        public String toString() {
            return "Node{"
                    + "element="
                    + element
                    + ", next="
                    + next
                    + '}';
        }
    }
}
