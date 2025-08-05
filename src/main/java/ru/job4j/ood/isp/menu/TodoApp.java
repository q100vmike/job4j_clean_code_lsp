package ru.job4j.ood.isp.menu;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action")
 * и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {

    private Menu menu;

    public TodoApp(Menu menu) {
        this.menu = menu;
    }

    public boolean addRootElement(String name, ActionDelegate delegate) {
        return menu.add(Menu.ROOT, name, delegate);
    }

    public boolean addChildElement(String parentName, String childName, ActionDelegate delegate) {

        return true;
    }

    public void runAction(String name) {

    }

    public static void main(String[] args) {
        TodoApp todoApp = new TodoApp(new SimpleMenu());

    }
}
