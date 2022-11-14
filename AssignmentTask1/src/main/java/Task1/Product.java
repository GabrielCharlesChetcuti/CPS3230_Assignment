package Task1;

import Task1.Interfaces.CheckPageIsLoaded;

public class Product {

    int alertType;
    String heading;
    String description;
    String url;
    String imageUrl;
    String postedBy;
    int priceInCents;

    public Product(int alertType, String heading, String description, String url, String imageUrl,String postedBy, int priceInCents)
    {
        this.alertType = alertType;
        this.heading = heading;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.postedBy = postedBy;
        this.priceInCents = priceInCents;
    }

//    @Override
//    public String toString()
//    {
//        return description;
//    }
}
