package io.github.eggy03.papertrail.sdk.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Getter
@Builder(toBuilder = true)
public class ErrorEntity {

    final int status;
    @NotNull final String error;
    @NotNull final String message;
    @NotNull final String timeStamp;
    @NotNull final String path;

}
