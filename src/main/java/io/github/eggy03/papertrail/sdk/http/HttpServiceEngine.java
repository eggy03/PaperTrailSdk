package io.github.eggy03.papertrail.sdk.http;

import io.github.eggy03.papertrail.sdk.entity.ErrorEntity;
import io.vavr.control.Either;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.time.Instant;

@Slf4j
@UtilityClass
public class HttpServiceEngine {

    private static final RestClient client = RestClient.builder().build();

    public static <S> Either<ErrorEntity, S> makeRequest (
            @NotNull HttpMethod httpMethod,
            @NotNull String url,
            @NotNull HttpHeaders headers,
            @NotNull Class<S> successResponseClass) {

        try {
            S body = client.method(httpMethod)
                    .uri(URI.create(url))
                    .headers(h-> h.addAll(headers))
                    .retrieve()
                    .toEntity(successResponseClass)
                    .getBody();

            return Either.right(body);
        } catch (HttpClientErrorException e) {
            log.debug("Client error when calling {} {}: {}", httpMethod, url, e.getMessage(), e);
            ErrorEntity error = e.getResponseBodyAs(ErrorEntity.class);
            return Either.left(error);
        } catch (HttpServerErrorException e) {
            log.error("Server error when calling {} {}: {}", httpMethod, url, e.getMessage(), e);
            ErrorEntity error = e.getResponseBodyAs(ErrorEntity.class);
            return Either.left(error);
        } catch (ResourceAccessException e) {
            log.error("Resource access error when calling {} {}: {}", httpMethod, url, e.getMessage(), e);
            ErrorEntity error =  new ErrorEntity(503, "API Unreachable", e.getMessage(), Instant.now().toString(), url);
            return Either.left(error);
        }
    }

    public static <S> Either<ErrorEntity, S> makeRequestWithBody (
            @NotNull HttpMethod httpMethod,
            @NotNull String url,
            @NotNull HttpHeaders headers,
            @NotNull Object requestBody,
            @NotNull Class<S> successResponseClass) {

        try {
            S body = client.method(httpMethod)
                    .uri(URI.create(url))
                    .headers(h-> h.addAll(headers))
                    .body(requestBody)
                    .retrieve()
                    .toEntity(successResponseClass)
                    .getBody();

            return Either.right(body);

        } catch (HttpClientErrorException e) {
            log.debug("Client error when calling {} {}: {}", httpMethod, url, e.getMessage(), e);
            ErrorEntity error = e.getResponseBodyAs(ErrorEntity.class);
            return Either.left(error);
        } catch (HttpServerErrorException e) {
            log.error("Server error when calling {} {}: {}", httpMethod, url, e.getMessage(), e);
            ErrorEntity error = e.getResponseBodyAs(ErrorEntity.class);
            return Either.left(error);
        } catch (ResourceAccessException e) {
            log.error("Resource access error when calling {} {}: {}", httpMethod, url, e.getMessage(), e);
            ErrorEntity error =  new ErrorEntity(503, "API Unreachable", e.getMessage(), Instant.now().toString(), url);
            return Either.left(error);
        }
    }
}
