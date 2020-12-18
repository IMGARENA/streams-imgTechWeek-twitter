package com.imgarena;

import com.amazonaws.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import twitter4j.*;

public class TechWeek {

    private static final Logger LOGGER = LogManager.getLogger(TechWeek.class);

    public static void main(String[] args) {

        var techWeek = new TechWeek();
        techWeek.start();
    }

    private void start() {
        TwitterStream twitterStream = TwitterStreamFactory.getSingleton();
        var twitter = TwitterFactory.getSingleton();

        var ivsService = new IVSService();

        var hashtag = System.getenv("twitter.hashtag");
        if (StringUtils.isNullOrEmpty(hashtag)) {
            hashtag = "#ufc";
        }
        LOGGER.info("Hashtag set to: {}", hashtag);


        twitterStream.addListener(new TechWeekListener(twitter, ivsService));
        FilterQuery filterQuery = new FilterQuery().language("en").track(hashtag);
        twitterStream.filter(filterQuery);
    }
}
