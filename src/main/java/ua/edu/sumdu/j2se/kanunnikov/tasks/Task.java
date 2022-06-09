/**
 * ua.edu.sumdu.j2se.kanunnikov.tasks is a package
 * for NC practice made by Mykola Kanunnikov (Nick Eve).
 */
package ua.edu.sumdu.j2se.kanunnikov.tasks;

import java.util.Arrays;

/**
 * Tasks blueprint.
 *
 * @author Mykola Kanunnikov (Nick Eve)
 * @version 1.1
 */

public class Task implements Cloneable {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    /**
     * It creates inactive and non-repeating task.
     *
     * @param title - name task however you want
     * @param time  - not conventional, just int
     */
    public Task(String title, int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }
        this.title = title;
        this.time = time;
    }

    /**
     * It creates inactive and repeating task.
     *
     * @param title    - name task however you want
     * @param start    - start of the time for task
     * @param end      - end of the period provided
     * @param interval - step between milestones (rounds) of task during the period
     */
    public Task(String title, int start, int end, int interval) {
        if (start < 0 || end <= 0 || interval <= 0) {
            throw new IllegalArgumentException("Time cannot be negative, as well as interval cannot be zero");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * It provides return as described below by own means
     * for inactive or non-repeating tasks, and
     * by means of specific methods for repeating ones.
     *
     * @param current - int representing the time
     *                considered as current
     * @return - the closest milestone (round)
     * of task in the time period, if any
     */
    public int nextTimeAfter(int current) {
        if (!isActive() || current >= getStartTime() && !isRepeated()) {
            return -1;
        } else if (current < getStartTime()) {
            return getStartTime();
        } else if (current == getStartTime()) {
            return getStartTime() + interval;
        } else if (current >= getEndTime() && isRepeated()) {
            return -1;
        } else {
            return nextTimeAfterRepeated(current, milestonesMethod());
        }
    }

    /**
     * It creates array of milestones (rounds) for task
     * resulting from time period and interval provided.
     *
     * @return - array of ints needed in
     * nextTimeAfterRepeated method
     */
    private int[] milestonesMethod() {
        int intervalAmount = getEndTime() / interval;
        int[] milestones = new int[intervalAmount];
        milestones[0] = getStartTime();

        int startFilling = getStartTime() + getRepeatInterval();

        for (int i = 1; i < intervalAmount; i++) {
            milestones[i] = startFilling;
            startFilling = startFilling + getRepeatInterval();
        }
        return milestones;
    }

    /**
     * Here we go through the array provided by mileseonesMethod,
     * up to milestone which is bigger than current time.
     * Then we return value as per if-else logic provided.
     *
     * @param current    - int representing the time
     *                   considered as current
     * @param milestones - array from mileseonesMethod
     * @return - the closest milestone (round) of task in the
     * time period, if any
     */
    private int nextTimeAfterRepeated(int current, int[] milestones) {
        System.out.println("Milestones: " + Arrays.toString(milestones));
        int i = milestones.length - 1;
        while (current < milestones[i]) {
            i--;
        }
        if (milestones[milestones.length - 1] < current) {
            return -1;
        } else if (milestones[i] == current || milestones[i] < current) {
            return milestones[i + 1];
        } else {
            return milestones[i];
        }
    }

    /**
     * It indicates whether the task to be done once or more.
     */
    public boolean isRepeated() {
        return interval != 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * It indicates whether the task to be done at all.
     */
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * It is just a getter with fork to serve both kind
     * of objects that may be instantiated.
     */
    public int getTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    /**
     * It is a setter designed for non-repeated tasks,
     * that however may be used with repeated ones.
     */
    public void setTime(int time) {
        if (isRepeated()) {
            this.time = time;
            setRepeatInterval(0);
        } else {
            this.time = time;
        }
    }

    /**
     * It is a setter designed for repeated tasks.
     */
    public void setTime(int start, int end, int interval) {
        do {
            this.start = start;
            this.end = end;
            this.interval = interval;
        } while (!isRepeated());
        setRepeatInterval(interval++);
    }

    public int getRepeatInterval() {
        return interval;
    }

    public void setRepeatInterval(int interval) {
        this.interval = interval;
    }

    /**
     * It is just a getter with fork to serve
     * both kind of objects that may be instantiated.
     */
    public int getStartTime() {
        if (!isRepeated()) {
            return time;
        } else {
            return start;
        }
    }

    public void setStartTime(int start) {
        this.start = start;
    }

    /**
     * It is just a getter with fork to serve both kind of objects
     * that may be instantiated.
     */
    public int getEndTime() {
        if (!isRepeated()) {
            return time;
        } else {
            return end;
        }
    }

    public void setEndTime(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Task{"
                + "title='" + title + '\''
                + ", time=" + time
                + ", start=" + start
                + ", end=" + end
                + ", interval=" + interval
                + ", active=" + active
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (getTime() != task.getTime()) return false;
        if (start != task.start) return false;
        if (end != task.end) return false;
        if (interval != task.interval) return false;
        if (isActive() != task.isActive()) return false;
        return getTitle() != null ? getTitle().equals(task.getTitle()) : task.getTitle() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + getTime();
        result = 31 * result + start;
        result = 31 * result + end;
        result = 31 * result + interval;
        result = 31 * result + (isActive() ? 1 : 0);
        return result;
    }

    public Task clone() {
        try {
            Task clone = (Task) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
