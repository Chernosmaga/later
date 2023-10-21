package com.example.later.metadata;

import java.time.LocalDateTime;

public interface UrlMetadataRetriever {

    public UrlMetadata retrieve(String urlString);

    interface UrlMetadata {
        String getNormalUrl();
        String getResolvedUrl();
        String getMimeType();
        String getTitle();
        boolean isHasImage();
        boolean isHasVideo();
        LocalDateTime getDateResolved();
    }

}