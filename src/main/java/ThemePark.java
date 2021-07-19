import attractions.Attraction;
import behaviours.IReviewed;
import behaviours.ISecurity;
import people.Visitor;
import stalls.Stall;

import java.util.ArrayList;
import java.util.HashMap;

public class ThemePark {

    private ArrayList<Attraction> attractions;
    private ArrayList<Stall> stalls;

    public ThemePark(ArrayList<Attraction> attractions, ArrayList<Stall> stalls) {
        this.attractions = attractions;
        this.stalls = stalls;
    }

    public ArrayList<IReviewed> getAllReviewed(){
        ArrayList reviews = new ArrayList();
        for(Attraction attraction : attractions){
            reviews.add(attraction);
        }
        for(Stall stall : stalls){
            reviews.add(stall);
        }
        return reviews;
    }

    public void visit(Visitor visitor, Attraction attraction){
        attraction.incrementVisitCount();
        visitor.addVisitedAttraction(attraction);
    }

    public HashMap<String, Integer> getRatings(){
        HashMap reviewsHash = new HashMap<String, Integer>();
        for(IReviewed review: getAllReviewed()){
            reviewsHash.put(review.getName(), review.getRating());
        }
        return reviewsHash;
    }

    public ArrayList<IReviewed> getAllowed(Visitor visitor){
        ArrayList allowed = new ArrayList<IReviewed>();
        for(IReviewed reviewed: getAllReviewed()){
            if(reviewed instanceof ISecurity){
                if(((ISecurity) reviewed).isAllowedTo(visitor)){
                    allowed.add(reviewed);
                };
            }
            else { allowed.add(reviewed);}
        }
        return allowed;
    }
}
