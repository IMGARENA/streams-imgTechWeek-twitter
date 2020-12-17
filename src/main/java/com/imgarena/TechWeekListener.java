package com.imgarena;

import com.fasterxml.jackson.jr.ob.JSON;
import com.imgarena.model.TwitterDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import twitter4j.Status;
import twitter4j.Twitter;

import java.io.IOException;

public class TechWeekListener  extends AbstractStatusListener {

    private static final Logger LOGGER = LogManager.getLogger(TechWeekListener.class);

    private final IVSService ivsService;

    public TechWeekListener(Twitter twitter, IVSService ivsService) {
        super(twitter);
        this.ivsService = ivsService;
    }


    public void onStatus(Status status) {
        String text = status.getText();
        LOGGER.info("From: {} ,Text: {}", status.getUser().getScreenName(), text);
        var twitterDTO = new TwitterDTO(status.getUser().getScreenName(), status.getText(), status.getUser().getMiniProfileImageURL());
        try {
            ivsService.send(JSON.std.asString(twitterDTO));
        } catch (IOException e) {
            LOGGER.atWarn().withThrowable(e).log("Error converting {} to JSON", twitterDTO);
        }
    }
}
