package com.pablo.lucas.learningspring.business.services;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pablo.lucas.learningspring.business.domain.RoomReservation;
import com.pablo.lucas.learningspring.data.entity.Guest;
import com.pablo.lucas.learningspring.data.entity.Reservation;
import com.pablo.lucas.learningspring.data.entity.Room;
import com.pablo.lucas.learningspring.data.repository.GuestRepository;
import com.pablo.lucas.learningspring.data.repository.ReservationRepository;
import com.pablo.lucas.learningspring.data.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationSerice {
    
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationSerice(RoomRepository roomRepository, GuestRepository guestRepository,
            ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }



    public List<RoomReservation> getRoomReservationsForDate(final Date date) {
        final Iterable<Room> rooms = this.roomRepository.findAll();
        final Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            final RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });

        final Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));

        reservations.forEach(reservation -> {
            final RoomReservation  roomReservation = roomReservationMap.get(reservation.getReservationID());
            roomReservation.setDate(date);

            final Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });

        List<RoomReservation> roomReservations = new ArrayList<>();
        for( Long id: roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }



        return roomReservations;
    }
}
