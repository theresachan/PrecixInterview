package com.example.weatherprecixjava;

import org.junit.Test;
import static org.junit.Assert.*;

public class InputValidationTest {

    @Test
    public void testValidCoordinates() {
        String validLatitude = "48.137";
        String validLongitude = "11.576";
        assertTrue(isValidInput(validLatitude, validLongitude));
    }

    @Test
    public void testEmptyCoordinates() {
        String emptyLatitude = "";
        String validLongitude = "11.576";
        assertFalse(isValidInput(emptyLatitude, validLongitude));
    }

    @Test
    public void testInvalidCoordinates() {
        String invalidLatitude = "abc";
        String validLongitude = "11.576";
        assertFalse(isValidInput(invalidLatitude, validLongitude));
    }

    private boolean isValidInput(String latitude, String longitude) {
        try {
            Double.parseDouble(latitude);
            Double.parseDouble(longitude);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
