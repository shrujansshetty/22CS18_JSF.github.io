package com.jsf.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jsf.model.Appointment;
import com.jsf.repository.AppointmentRepository;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/user/submit-appointment")
    public String submitAppointment(
            @RequestParam("email") String email,
            @RequestParam("fullName") String fullName,
            @RequestParam("doctor") String doctor,
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            @RequestParam("condition") String pcondition,
            RedirectAttributes redirectAttributes) {

        // Create a new Appointment object and populate it with form data
        Appointment appointment = new Appointment();
        appointment.setEmail(email);
        appointment.setFullName(fullName);
        appointment.setDoctor(doctor);
        appointment.setDate(LocalDate.parse(date));
        appointment.setTime(LocalTime.parse(time));
        appointment.setCondition(pcondition);

        // Save appointment to database
        appointmentRepository.save(appointment);

        // Add a success message and redirect to the home page
        redirectAttributes.addFlashAttribute("message", "Appointment booked successfully!");
        return "redirect:/user/home";
    }
}
