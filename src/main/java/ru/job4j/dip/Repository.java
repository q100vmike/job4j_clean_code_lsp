package ru.job4j.dip;

import java.util.List;

public class Repository {
    private List<Action> actionList;
    private List<SiFi> sifiList;
    private List<Triller> trillerList;

    public Repository(List<Action> actionList, List<SiFi> sifiList, List<Triller> trillerList) {
        this.actionList = actionList;
        this.sifiList = sifiList;
        this.trillerList = trillerList;
    }

    public Action getRandomActionFilm() {
        return actionList.get((int) (Math.random() * actionList.size()));
    }
}
