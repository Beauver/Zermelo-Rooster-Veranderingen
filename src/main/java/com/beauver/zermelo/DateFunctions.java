package com.beauver.zermelo;

import nl.mrwouter.zermelo4j.appointments.Appointment;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateFunctions {

    public static String getStartDate(Appointment appointment){
        long startDateUnixTimestamp = appointment.getStart();

        //getting the date then putting it in the timezone of the current server
        Instant startInstant = Instant.ofEpochSecond(startDateUnixTimestamp);
        LocalDateTime startDateTime = LocalDateTime.ofInstant(startInstant, ZoneId.systemDefault());
        //format the date accordingly
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM HH:mm");

        return startDateTime.format(formatter);
    }
    public static String getEndDate(Appointment appointment){
        long endDateUnixTimestamp = appointment.getEnd();

        Instant endInstant = Instant.ofEpochSecond(endDateUnixTimestamp);
        LocalDateTime endDateTime = LocalDateTime.ofInstant(endInstant, ZoneId.systemDefault());
        DateTimeFormatter endFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return endDateTime.format(endFormatter);
    }

}
