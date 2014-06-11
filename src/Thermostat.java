/**
 * Created with IntelliJ IDEA.
 * User: sahuja
 * Date: 6/10/14
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Thermostat {

    private HVAC hvac;
    private boolean heat;
    private int heatCounter, coolCounter;


    public Thermostat(HVAC hvac) {
        this.hvac = hvac;
    }

    public void tic(){
        if(hvac.getTemp() > 75.0f){
            startCooler();
        } else if (hvac.getTemp() < 65.0f)
            startHeater();
        else {
            stopHeater();
            stopCooler();
        }

        if(heatCounter > 0) {
            heatCounter--;
            if (heatCounter == 0)
                stopFan();
        }

    }

    private void startCooler() {
        if(coolCounter > 0)
            coolCounter--;

        if(coolCounter == 0){
            hvac.cool(true);
            hvac.fan(true);
        }
    }

    private void stopCooler() {
            hvac.cool(false);
            stopFan();
            this.coolCounter = 3;
    }

    private void stopFan() {
        if(heatCounter == 0){
            hvac.fan(false);
        }
    }


    private void startHeater(){
        this.heat = true;
        hvac.heat(true);
        hvac.fan(true);
    }

    private void stopHeater(){

        if(this.heat && heatCounter == 0){
            this.heat = false;
            hvac.heat(this.heat);
            this.heatCounter = 6;
        }
    }




}
