package io.github.eggy03.papertrail.sdk.entity;

import lombok.Data;
import lombok.Getter;
import org.jspecify.annotations.Nullable;

@Data
public class HealthEntity {

    @Nullable String status;
    @Nullable Components components;

    @Getter
    public static class Components {

        @Nullable Database db;
        @Nullable Ping ping;
        @Nullable Redis redis;
        @Nullable Ssl ssl;

        @Data
        public static class Database {
            @Nullable String status;
        }

        @Data
        public static class Ping {
            @Nullable String status;
        }

        @Data
        public static class Redis {
            @Nullable String status;
        }

        @Data
        public static class Ssl {
            String status;
        }
    }
}
