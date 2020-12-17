package com.imgarena;

import com.amazonaws.services.ivs.AbstractAmazonIVS;
import com.amazonaws.services.ivs.AmazonIVS;
import com.amazonaws.services.ivs.AmazonIVSClientBuilder;
import com.amazonaws.services.ivs.model.PutMetadataRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

public class IVSService extends AbstractAmazonIVS {

    private static final String CHANNEL_ARN = "arn:aws:ivs:eu-west-1:154882459970:channel/19gzIcV0doJY";

    private static final Logger LOGGER = LogManager.getLogger(IVSService.class);
    private static final AmazonIVS amazonIVS = AmazonIVSClientBuilder.defaultClient();

    public void send(String twitterJson) {
        LOGGER.info("Sending [{}] to IVS", twitterJson);
        LOGGER.info("Total length {} bytes", () -> twitterJson.getBytes(StandardCharsets.UTF_8).length);


        final var putMetadataRequest = new PutMetadataRequest()
                .withChannelArn(CHANNEL_ARN)
                .withMetadata(twitterJson);

        final var putMetadataResult = amazonIVS.putMetadata(putMetadataRequest);
        LOGGER.info("PutMetadata status result: {}, headers: {}",
                putMetadataResult.getSdkHttpMetadata().getHttpStatusCode(),
                putMetadataResult.getSdkHttpMetadata().getAllHttpHeaders());
    }
}
