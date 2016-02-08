/*
 * Author: Brendan McDonald 
 */
 
/*
 * The WeatherStationSwing class is an observer of the WeatherStation that,
 * when it receives an update message, prints the temperature
 * in Celsius and Kelvin.
 */
import java.util.Observer ;
import java.util.Observable ;

public class WeatherStationSwing implements Observer {
    private final SwingUI sui;
    private final WeatherStation station ;
    
    public WeatherStationSwing(SwingUI swingArr, WeatherStation station){
        this.sui = swingArr;
        this.station = station ;
        this.station.addObserver(this) ;
    }

     public void update(Observable obs, Object ignore) {
     /*
      * Check for spurious updates from unrelated objects.
      */
     if( station != obs ) {
         return ;
     }
     /*
      * Retrieve and add temperatures to the proper JLabels.
      */
     this.sui.setKelvinJLabel(station.getKelvin());
     this.sui.setCelsiusJLabel(station.getCelsius());
 }
   /*
    * Main Class
    * Instantiates the GUI, the Observable Weather Station, and passes those objects
    * to the WeatherStation Swing class then starts the thread from the Weatherstation object.
    */
 
    public static void main(String[] args){
        SwingUI swingUI = new SwingUI();
        WeatherStation ws = new WeatherStation();
        WeatherStationSwing wss = new WeatherStationSwing(swingUI, ws);
        Thread t = new Thread(ws);
        t.start();
    }
}