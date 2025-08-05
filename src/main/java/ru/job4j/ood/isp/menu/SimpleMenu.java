package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
   /*  добавьте реализацию*/
        boolean result = true;

        if (Objects.equals(Menu.ROOT, parentName)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else {
            Optional<ItemInfo> parentInfo = findItem(parentName);
            if (parentInfo.isPresent()) {
                parentInfo.get().menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
            } else {
                result = false;
            }
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        MenuItemInfo item = null;
        Iterator<MenuItemInfo> iter = this.iterator();

        while (iter.hasNext()) {
            item = iter.next();
            if (item.getName().equals(itemName)) {
                break;
            }
        }

        return Optional.ofNullable(item);
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        List<MenuItemInfo> menuItemInfos = new ArrayList<>();
        DFSIterator iter = new DFSIterator();
        ItemInfo item = null;

        while (iter.hasNext()) {
            item = iter.next();
            menuItemInfos.add(new MenuItemInfo(item.menuItem, item.number));
        }
        return menuItemInfos.iterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator iter = new DFSIterator();
        ItemInfo item = null;

        while (iter.hasNext()) {
            item = iter.next();
            if (name.equals(item.menuItem.getName())) {
                break;
            }
        }
        return Optional.ofNullable(item);
    }

    private static class SimpleMenuItem implements MenuItem {
        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}