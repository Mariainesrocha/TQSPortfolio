package ui;

import euromillions.CuponEuromillions;
import euromillions.Dip;
import euromillions.EuromillionsDraw;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DemoMain {

    /**
     * demonstrates a client for ramdom euromillions bets
     */
    private static final Logger LOGGER = Logger.getLogger( DemoMain.class.getName() );

    public static void main(String[] args) {

        // played sheet
        CuponEuromillions thisWeek = new CuponEuromillions();
        LOGGER.log(Level.INFO, "Betting with three random bets...");
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());

        // simulate a random draw
        EuromillionsDraw draw = EuromillionsDraw.generateRandomDraw();

        //report results
        LOGGER.log(Level.INFO,"You played:");
        LOGGER.log(Level.INFO,thisWeek.format());

        LOGGER.log(Level.INFO, "Draw results:");
        LOGGER.log(Level.INFO, draw.getDrawResults().format());

        LOGGER.log(Level.INFO,"Your score:");
        for (Dip dip : draw.findMatches(thisWeek)) {
            LOGGER.log(Level.INFO, dip.format());
        }
    }
}
