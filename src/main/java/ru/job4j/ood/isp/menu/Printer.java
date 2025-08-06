package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class Printer implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        Menu.MenuItemInfo item = null;
        Iterator<Menu.MenuItemInfo> iter = menu.iterator();
        while (iter.hasNext()) {
            item = iter.next();
            System.out.println(item.getNumber() + item.getName());
        }
    }
}
