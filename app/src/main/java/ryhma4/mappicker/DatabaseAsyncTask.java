package ryhma4.mappicker;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DatabaseAsyncTask extends AsyncTask< String, Integer, Boolean> {


    private String tournamentID;
    String result;
    interface OnSleepProgressUpdate {
        void sleepDone();
    }

    private OnSleepProgressUpdate callback = null;

    public void setCallback(OnSleepProgressUpdate callback) {
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(String... strings) {

        executeTask();
        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        callback.sleepDone();
    }

    private void executeTask() {
        try {
            URL url = new URL("https://iqkoa7ylxc.execute-api.eu-north-1.amazonaws.com/test/get-tournament");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream stream = urlConnection.getInputStream();
            result = fromStream(stream);
            Log.d("Tietokannasta: ", result);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }

}
