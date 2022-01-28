package com.pablo.lucas.learningspring.business.web;

import java.util.Date;
import java.util.List;

import com.pablo.lucas.learningspring.business.domain.RoomReservation;
import com.pablo.lucas.learningspring.business.services.ReservationSerice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservations")
public class RoomReservationWebController {
    private final ReservationSerice reservationSerice;
    
    @Autowired
    public RoomReservationWebController(ReservationSerice reservationSerice) {
        this.reservationSerice = reservationSerice;
    }

    @GetMapping
    public String getReservations(@RequestParam(value="date", required = false) final String dateString, Model model) {
        final Date date = DateUtils.createDateFromDateString(dateString);
        final List<RoomReservation> roomReservations = this.reservationSerice.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations", roomReservations);
        return "reservations";
    }

}
