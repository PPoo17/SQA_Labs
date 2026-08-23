package com.sqa.lab9_2;
/*
นายปฏิภาณ มะนิลทิพย์ 673380589-2 Sec 2
*/
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SeatReservationTest {

    @Mock
    private SeatDAO seatDAO;

    private SeatReservation seatReservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seatReservation = new SeatReservation(seatDAO);
    }

    @Test
    void testCheckSeatAvailability_SeatIsAvailable_ShouldReturnTrue() throws SQLException {
        // Arrange
        List<String> mockSeats = Arrays.asList("A1", "A2", "B3");
        when(seatDAO.fetchAvailableSeats()).thenReturn(mockSeats);

        // Act
        boolean result = seatReservation.checkSeatAvailability("A2");

        // Assert
        assertTrue(result);
        verify(seatDAO, times(1)).fetchAvailableSeats();
    }

    @Test
    void testCheckSeatAvailability_SeatIsNotAvailable_ShouldReturnFalse() throws SQLException {
        // Arrange
        List<String> mockSeats = Arrays.asList("A1", "A2", "B3");
        when(seatDAO.fetchAvailableSeats()).thenReturn(mockSeats);

        // Act
        boolean result = seatReservation.checkSeatAvailability("C5");

        // Assert
        assertFalse(result);
    }

    @Test
    void testCheckSeatAvailability_NoSeatsAvailable_ShouldReturnFalse() throws SQLException {
        // Arrange
        when(seatDAO.fetchAvailableSeats()).thenReturn(Collections.emptyList());

        // Act
        boolean result = seatReservation.checkSeatAvailability("A1");

        // Assert
        assertFalse(result);
    }

    @Test
    void testCheckSeatAvailability_DaoThrowsSQLException_ShouldPropagate() throws SQLException {
        // Arrange
        when(seatDAO.fetchAvailableSeats()).thenThrow(new SQLException("DB connection failed"));

        // Act & Assert
        assertThrows(SQLException.class, () -> seatReservation.checkSeatAvailability("A1"));
    }
}