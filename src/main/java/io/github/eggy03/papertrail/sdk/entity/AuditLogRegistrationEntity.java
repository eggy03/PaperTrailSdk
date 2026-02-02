package io.github.eggy03.papertrail.sdk.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

/**
 * Represents a registration entry for audit logging within a Discord guild.
 * <p>
 * This entity maps a guild ID to the channel ID where audit log events
 * should be sent. It is immutable and safe to use across threads.
 * </p>
 */
@Getter
@Builder(toBuilder = true)
public class AuditLogRegistrationEntity {

    /**
     * The unique ID of the Discord guild.
     */
    @NotNull
    private final String guildId;

    /**
     * The unique ID of the Discord channel where audit logs will be posted.
     */
    @NotNull
    private final String channelId;

    /**
     * Creates a new {@code AuditLogRegistrationEntity}.
     *
     * @param guildId   the Discord guild ID (must not be {@code null})
     * @param channelId the Discord channel ID (must not be {@code null})
     */
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AuditLogRegistrationEntity(@JsonProperty("guildId") @NonNull String guildId,
                                      @JsonProperty("channelId") @NonNull String channelId) {
        this.guildId = guildId;
        this.channelId = channelId;
    }
}