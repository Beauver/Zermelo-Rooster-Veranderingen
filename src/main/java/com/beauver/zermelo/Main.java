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

public class Main {

    public static ZermeloAPI api;
    public static Date endDate = new Date();

    public static void main(String[] args) throws ZermeloApiException, IOException {
        endDate.setTime(endDate.getTime() + 432000000L);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //needs a school name as a terminal input and an auth code
        System.out.println("Go to: Zermelo --> Settings --> \"Link External Application\"");
        System.out.println("Enter your school name: ");
        String schoolName = reader.readLine();
        System.out.println("Enter your Auth Code or Access Token:");
        String authCode = reader.readLine();
        long authCodeL;
        String AccessToken;
        //checks for auth code
        try{
            authCodeL = Long.parseLong(authCode);
            AccessToken = ZermeloAPI.getAccessToken(schoolName, String.valueOf(authCodeL));
        } catch (Exception e) {
            AccessToken = authCode;
        }
        //gets the access token
        System.out.println(AccessToken);
        //enables the API
        api = ZermeloAPI.getAPI(schoolName, AccessToken);

        System.out.println("Cancelled lessons:");
        getCancelledLessons();
        System.out.println("Moved lessons:");
        getMovedLessons();
    }
    private static void getCancelledLessons() throws ZermeloApiException {
        List<Appointment> appointments = api.getAppointments(new Date(), endDate);
        for(Appointment appointment : appointments){

            //if the lesson is cancelled
            if(appointment.isCancelled()){

                String formattedStartDate = getStartDate(appointment);
                String formattedEndDate = getEndDate(appointment);

                List<String> subject = appointment.getSubjects();
                List<String> teacher = appointment.getTeachers();

                //prints the date
                System.out.println("-" + "Lesson: " + subject.get(0) + " - Teachers: " + teacher.get(0) + " - " + "Date: " + formattedStartDate + " - " + formattedEndDate);
            }
        }
    }

    private static void getMovedLessons() throws ZermeloApiException {
        List<Appointment> appointments = api.getAppointments(new Date(), endDate);
        for(Appointment appointment : appointments){
            if(appointment.isMoved()){

                String formattedStartDate = getStartDate(appointment);
                String formattedEndDate = getEndDate(appointment);

                List<String> subject = appointment.getSubjects();
                List<String> teacher = appointment.getTeachers();
                List<String> rooms = appointment.getLocations();

                //prints the date
                System.out.println("-" + "Lesson: " + subject.get(0) + " - Teachers: " + teacher.get(0) + " - " + "New Room: " + rooms.get(0) + " - " + "Date: " + formattedStartDate + " - " + formattedEndDate);
            }
        }
    }

    private static String getStartDate(Appointment appointment){
        long startDateUnixTimestamp = appointment.getStart();

        //getting the date then putting it in the timezone of the current server
        Instant startInstant = Instant.ofEpochSecond(startDateUnixTimestamp);
        LocalDateTime startDateTime = LocalDateTime.ofInstant(startInstant, ZoneId.systemDefault());
        //format the date accordingly
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM HH:mm");

        return startDateTime.format(formatter);
    }
    private static String getEndDate(Appointment appointment){
        long endDateUnixTimestamp = appointment.getEnd();

        Instant endInstant = Instant.ofEpochSecond(endDateUnixTimestamp);
        LocalDateTime endDateTime = LocalDateTime.ofInstant(endInstant, ZoneId.systemDefault());
        DateTimeFormatter endFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return endDateTime.format(endFormatter);
    }
}
