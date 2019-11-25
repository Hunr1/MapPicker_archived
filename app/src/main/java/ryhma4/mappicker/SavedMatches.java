package ryhma4.mappicker;


import java.util.ArrayList;

public class SavedMatches {
    public static SavedMatches myObj;

    public void RegularJavaClass() {}

    public static SavedMatches getInstance() {
        if (myObj == null) {
            myObj = new SavedMatches();
        }

        return myObj;
    }

    private ArrayList<Match> savedMatches = new ArrayList<>();

    public ArrayList<Match> getSavedMatches() {
        return savedMatches;
    }

    public void setSavedMatches(ArrayList<Match> savedMatches) {
        this.savedMatches = savedMatches;
    }
}
