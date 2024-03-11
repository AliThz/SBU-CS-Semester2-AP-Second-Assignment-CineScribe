import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;

public class Actors {

    //region [ - Properties - ]
    public static final String API_KEY = "uvpIFPXS7SEX6mroJq8uxPyUf5q9vyWhLxfv4D6U";
    String name;
    String gender;
    String nationality;
    ArrayList<String> occupations;
    Double height;
    String birthday;
    Double netWorth;
    Boolean isAlive;
    String dateOfDeath;
    String message;
    //endregion

    //region [ - Constructors - ]

    //region [ - Actors(String netWorth, boolean isAlive) - ]
    public Actors(String netWorth, boolean isAlive) {
    }
    //endregion

    //region [ - Actors(String name) - ]
    public Actors(String name) {
        String actorData = getActorData(name);
        if (actorData.isEmpty()){
            message = "Error actor " + name + " not found";
            return;
        }
        this.name = getNameViaApi(actorData);
        gender = getGenderViaApi(actorData);
        nationality = getNationalityViaApi(actorData);
        occupations = new ArrayList<>();
        getOccupationsListViaApi(actorData);
        height = getHeightViaApi(actorData);
        birthday = getBirthdayViaApi(actorData);
        netWorth = getNetWorthViaApi(actorData);
        isAlive = isAlive(actorData);
        if(!isAlive) {
            dateOfDeath = getDateOfDeathViaApi(actorData);
        }
        message = "";
    }
    //endregion

    //endregion

    //region [ - Methods - ]

    //region [ - getActorData(String name) - ]
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
    //endregion

    //region [ - getNameViaApi(String actorsInfoJson) - ]
    public String getNameViaApi(String actorsInfoJson){
        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        String result = jsonObject.getString("name");
        return result;
    }
    //endregion

    //region [ - getGenderViaApi(String actorsInfoJson) - ]
    public String getGenderViaApi(String actorsInfoJson) {

        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        String result = jsonObject.getString("gender");
        return result;
    }
    //endregion

    //region [ - getNationalityViaApi(String actorsInfoJson) - ]
    public String getNationalityViaApi(String actorsInfoJson) {

        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        String result = jsonObject.getString("nationality");
        return result;
    }
    //endregion

    //region [ - getOccupationsListViaApi(String actorsInfoJson) - ]
    public void getOccupationsListViaApi(String actorsInfoJson){
        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        JSONArray allOccupations = jsonObject.getJSONArray("occupation");
        allOccupations.forEach(o -> this.occupations.add(o.toString()));
    }
    //endregion

    //region [ - getHeightViaApi(String actorsInfoJson) - ]
    public Double getHeightViaApi(String actorsInfoJson) {
        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        Double result = jsonObject.getDouble("height");
        return result;
    }
    //endregion

    //region [ - getBirthdayViaApi(String actorsInfoJson) - ]
    public String getBirthdayViaApi(String actorsInfoJson) {
        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        String result = jsonObject.getString("birthday");
        return result;
    }
    //endregion

    //region [ - getNetWorthViaApi(String actorsInfoJson) - ]
    public double getNetWorthViaApi(String actorsInfoJson) {

        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        Double result = jsonObject.getDouble("net_worth");
        return result;
    }
    //endregion

    //region [ - isAlive(String actorsInfoJson) - ]
    public boolean isAlive(String actorsInfoJson) {
        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        boolean result = jsonObject.getBoolean("is_alive");
        return result;
    }
    //endregion

    //region [ - getDateOfDeathViaApi(String actorsInfoJson) { - ]
    public String getDateOfDeathViaApi(String actorsInfoJson) {
        JSONObject jsonObject = new JSONObject(actorsInfoJson);
        String result = jsonObject.getString("death");
        return result;
    }
    //endregion

    //endregion

}