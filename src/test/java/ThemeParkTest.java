import attractions.*;
import org.junit.Before;
import org.junit.Test;
import people.Visitor;
import stalls.*;

import javax.smartcardio.ATR;
import java.util.ArrayList;
import java.util.concurrent.locks.StampedLock;

import static org.junit.Assert.assertEquals;

public class ThemeParkTest {

    ThemePark themePark;
    Dodgems dodgems;
    Park park;
    Playground playground;
    RollerCoaster rollerCoaster;
    CandyflossStall candyflossStall;
    IceCreamStall iceCreamStall;
    TobaccoStall tobaccoStall;
    Visitor visitor;


    @Before
    public void before(){
        visitor = new Visitor(18, 150, 200);
        dodgems = new Dodgems("The Dodgems", 6);
        park = new Park("Nice Park", 7);
        playground = new Playground("FunLand", 5);
        rollerCoaster = new RollerCoaster("The Big One", 10);
        candyflossStall = new CandyflossStall("Tasty Floss", "Mr. Candy", 8, ParkingSpot.A1);
        iceCreamStall = new IceCreamStall("Creamy", "Mrs. Ice Cream", 7, ParkingSpot.A4);
        tobaccoStall = new TobaccoStall("Smokes Place", "Mr. Marlboro", 6, ParkingSpot.B1);

        ArrayList attractions = new ArrayList<Attraction>();
        ArrayList stalls = new ArrayList<Stall>();

        attractions.add(dodgems);
        attractions.add(park);
        attractions.add(playground);
        attractions.add(rollerCoaster);

        stalls.add(candyflossStall);
        stalls.add(iceCreamStall);
        stalls.add(tobaccoStall);

        themePark = new ThemePark(attractions, stalls);
    }

    @Test
    public void canGetAllReviewed(){
        assertEquals(7, themePark.getAllReviewed().size());
    }

    @Test
    public void attractionAddedToVisited(){
        visitor.addVisitedAttraction(park);
        assertEquals(1, visitor.getVisitedAttractions().size());
    }

    @Test
    public void canVisit(){
        themePark.visit(visitor, park);
        assertEquals(1, park.getVisitCount());
        assertEquals(1, visitor.getVisitedAttractions().size());
    }

    @Test
    public void canGetReviewsHash(){
        themePark.getRatings();
        assertEquals(6, themePark.getRatings().get("The Dodgems").intValue());
    }

    @Test
    public void canGetListOfAllowed(){
        themePark.getAllowed(visitor);
        assertEquals(6, themePark.getAllowed(visitor).size());
    }

}
