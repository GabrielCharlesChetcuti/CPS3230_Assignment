package Task1.Stubs;

import Task1.Interfaces.CheckPageIsLoaded;


public class PageIsNotLoaded implements CheckPageIsLoaded {
    public boolean isPageLoaded()
    {
        return false;
    }
}