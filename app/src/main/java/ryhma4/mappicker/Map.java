package ryhma4.mappicker;


//Olioon tallennetaan karttojen kuvat(int viittaus drawable ID:seen) ja nimet
public class Map {
    private String mapName;
    private Integer mapImage;

    public Map(String mapName, Integer mapImage) {
        this.mapName = mapName;
        this.mapImage = mapImage;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public Integer getMapImage() {
        return mapImage;
    }

    public void setMapImage(Integer mapImage) {
        this.mapImage = mapImage;
    }
}

