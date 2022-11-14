package Task1.Spies;

import Task1.Interfaces.amntTimesOpened;

public class amntTimesOpenedSpy implements amntTimesOpened {

    int numTimesPageIsOpened = 0;

    public int timeOpened() {
        numTimesPageIsOpened++;
        return numTimesPageIsOpened;
    }
}
