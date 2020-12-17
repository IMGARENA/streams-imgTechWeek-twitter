package com.imgarena;

import twitter4j.*;

public abstract class AbstractStatusListener implements StatusListener {

    private static final Logger LOGGER = Logger.getLogger(AbstractStatusListener.class);

    final Twitter twitter;


    public AbstractStatusListener(Twitter twitter) {
        this.twitter = twitter;
    }

    void reply(Status status, String reply) {
        LOGGER.info("Replying ====> " + reply);
        StringBuilder update = new StringBuilder(reply);
        update.append(" @").append(status.getUser().getScreenName());

/*        UserMentionEntity[] userMentionEntities = status.getUserMentionEntities();
        String mentions = Arrays.stream(userMentionEntities)
                .map(UserMentionEntity::getScreenName)
                .map(name -> "@" + name)
                .collect(Collectors.joining(" ", " ", ""));
        update.append(mentions);*/

        StatusUpdate statusUpdate = new StatusUpdate(update.toString());
        statusUpdate.setInReplyToStatusId(status.getId());
        statusUpdate.autoPopulateReplyMetadata(true);
        try {
            twitter.updateStatus(statusUpdate);
        } catch (TwitterException e) {
            LOGGER.warn("Twitter exception when trying to reply", e);
        }
    }


    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int i) {
        LOGGER.warn("Track Limitation happened: " + i);
    }

    @Override
    public void onScrubGeo(long l, long l1) {

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {
        LOGGER.warn("Stall happened : ", stallWarning.getMessage());

    }

    @Override
    public void onException(Exception e) {
        LOGGER.warn("Exception happened", e);
    }
}
