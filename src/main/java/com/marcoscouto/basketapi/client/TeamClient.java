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
                                    array.getJSONObject(i).getString("name")
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
                        obj.getString("name"));


                return team;

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
