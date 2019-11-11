package ryhma4.mappicker;

import java.util.ArrayList;

public class Game {

    private String gameName;
    private ArrayList<String> maps = new ArrayList<>();


    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public ArrayList<String> getMaps() {
        return maps;
    }

    public void setMaps(ArrayList<String> maps) {
        this.maps = maps;
    }
}
