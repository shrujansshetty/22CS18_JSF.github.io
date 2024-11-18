package com.jsf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

import com.jsf.model.Appointment;
import com.jsf.model.UserDtls;
import com.jsf.repository.AppointmentRepository;
import com.jsf.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String home() {
        return "admin/home";
    }

    @GetMapping("/home")
    public String adminhome() {
        return "admin/home";
    }

//    @GetMapping("/appointments")
//    public String viewAppointments(@AuthenticationPrincipal UserDtls user, Model model) {
//    	//bro the error is that here the user parameter is not being sent properly
//    
//
//        String email = user.getEmail();
////    	String email="admin@gmail.com";
//        com.jsf.model.UserDtls doctorEntity = userRepo.findByEmail(email);
//
//        if (doctorEntity == null) {
//            logger.error("No doctor found with email: {}", email);
//            model.addAttribute("error", "Doctor not found");
//            return "error";
//        }
//
//        String doctorName = doctorEntity.getFullName();
//        logger.info("Fetching appointments for doctor: {} ({})", doctorName, email);
//
//        LocalDate today = LocalDate.now();
//
//        List<Appointment> upcomingAppointments = appointmentRepository.findByDoctorAndDateAfterOrderByDateAsc(doctorName, today);
//        List<Appointment> previousAppointments = appointmentRepository.findByDoctorAndDateBeforeOrderByDateDesc(doctorName, today);
//
//        model.addAttribute("doctor", doctorName);
//        model.addAttribute("upcomingAppointments", upcomingAppointments);
//        model.addAttribute("previousAppointments", previousAppointments);
//
//        return "admin/appointments";
//    }
  
    
    
    
    
    
    
//    @GetMapping("/appointments")
//    public String viewAppointments(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, Model model) {
//        if (user == null) {
//            // Handle case where user is not logged in
//            return "admin/appointments";
//        }
//
//        // Get the logged-in user's email
//        String email = user.getUsername(); // This retrieves the email/username from Spring Security
//
//        // Fetch doctor details from the repository
//        com.jsf.model.UserDtls doctorEntity = userRepo.findByEmail(email);
//
//        if (doctorEntity == null) {
//            // If doctor is not found, return empty or handle gracefully
//            model.addAttribute("error", "Doctor not found");
//            return "error";
//        }
//
//        String doctorName = doctorEntity.getFullName();
//        LocalDate today = LocalDate.now();
//
//        // Fetching appointments for the doctor
//        List<Appointment> upcomingAppointments = appointmentRepository.findByDoctorAndDateAfterOrderByDateAsc(doctorName, today);
//        List<Appointment> previousAppointments = appointmentRepository.findByDoctorAndDateBeforeOrderByDateDesc(doctorName, today);
//
//        model.addAttribute("doctor", doctorName);
//        model.addAttribute("upcomingAppointments", upcomingAppointments);
//        model.addAttribute("previousAppointments", previousAppointments);
//
//        return "admin/appointments";
//    }

    
    
    @GetMapping("/appointments")
    public String viewAppointments(@AuthenticationPrincipal User user, Model model) {
        if (user == null) {
            return "admin/appointments";
        }

        // Get the logged-in user's email
        String email = user.getUsername();
        UserDtls doctorEntity = userRepo.findByEmail(email);
        System.out.println("Doctor Name: " + doctorEntity.getFullName());
        System.out.println("Doctor email: " + email);
        System.out.println("----------------------Doctor Name:--------------------------- ");
        if (doctorEntity == null) {
            model.addAttribute("doctor", "Unknown Doctor");
        } else {
            String doctorName = doctorEntity.getFullName();
            model.addAttribute("doctor", doctorName);
        }

        LocalDate today = LocalDate.now();

        // Fetching upcoming and previous appointments
        List<Appointment> upcomingAppointments = appointmentRepository.findByDoctorAndDateAfterOrderByDateAsc(doctorEntity.getFullName(), today);
        List<Appointment> previousAppointments = appointmentRepository.findByDoctorAndDateBeforeOrderByDateDesc(doctorEntity.getFullName(), today);

        model.addAttribute("upcomingAppointments", upcomingAppointments);
        model.addAttribute("previousAppointments", previousAppointments);
        System.out.println("Doctor Name: " + doctorEntity.getFullName());

        return "admin/appointments";
    }

}
