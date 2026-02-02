package io.github.eggy03.papertrail.sdk.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

/**
 * Represents a simplified version of Discord's message object, stripped of all metadata except message and author ID.
 * <p>
 * Immutable and thread-safe
 * </p>
 */
@Getter
@Builder(toBuilder = true)
public class MessageLogContentEntity {

    /**
     * The unique ID of the Discord message.
     */
    @NotNull
    private String messageId;

    /**
     * The plain-text content of the message.
     */
    @NotNull
    private String messageContent;

    /**
     * The unique ID of the author of the message.
     */
    @NotNull
    private String authorId;

    /**
     * Creates a new {@code MessageLogContentEntity}.
     *
     * @param messageId      the Discord message ID (must not be {@code null})
     * @param messageContent the message content in plain text (must not be {@code null})
     * @param authorId       the Discord user ID of the message author (must not be {@code null})
     */
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MessageLogContentEntity(@JsonProperty("messageId") @NonNull String messageId,
                                   @JsonProperty("messageContent") @NonNull String messageContent,
                                   @JsonProperty("authorId") @NonNull String authorId) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.authorId = authorId;
    }
}
