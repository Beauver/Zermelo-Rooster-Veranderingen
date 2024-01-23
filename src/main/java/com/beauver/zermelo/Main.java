package com.beauver.zermelo;

import nl.mrwouter.zermelo4j.ZermeloAPI;
import nl.mrwouter.zermelo4j.api.ZermeloApiException;
import nl.mrwouter.zermelo4j.appointments.Appointment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    public static ZermeloAPI api;
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static Date endDate = new Date();
    public static String schoolName;
    public static String authCode;
    public static String accessToken;


    public static void main(String[] args) throws ZermeloApiException, IOException {
        System.out.println("What user do you want to see their schedule of? \n(This will create a {user}.json file with their zermelo information, these files can also be shared across apps)");
        String user = Main.reader.readLine();
        endDate.setTime(endDate.getTime() + 432000000L);

        if(!InitFunctions.autoInit(user)){
            InitFunctions.manualInit(user);
        }

        //enables the API
        api = ZermeloAPI.getAPI(schoolName, accessToken);

        System.out.println("Important Exams:");
        LessonFunctions.getExams();
        System.out.println("Cancelled lessons:");
        LessonFunctions.getCancelledLessons();
        System.out.println("Moved lessons:");
        LessonFunctions.getMovedLessons();
    }
}
