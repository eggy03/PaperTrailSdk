package io.github.eggy03.papertrail.sdk.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

/**
 * Represents a registration entry for message logging within a Discord guild.
 * <p>
 * This entity maps a guild ID to the channel ID where message logs
 * (such as edits and deletions) should be sent. It is immutable and
 * safe for use across threads.
 * </p>
 */
@Getter
@Builder(toBuilder = true)
public class MessageLogRegistrationEntity {

    /**
     * The unique ID of the Discord guild.
     */
    @NotNull
    private final String guildId;

    /**
     * The unique ID of the Discord channel where message logs will be posted.
     */
    @NotNull
    private final String channelId;

    /**
     * Creates a new {@code MessageLogRegistrationEntity}.
     *
     * @param guildId   the Discord guild ID (must not be {@code null})
     * @param channelId the Discord channel ID (must not be {@code null})
     */
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MessageLogRegistrationEntity(@JsonProperty("guildId") @NonNull String guildId,
                                        @JsonProperty("channelId") @NonNull String channelId) {
        this.guildId = guildId;
        this.channelId = channelId;
    }
}
