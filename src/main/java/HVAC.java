/**
 * Created with IntelliJ IDEA.
 * User: sahuja
 * Date: 6/10/14
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HVAC {

    public void heat(boolean on);
    public void cool(boolean on);
    public void fan(boolean on);
    public float getTemp();
}
