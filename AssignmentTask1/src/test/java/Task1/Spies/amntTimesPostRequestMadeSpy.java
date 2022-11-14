package Task1.Spies;

import Task1.Interfaces.amntTimesPostRequestMade;

public class amntTimesPostRequestMadeSpy implements amntTimesPostRequestMade {

    int numTimesPostRequestMade = 0;

    public int madePostRequests() {
        numTimesPostRequestMade++;
        return numTimesPostRequestMade;
    }
}
