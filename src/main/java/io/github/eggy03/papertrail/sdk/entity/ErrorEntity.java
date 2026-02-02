package io.github.eggy03.papertrail.sdk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorEntity {

    private int status;
    @NotNull private String error;
    @NotNull private String message;
    @NotNull private String timeStamp;
    @NotNull private String path;

}
