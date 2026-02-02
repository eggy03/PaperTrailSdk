package io.github.eggy03.papertrail.sdk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageLogContentEntity {

    @NotNull
    private String messageId;

    @NotNull
    private String messageContent;

    @NotNull
    private String authorId;
}
