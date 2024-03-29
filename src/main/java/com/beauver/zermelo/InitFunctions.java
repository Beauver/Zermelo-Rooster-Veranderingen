package com.beauver.zermelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.mrwouter.zermelo4j.ZermeloAPI;

import java.io.FileWriter;
import java.io.IOException;

public class InitFunctions {

    public static void manualInit(String user) throws IOException {
        //needs a school name as a terminal input and an auth code
        System.out.println("Go to: Zermelo --> Settings --> \"Link External Application\"");
        System.out.println("Enter your school name: ");
        Main.schoolName = Main.reader.readLine();
        System.out.println("Enter your Auth Code or Access Token:");
        Main.authCode = Main.reader.readLine();
        long authCodeL;
        //checks if it's an auth code, if it doesn't succeed, it's a accessToken
        try{
            authCodeL = Long.parseLong(Main.authCode);
            Main.accessToken = ZermeloAPI.getAccessToken(Main.schoolName, String.valueOf(authCodeL));
            System.out.println(Main.accessToken);
        } catch (Exception e) {
            Main.accessToken = Main.authCode;
        }
        initFile(user);
    }

    public static boolean autoInit(String user){
        return FileFunctions.readFromJSONFile("ZermeloRoosterVeranderingen/" + user + ".json");
    }


    private static void initFile(String user) throws IOException {
        FileFunctions.writeToFile("ZermeloRoosterVeranderingen/" + user  +".json", Main.schoolName, Main.accessToken);
    }

}
