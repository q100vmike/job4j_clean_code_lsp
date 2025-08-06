package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class TodoApp {

    private Menu menu;

    public TodoApp(Menu menu) {
        this.menu = menu;
    }

    public boolean addRootElement(String name, ActionDelegate delegate) {
        return menu.add(Menu.ROOT, name, delegate);
    }

    public boolean addChildElement(String parentName, String childName, ActionDelegate delegate) {
        return menu.add(parentName, childName, delegate);
    }

    public void runAction(String name) {
        menu.select(name).ifPresent(n -> n.getActionDelegate().delegate());
    }

    public void show() {
        Menu.MenuItemInfo item = null;
        Iterator<Menu.MenuItemInfo> iter = menu.iterator();
        while (iter.hasNext()) {
            item = iter.next();
            System.out.println(item.getNumber() + item.getName());
        }
    }

    public static void main(String[] args) {
        ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

        TodoApp todoApp = new TodoApp(new SimpleMenu());
        todoApp.addRootElement("Сходить в магазин", DEFAULT_ACTION);
        todoApp.addRootElement("Покормить собаку", DEFAULT_ACTION);
        todoApp.addChildElement("Сходить в магазин", "Купить продукты", DEFAULT_ACTION);
        todoApp.addChildElement("Купить продукты", "Купить хлеб", () -> System.out.println("ХЛЕБ КУПИ!"));
        todoApp.addChildElement("Купить продукты", "Купить молоко", DEFAULT_ACTION);
        todoApp.show();
        todoApp.runAction("Купить хлеб");
    }
}
