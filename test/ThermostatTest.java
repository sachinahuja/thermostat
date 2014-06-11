import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: sahuja
 * Date: 6/10/14
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThermostatTest {

    private HVACSpy hvac;
    private Thermostat therm;

    @Before
    public void setUp() throws Exception {
        hvac = new HVACSpy();
        therm = new Thermostat(hvac);
    }

    @Test
    public void initializeThermostat_hasAllSystemsOff() throws Exception {
        assertAllSystemsOff();
    }

    @Test
    public void doNothingIfTempIs70() throws Exception {
        hvac.temp = 70.0f;
        therm.tic();
        assertAllSystemsOff();
    }

    @Test
    public void doNothingIfTempIs65() throws Exception {
        hvac.temp = 65.0f;
        therm.tic();
        assertAllSystemsOff();
    }

    @Test
    public void doNothingIfTempIs75() throws Exception {
        hvac.temp = 75.0f;
        therm.tic();
        assertAllSystemsOff();
    }

    @Test
    public void startCoolingIfTempIs76() throws Exception {
        hvac.temp = 76.0f;
        therm.tic();
        assertStates("hCF");

    }

    @Test
    public void startCoolingIfTempIs200() throws Exception {
        hvac.temp = 200.3f;
        therm.tic();
        assertStates("hCF");
    }

    @Test
    public void startHeatingIfTempIs64() throws Exception {
        hvac.temp = 64.0f;
        therm.tic();
        assertStates("HcF");
    }

    @Test
    public void startHeatingIfTempIs10() throws Exception {
        hvac.temp = 10.3f;
        therm.tic();
        assertStates("HcF");

    }

    @Test
    public void turnHeatOffIfTempIsBackToOver65() throws Exception {
        hvac.temp = 60.0f;
        therm.tic();
        hvac.temp = 66.0f;
        therm.tic();
        assertFalse(hvac.heat);
    }

    @Test
    public void tempBackOver65_willTurnHeatOffButKeepFanOnFor5Minutes() throws Exception {
        hvac.temp = 60.0f;
        therm.tic();
        hvac.temp = 66.0f;
        therm.tic();
        assertStates("hcF");
        therm.tic();
        therm.tic();
        therm.tic();
        assertStates("hcF");
        therm.tic();
        assertStates("hcF");
        therm.tic();
        assertStates("hcf");
    }

    @Test
    public void turnCoolingOffIfTempBackUnder75() throws Exception {
        hvac.temp = 76.0f;
        therm.tic();
        hvac.temp = 74.0f;
        therm.tic();
        assertStates("hcf");
    }

    @Test
    public void doNotStartCoolerFor3MinsAfterTurningOff() throws Exception {
        hvac.temp = 76.0f;
        therm.tic();
        hvac.temp = 74.0f;
        therm.tic();
        hvac.temp = 76.0f;
        therm.tic();
        assertStates("hcf");
        therm.tic();
        assertStates("hcf");
        therm.tic();
        assertStates("hCF");
    }

    private void assertStates(String states){
        if(states.charAt(0) == 'H')
            assertTrue(hvac.heat);
        if(states.charAt(0) == 'h')
            assertFalse(hvac.heat);
        if(states.charAt(1) == 'C')
            assertTrue(hvac.cool);
        if(states.charAt(1) == 'c')
            assertFalse(hvac.cool);
        if(states.charAt(2) == 'F')
            assertTrue(hvac.fan);
        if(states.charAt(2) == 'f')
            assertFalse(hvac.fan);



    }

    private void assertAllSystemsOff() {
        assertFalse(hvac.cool);
        assertFalse(hvac.heat);
        assertFalse(hvac.fan);
    }
}
