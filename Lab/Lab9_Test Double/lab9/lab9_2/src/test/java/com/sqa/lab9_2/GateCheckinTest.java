package com.sqa.lab9_2;
/*
นายปฏิภาณ มะนิลทิพย์ 673380589-2 Sec 2
*/
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GateCheckinTest {

    @Mock
    private TicketCounter ticketCounter;

    private GateCheckin gateCheckin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gateCheckin = new GateCheckin(ticketCounter);
    }

    @Test
    void testCustomerEntry_NewTicket_ShouldAddToCheckinAndUpdateCounter() {
        // Act
        gateCheckin.customerEntry(1001);

        // Assert
        Set<Integer> checkedIn = gateCheckin.getPassengersOnBoard();
        assertTrue(checkedIn.contains(1001));
        assertEquals(1, checkedIn.size());

        // Verify ว่ามีการเรียก TicketCounter ให้เพิ่มจำนวนผู้เข้าชม
        verify(ticketCounter, times(1)).changeTicketStatus(true);
    }

    @Test
    void testCustomerEntry_DuplicateTicket_ShouldNotAddAgainOrUpdateCounter() {
        // Arrange: เข้าไปแล้วครั้งแรก
        gateCheckin.customerEntry(2002);

        // Act: สแกนตั๋วเดิมซ้ำ
        gateCheckin.customerEntry(2002);

        // Assert
        Set<Integer> checkedIn = gateCheckin.getPassengersOnBoard();
        assertEquals(1, checkedIn.size());

        // เรียก changeTicketStatus แค่ครั้งเดียวเท่านั้น (จากครั้งแรก)
        verify(ticketCounter, times(1)).changeTicketStatus(true);
    }

    @Test
    void testCustomerIsEligible_NewTicket_ShouldReturnTrue() {
        assertTrue(gateCheckin.customerIsEligible(3003));
    }

    @Test
    void testCustomerIsEligible_AlreadyCheckedIn_ShouldReturnFalse() {
        gateCheckin.customerEntry(4004);

        assertFalse(gateCheckin.customerIsEligible(4004));
    }

    @Test
    void testCustomerEntry_MultipleDifferentTickets_ShouldCheckInAll() {
        gateCheckin.customerEntry(5001);
        gateCheckin.customerEntry(5002);
        gateCheckin.customerEntry(5003);

        Set<Integer> checkedIn = gateCheckin.getPassengersOnBoard();
        assertEquals(3, checkedIn.size());
        verify(ticketCounter, times(3)).changeTicketStatus(true);
    }
}