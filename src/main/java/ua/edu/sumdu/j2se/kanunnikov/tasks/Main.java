package ua.edu.sumdu.j2se.kanunnikov.tasks;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");

        Task task = new Task("practice", 10, 100, 20);
        task.setActive(true);
        int currentTime = 17;
        System.out.println("Current time: " + currentTime);
        System.out.println("Next time: " + task.nextTimeAfter(currentTime));

        ArrayTaskList list = new ArrayTaskList();
        list.add(task);
        list.add(task);
        list.remove(task);
        System.out.println(list);
    }
}
