package com.beauver.zermelo;

import nl.mrwouter.zermelo4j.annoucements.Announcement;
import nl.mrwouter.zermelo4j.api.ZermeloApiException;
import nl.mrwouter.zermelo4j.appointments.Appointment;
import nl.mrwouter.zermelo4j.appointments.AppointmentType;

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
                System.out.println("-" + "Lesson: " + subject + " - Teachers: " + teacher + " - " + "Date: " + formattedStartDate + " - " + formattedEndDate);
            }
        }
    }

    public static void getMovedLessons() throws ZermeloApiException {
        List<Appointment> appointments = Main.api.getAppointments(new Date(), Main.endDate);
        for(Appointment appointment : appointments){
            if(appointment.isMoved() && !appointment.getType().equals(AppointmentType.EXAM)){

                String formattedStartDate = DateFunctions.getStartDate(appointment);
                String formattedEndDate = DateFunctions.getEndDate(appointment);

                List<String> subject = appointment.getSubjects();
                List<String> teacher = appointment.getTeachers();
                List<String> rooms = appointment.getLocations();

                //prints the date
                System.out.println("-" + "Lesson: " + subject + " - Teachers: " + teacher + " - " + "New Room: " + rooms + " - " + "Date: " + formattedStartDate + " - " + formattedEndDate);
            }
        }
    }

    public static void getExams() throws ZermeloApiException {
        List<Appointment> announcements = Main.api.getAppointments(new Date(), Main.endDate);
        for(Appointment announcement : announcements){
            if(announcement.getType().equals(AppointmentType.EXAM)){

                String formattedStartDate = DateFunctions.getStartDate(announcement);
                String formattedEndDate = DateFunctions.getEndDate(announcement);

                List<String> subject = announcement.getSubjects();
                List<String> rooms = announcement.getLocations();

                System.out.println("-" + "Exam: " + subject + " - " + "Room: " + rooms + " - " + "Date: " + formattedStartDate + " - " + formattedEndDate);
            }
        }
    }

}
