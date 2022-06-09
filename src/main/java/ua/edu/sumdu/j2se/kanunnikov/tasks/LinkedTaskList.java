package ua.edu.sumdu.j2se.kanunnikov.tasks;

import java.util.Iterator;

public class LinkedTaskList extends AbstractTaskList {

    private Node head;
    private int size;

    public LinkedTaskList() {
    }

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

    public int size() {
        return size;
    }

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

    @Override
    public String toString() {
        return "LinkedTaskList{"
                + "head="
                + head
                + '}';
    }


    @Override
    public Iterator iterator() {
        return new LinkedIterator(this);
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

    private class LinkedIterator implements Iterator {
        Node ltl;
        Task element;
        Node visitedLtl;
        private boolean nextIsCalled;


        LinkedIterator(LinkedTaskList ltl) {
            if (visitedLtl == null) {
                this.ltl = ltl.head;
            } else {
                this.ltl = visitedLtl;
            }
        }

        @Override
        public boolean hasNext() {
            return ltl != null;
        }

        @Override
        public Task next() {
            nextIsCalled = true;
            element = ltl.element;
            visitedLtl = ltl;
            ltl = ltl.next;
            return element;
        }

        @Override
        public void remove() {
            if (!nextIsCalled) {
                throw new IllegalStateException();
            } else {
                LinkedTaskList.this.remove(element);
            }
        }
    }
}
