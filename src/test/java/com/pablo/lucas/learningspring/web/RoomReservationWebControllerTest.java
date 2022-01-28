package com.pablo.lucas.learningspring.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pablo.lucas.learningspring.business.domain.RoomReservation;
import com.pablo.lucas.learningspring.business.services.ReservationSerice;
import com.pablo.lucas.learningspring.business.web.DateUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(RoomReservationWebControllerTest.class)
public class RoomReservationWebControllerTest {

    @MockBean
    private ReservationSerice reservationSerice;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getReservations() throws Exception {
        final String dateString = "2022-01-01";
        final Date date = DateUtils.createDateFromDateString(dateString);

        final List<RoomReservation> roomReservations = new ArrayList<>();
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setLastName("Unit");
        roomReservation.setFirstName("Junit");
        roomReservation.setDate(date);
        roomReservation.setGuestId(1);
        roomReservation.setRoomId(100);
        roomReservation.setRoomName("Junit room");
        roomReservation.setRoomNumber("J1");
        roomReservations.add(roomReservation);

        given(reservationSerice.getRoomReservationsForDate(date)).willReturn(roomReservations);

        this.mockMvc.perform(get("/reservations?2022-01-01"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Unit, Junit")));
        
    }
    
}
