package io.github.eggy03.papertrail.sdk.client;

import io.github.eggy03.papertrail.sdk.entity.MessageLogRegistrationEntity;
import io.github.eggy03.papertrail.sdk.entity.ErrorEntity;
import io.github.eggy03.papertrail.sdk.http.HttpServiceEngine;
import io.vavr.control.Either;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Optional;

/**
 * Client for managing message log registrations via the PaperTrail API.
 */
@RequiredArgsConstructor
@Slf4j
public class MessageLogRegistrationClient {

    private final String baseUrl;

    /**
     * Registers a guild for message logging.
     *
     * @param guildId   the Discord guild ID (must not be {@code null})
     * @param channelId the Discord channel ID where message logs should be sent (must not be {@code null})
     * @return {@code true} if the registration succeeded, {@code false} otherwise
     */
    public boolean registerGuild(@NonNull String guildId, @NonNull String channelId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Either<ErrorEntity, MessageLogRegistrationEntity> responseBody = HttpServiceEngine.makeRequestWithBody(
                HttpMethod.POST,
                baseUrl+"api/v1/log/message",
                headers,
                new MessageLogRegistrationEntity(guildId, channelId),
                MessageLogRegistrationEntity.class
        );

        // log in case of failure
        responseBody.peekLeft(failure -> log.debug("Failed to register guild for message logging.\nAPI Response: {}", failure));

        return responseBody.isRight();
    }


    /**
     * Retrieves the message log registration for a guild, if one exists.
     *
     * @param guildId the Discord guild ID (must not be {@code null})
     * @return an {@link Optional} containing the registration if found, or empty if not registered
     */
    @NotNull
    public Optional<MessageLogRegistrationEntity> getRegisteredGuild (@NonNull String guildId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Either<ErrorEntity, MessageLogRegistrationEntity> response = HttpServiceEngine.makeRequest(
                HttpMethod.GET,
                baseUrl+"api/v1/log/message/"+guildId,
                headers,
                MessageLogRegistrationEntity.class
        );

        // in case of error entity, log it
        response.peekLeft(error -> log.debug("No guild of the ID: {} is registered.\nAPI Response: {}", guildId, error));

        // in case of success, return the MessageLogRegistrationEntity object or empty optional
        return response.map(Optional::of).getOrElse(Optional.empty());
    }

    /**
     * Deletes the message log registration for a guild.
     *
     * @param guildId the Discord guild ID (must not be {@code null})
     * @return {@code true} if the deletion succeeded, {@code false} otherwise
     */
    public boolean deleteRegisteredGuild (@NonNull String guildId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Either<ErrorEntity, Void> responseBody = HttpServiceEngine.makeRequest(
                HttpMethod.DELETE,
                baseUrl +"api/v1/log/message/"+guildId,
                headers,
                Void.class
        );

        responseBody.peekLeft(failure -> log.debug("Failed to delete registered guild for message logging.\nAPI Response: {}", failure));

        return responseBody.isRight();
    }
}
