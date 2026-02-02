package io.github.eggy03.papertrail.sdk.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

/**
 * Represents a structured error response returned by the PaperTrail API.
 */
@Getter
@Builder(toBuilder = true)
public class ErrorEntity {

    /**
     * The HTTP status code associated with the error.
     */
    private final int status;

    /**
     * A short error identifier or type, often the underlying exception class
     */
    @NotNull
    private final String error;

    /**
     * A human-readable error message describing what went wrong.
     */
    @NotNull
    private final String message;

    /**
     * The timestamp indicating when the error occurred.
     */
    @NotNull
    private final String timeStamp;

    /**
     * The request path that caused the error.
     */
    @NotNull
    private final String path;

    /**
     * Creates a new {@code ErrorEntity}.
     *
     * @param status    the HTTP status code
     * @param error     a short error identifier or type (must not be {@code null})
     * @param message   a human-readable error message (must not be {@code null})
     * @param timeStamp the timestamp of the error occurrence (must not be {@code null})
     * @param path      the request path that caused the error (must not be {@code null})
     */
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ErrorEntity(@JsonProperty("status") int status,
                       @JsonProperty("error") @NonNull String error,
                       @JsonProperty("message") @NonNull String message,
                       @JsonProperty("timeStamp") @NonNull String timeStamp,
                       @JsonProperty("path") @NonNull String path
    ) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timeStamp = timeStamp;
        this.path = path;
    }
}
