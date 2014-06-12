/**
 * Created with IntelliJ IDEA.
 * User: sahuja
 * Date: 6/10/14
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class HVACSpy implements HVAC {

    boolean heat, cool, fan;
    float temp;

    @Override
    public void heat(boolean on) {
        this.heat = on;
    }

    @Override
    public void cool(boolean on) {
        this.cool = on;
    }

    @Override
    public void fan(boolean on) {
        this.fan = on;
    }

    @Override
    public float getTemp() {
        return temp;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
