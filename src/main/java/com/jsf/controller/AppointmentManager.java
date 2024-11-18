package com.jsf.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsf.model.Appointment;
import com.jsf.repository.AppointmentRepository;

@Controller
@RequestMapping("/user")
public class AppointmentManager {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/appointments")
    public String viewAppointments(Model model, Principal principal) {
        String email = principal.getName();
        List<Appointment> allAppointments = appointmentRepository.findByEmailOrderByDateAsc(email);

        List<Appointment> upcomingAppointments = allAppointments.stream()
                .filter(appointment -> appointment.getDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());

        List<Appointment> previousAppointments = allAppointments.stream()
                .filter(appointment -> appointment.getDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());

        model.addAttribute("upcomingAppointments", upcomingAppointments);
        model.addAttribute("previousAppointments", previousAppointments);
        return "user/appointments";
    }
}
