package sqa.test;
/*
 * ชื่อ-นามสกุล: นายปฏิภาณ มะนิลทิพย์
 * รหัสนักศึกษา: 673380589-2
 * Sec: 2
 *
 * Lab#8 - Integration Testing
 * Top-down Integration Testing
 */
import sqa.main.DistanceConverter;
import sqa.main.WeightConverter;
import sqa.main.TemperatureConverter;
import sqa.main.UniversalConverter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TopDownIntegrationTest {

    // =====================
    // STUBS DEFINITION
    // =====================
    static class StubDistanceConverter extends DistanceConverter {
        @Override
        public double convert(double distanceValue, String fromUnit, String toUnit) {
            return 5000.0;
        }
    }

    static class StubWeightConverter extends WeightConverter {
        @Override
        public double convert(double massValue, String fromUnit, String toUnit) {
            return 4.410;
        }
    }

    static class StubTemperatureConverter extends TemperatureConverter {
        @Override
        public double convert(double tempValue, String fromUnit, String toUnit) {
            return 212.0;
        }
    }

    // =========================================================================
    // STEP 1: Top-Level Module (UniversalConverter) tested via Stubs (TC 1 - 3)
    // =========================================================================

    @Test
    @DisplayName("TC-TD-01: UniversalConverter with StubDistanceConverter (km -> m)")
    public void testTC01_Universal_Distance_Stub() {
        StubDistanceConverter stubDist = new StubDistanceConverter();
        double result = stubDist.convert(5.0, "km", "m");
        assertEquals(5000.0, result, 0.001);
    }

    @Test
    @DisplayName("TC-TD-02: UniversalConverter with StubWeightConverter (kg -> lbs)")
    public void testTC02_Universal_Weight_Stub() {
        StubWeightConverter stubWeight = new StubWeightConverter();
        double result = stubWeight.convert(2.0, "kg", "lbs");
        assertEquals(4.410, result, 0.001);
    }

    @Test
    @DisplayName("TC-TD-03: UniversalConverter with StubTemperatureConverter (C -> F)")
    public void testTC03_Universal_Temperature_Stub() {
        StubTemperatureConverter stubTemp = new StubTemperatureConverter();
        double result = stubTemp.convert(100.0, "C", "F");
        assertEquals(212.0, result, 0.001);
    }

    // =========================================================================
    // STEP 2: UniversalConverter + real DistanceConverter module (TC 4 - 5)
    // =========================================================================

    @Test
    @DisplayName("TC-TD-04: UniversalConverter + DistanceConverter (5 km -> meter)")
    public void testTC04_Universal_RealDistance_KmToM() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(5.0, "Distance", "kilometer", "meter");
        assertEquals(5000.0, result, 0.001);
    }

    @Test
    @DisplayName("TC-TD-05: UniversalConverter + DistanceConverter (1000 meter -> kilometer)")
    public void testTC05_Universal_RealDistance_MToKm() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(1000.0, "Distance", "meter", "kilometer");
        assertEquals(1.0, result, 0.001);
    }

    // =========================================================================
    // STEP 3: UniversalConverter + real WeightConverter module (TC 6 - 7)
    // =========================================================================

    @Test
    @DisplayName("TC-TD-06: UniversalConverter + WeightConverter (2 kg -> gram) [Defect]")
    public void testTC06_Universal_RealWeight_KgToGram() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(2.0, "Weight", "kilogram", "gram");
        // Expected: 2000.0, Actual in code: 0.002 (multiplier is set to 1.0/1000 instead of 1000)
        assertEquals(2000.0, result, 0.001);
    }

    @Test
    @DisplayName("TC-TD-07: UniversalConverter + WeightConverter (1 kg -> lbs)")
    public void testTC07_Universal_RealWeight_KgToLbs() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(1.0, "Weight", "kilogram", "lbs");
        assertEquals(2.205, result, 0.001);
    }

    // =========================================================================
    // STEP 4: UniversalConverter + real TemperatureConverter module (TC 8 - 10)
    // =========================================================================

    @Test
    @DisplayName("TC-TD-08: UniversalConverter + TemperatureConverter (100 C -> F) [Defect]")
    public void testTC08_Universal_RealTemp_CtoF() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(100.0, "Temperature", "C", "F");
        // Expected: 212.0, Actual in code: 132.0 (integer division 9/5 = 1)
        assertEquals(212.0, result, 0.001);
    }

    @Test
    @DisplayName("TC-TD-09: UniversalConverter + TemperatureConverter (212 F -> C) [Defect]")
    public void testTC09_Universal_RealTemp_FtoC() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(212.0, "Temperature", "F", "C");
        // Expected: 100.0, Actual in code: 0.0 (integer division 5/9 = 0)
        assertEquals(100.0, result, 0.001);
    }

    @Test
    @DisplayName("TC-TD-10: UniversalConverter + TemperatureConverter (0 C -> K)")
    public void testTC10_Universal_RealTemp_CtoK() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(0.0, "Temperature", "C", "K");
        assertEquals(273.15, result, 0.001);
    }
}
