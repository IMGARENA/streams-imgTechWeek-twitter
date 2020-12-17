package com.imgarena;

import twitter4j.*;

public class TechWeek {

    public static void main(String[] args) {

        var techWeek = new TechWeek();
        techWeek.start();
    }

    private void start() {
        TwitterStream twitterStream = TwitterStreamFactory.getSingleton();
        var twitter = TwitterFactory.getSingleton();

        var ivsService = new IVSService();

        twitterStream.addListener(new TechWeekListener(twitter, ivsService));
        FilterQuery filterQuery = new FilterQuery().language("en").track("#ufc");
        twitterStream.filter(filterQuery);
    }
}
