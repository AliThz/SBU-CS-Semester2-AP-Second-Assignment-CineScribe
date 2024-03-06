import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Movie {
    public static final String API_KEY = "36ccb2d7";
    int ImdbVotes;
    ArrayList<String> actorsList;
    String rating;

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes){
        //TODO --> (Write a proper constructor using the get_from_api functions)
    }

    public Movie(String title) throws IOException {
        String movieData = getMovieData(title);
        ImdbVotes = getImdbVotesViaApi(movieData);
        rating = getRatingViaApi(movieData);
        getActorListViaApi(movieData);
    }

    @SuppressWarnings("deprecation")
    /**
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */

    public String getMovieData(String title) throws IOException {
        URL url = new URL("https://www.omdbapi.com/?t="+title+"&apikey="+API_KEY);
        URLConnection Url = url.openConnection();
        Url.setRequestProperty("Authorization", "Key" + API_KEY);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine())!=null) {
            stringBuilder.append(line);
        }
        reader.close();
        //handle an error if the chosen movie is not found
        return stringBuilder.toString();
    }
    public int getImdbVotesViaApi(String moviesInfoJson){
        JSONObject jsonObject = new JSONObject(moviesInfoJson);
        String stringImdbVotes =  jsonObject.getString("imdbVotes").replace(",","");
        int result = Integer.parseInt(stringImdbVotes);
        return result;
    }

    public String getRatingViaApi(String moviesInfoJson){
        JSONObject jsonObject = new JSONObject(moviesInfoJson);
        JSONArray ratings = jsonObject.getJSONArray("Ratings");
        JSONObject rating = ratings.getJSONObject(0);
        String result = rating.getString("Value");
        return result;
    }

    public void getActorListViaApi(String moviesInfoJson){
        //TODO --> (This function must return the "Actors" in actorsList)
        JSONObject jsonObject = new JSONObject(moviesInfoJson);
        String allActors = jsonObject.getString("Actors");
        String[] actors = allActors.split(", ");
        this.actorsList.addAll(Arrays.asList(actors));
    }
}