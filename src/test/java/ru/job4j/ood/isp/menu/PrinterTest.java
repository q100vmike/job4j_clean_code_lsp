package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.job4j.ood.isp.menu.SimpleMenuTest.STUB_ACTION;

class PrinterTest {

    @Test
    public void whenPrinterTest() {

        PrintStream saveOut = System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String expected = "1.Сходить в магазин"
                + System.lineSeparator()
                + "1.1.Купить продукты"
                + System.lineSeparator()
                + "1.1.1.Купить хлеб"
                + System.lineSeparator()
                + "1.1.2.Купить молоко"
                + System.lineSeparator()
                + "2.Покормить собаку"
                + System.lineSeparator();

        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        Printer printer = new Printer();

        printer.print(menu);

        assertEquals(expected, out.toString());
    }
}