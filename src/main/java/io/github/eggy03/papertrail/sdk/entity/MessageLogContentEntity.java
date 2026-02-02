package io.github.eggy03.papertrail.sdk.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Getter
@Builder(toBuilder = true)
public class MessageLogContentEntity {

    @NotNull
    final String messageId;

    @NotNull
    final String messageContent;

    @NotNull
    final String authorId;
}
