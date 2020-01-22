package com.marcoscouto.basketapi.client;

import com.marcoscouto.basketapi.client.exception.ClientException;
import com.marcoscouto.basketapi.model.Team;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeamClient {

    private final String host = "free-nba.p.rapidapi.com";
    private final String key = "804a18db84msh025369b8e1261c1p159576jsna1a63729f3fe";

    public List<Team> getAllTeams(String url) {
        try {

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("x-rapidapi-host", host);
            conn.setRequestProperty("x-rapidapi-key", key);
            InputStream in;

            if (conn.getResponseCode() == 200) {
                List<Team> teams = new ArrayList<>();
                in = conn.getInputStream();
                JSONObject obj = new JSONObject(IOUtils.toString(in, Charset.forName("UTF-8")));
                JSONArray array = obj.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    teams.add(
                            new Team(
                                    array.getJSONObject(i).getString("id"),
                                    array.getJSONObject(i).getString("abbreviation"),
                                    array.getJSONObject(i).getString("city"),
                                    array.getJSONObject(i).getString("conference"),
                                    array.getJSONObject(i).getString("division"),
                                    array.getJSONObject(i).getString("full_name"),
                                    array.getJSONObject(i).getString("name"),
                                    findState(array.getJSONObject(i).getString("city"))
                            ));
                }

                return teams;
            }


        } catch (ClientException | JSONException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public Team getTeamById(String url) {
        try {

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("x-rapidapi-host", host);
            conn.setRequestProperty("x-rapidapi-key", key);
            InputStream in;

            if (conn.getResponseCode() == 200) {
                in = conn.getInputStream();
                JSONObject obj = new JSONObject(IOUtils.toString(in, Charset.forName("UTF-8")));
                Team team = new Team(
                        obj.getString("id"),
                        obj.getString("abbreviation"),
                        obj.getString("city"),
                        obj.getString("conference"),
                        obj.getString("division"),
                        obj.getString("full_name"),
                        obj.getString("name"),
                        findState(obj.getString("city"))
                );
                return team;

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    private String findState(String city){
        if(city.equalsIgnoreCase( "atlanta"))
            return "Georgia";
        if(city.equalsIgnoreCase( "boston"))
            return "Massachusetts";
        if(city.equalsIgnoreCase( "brooklyn")
                || city.equalsIgnoreCase( "new york"))
            return "New York";
        if(city.equalsIgnoreCase( "charlotte"))
            return "North Carolina";
        if(city.equalsIgnoreCase( "chicago"))
            return "Illinois";
        if(city.equalsIgnoreCase( "cleveland"))
            return "Ohio";
        if(city.equalsIgnoreCase( "dallas")
                || city.equalsIgnoreCase( "houston")
                || city.equalsIgnoreCase( "san antonio"))
            return "Texas";
        if(city.equalsIgnoreCase( "denver"))
            return "Colorado";
        if(city.equalsIgnoreCase( "detroit"))
            return "Michigan";
        if(city.equalsIgnoreCase( "golden state")
                || city.equalsIgnoreCase( "los angeles")
                || city.equalsIgnoreCase( "la")
                || city.equalsIgnoreCase( "sacramento"))
            return "California";
        if(city.equalsIgnoreCase( "miami")
                || city.equalsIgnoreCase( "orlando"))
            return "Florida";
        if(city.equalsIgnoreCase( "new orleans"))
            return "Louisiana";
        if(city.equalsIgnoreCase( "portland"))
            return "Oregon";
        if(city.equalsIgnoreCase( "toronto"))
            return "Ontario";
        if(city.equalsIgnoreCase( "utah"))
            return "Utah";
        if(city.equalsIgnoreCase( "washington"))
            return "Washington";
        if(city.equalsIgnoreCase( "memphis"))
            return "Tennessee";
        if(city.equalsIgnoreCase( "milwaukee"))
            return "Wisconsin";
        if(city.equalsIgnoreCase( "minnesota"))
            return "Minnesota";
        if(city.equalsIgnoreCase( "oklahoma"))
            return "Oklahoma";
        if(city.equalsIgnoreCase( "philadelphia"))
            return "Pensilvania";
        if(city.equalsIgnoreCase( "phoenix"))
            return "Arizona";

        return "";
    }

}
