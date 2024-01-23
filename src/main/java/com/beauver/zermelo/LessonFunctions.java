package com.beauver.zermelo;

import nl.mrwouter.zermelo4j.api.ZermeloApiException;
import nl.mrwouter.zermelo4j.appointments.Appointment;

import java.util.Date;
import java.util.List;

public class LessonFunctions {

    public static void getCancelledLessons() throws ZermeloApiException {
        List<Appointment> appointments = Main.api.getAppointments(new Date(), Main.endDate);
        for(Appointment appointment : appointments){

            //if the lesson is cancelled
            if(appointment.isCancelled()){

                String formattedStartDate = DateFunctions.getStartDate(appointment);
                String formattedEndDate = DateFunctions.getEndDate(appointment);

                List<String> subject = appointment.getSubjects();
                List<String> teacher = appointment.getTeachers();

                //prints the date
                System.out.println("-" + "Lesson: " + subject.get(0) + " - Teachers: " + teacher.get(0) + " - " + "Date: " + formattedStartDate + " - " + formattedEndDate);
            }
        }
    }

    public static void getMovedLessons() throws ZermeloApiException {
        List<Appointment> appointments = Main.api.getAppointments(new Date(), Main.endDate);
        for(Appointment appointment : appointments){
            if(appointment.isMoved()){

                String formattedStartDate = DateFunctions.getStartDate(appointment);
                String formattedEndDate = DateFunctions.getEndDate(appointment);

                List<String> subject = appointment.getSubjects();
                List<String> teacher = appointment.getTeachers();
                List<String> rooms = appointment.getLocations();

                //prints the date
                System.out.println("-" + "Lesson: " + subject.get(0) + " - Teachers: " + teacher.get(0) + " - " + "New Room: " + rooms.get(0) + " - " + "Date: " + formattedStartDate + " - " + formattedEndDate);
            }
        }
    }

}
