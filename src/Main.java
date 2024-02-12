/**
 *
 * @author sema_
 */

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();
        
        GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,"led",PinState.LOW);
        GpioPinDigitalInput button = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,"button",PinPullResistance.PULL_DOWN) ;
        led.setShutdownOptions(true);
        button.setShutdownOptions(true);
        
        button.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println("State: " + event.getState());
                
            }
        });
        
        while (true) {
            Thread.sleep(500);
            led.high();
            Thread.sleep(500);
            led.low();
            Thread.sleep(500);
        }
    }
    
}
