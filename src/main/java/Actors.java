import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class Actors {
    public static final String API_KEY = "uvpIFPXS7SEX6mroJq8uxPyUf5q9vyWhLxfv4D6U";
    String netWorth;
    Boolean isAlive;

    public Actors(String netWorth, boolean isAlive) {
        //TODO --> (Write a proper constructor using the get_from_api functions)
    }

    @SuppressWarnings({"deprecation"})
    /**
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name=" +
                    name.replace(" ", "+") + "&apikey=" + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
            System.out.println(connection);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.substring(1, response.length()-1);
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getNetWorthViaApi(String actorsInfoJson) {

        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        double result = jsonObject.getDouble("net_worth");
        return result;
    }

    public boolean isAlive(String actorsInfoJson) {
        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        boolean statues = jsonObject.getBoolean("is_alive");
        return statues;
    }

    public String getDateOfDeathViaApi(String actorsInfoJson) {
        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        String date = jsonObject.getString("death");
        return date;
    }

}