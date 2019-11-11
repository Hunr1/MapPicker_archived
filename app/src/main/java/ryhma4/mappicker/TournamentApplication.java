package ryhma4.mappicker;

import android.app.Application;

public class TournamentApplication extends Application {
    private static final TournamentEngine tournaments = new TournamentEngine();

    public static TournamentEngine getEngine() {
        return tournaments;
    }
}
