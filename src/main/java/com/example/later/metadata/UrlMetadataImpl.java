package com.example.later.metadata;

import lombok.Builder;

import java.time.LocalDateTime;

@lombok.Value
@Builder(toBuilder = true)
public class UrlMetadataImpl implements UrlMetadataRetriever.UrlMetadata {
    String normalUrl;
    String resolvedUrl;
    String mimeType;
    String title;
    boolean hasImage;
    boolean hasVideo;
    LocalDateTime dateResolved;
}
