package ryhma4.mappicker;


//Olioon tallennetaan karttojen kuvat(int viittaus drawable ID:seen) ja nimet
public class Map {
    private String mapName;
    private Integer mapImage;
    private Boolean isSelected;
    private Boolean CTPick;
    private Boolean TeroPick;

    public Map(String mapName, Integer mapImage) {
        this.mapName = mapName;
        this.mapImage = mapImage;
        isSelected = false;
        CTPick = false;
        TeroPick = false;
    }

    public void setCTPick(Boolean CTPick) {
        this.CTPick = CTPick;
    }

    public void setTeroPick(Boolean teroPick) {
        TeroPick = teroPick;
    }

    public Boolean getCTPick() {
        return CTPick;
    }

    public Boolean getTeroPick() {
        return TeroPick;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
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


