package ru.job4j.dip;

import java.util.List;

public class Cinema {

    public Cinema() {
    }

    public void play(Repository repository) {
        repository.getRandomActionFilm();
    }
}

