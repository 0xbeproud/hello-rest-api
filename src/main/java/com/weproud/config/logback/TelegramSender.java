package com.weproud.config.logback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.base.MoreObjects;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Arrays;

/**
 * Logan. k
 */
@Slf4j
@Component
public class TelegramSender {

    private static final String SEND_MESSAGE_API_URL = "https://api.telegram.org/bot%s/sendMessage";

    @Value("${telegram.bot-token}")
    private String botToken;

    @Value("${telegram.chat-id}")
    private String chatId;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    private HttpHeaders headers;

    public TelegramSender() {
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public void sendAsync(final String text) throws JsonProcessingException {
        String sendData = this.objectMapper.writeValueAsString(new SendData(this.chatId, text));
        HttpEntity request = new HttpEntity(sendData, this.headers);
        ListenableFuture<ResponseEntity<String>> future = this.asyncRestTemplate.exchange(String.format(SEND_MESSAGE_API_URL, botToken),
                HttpMethod.POST, request, String.class);

        future.addCallback(
                result -> log.debug("result: {}", result),
                ex -> log.debug("ex: {}", ex.getStackTrace())
        );

    }

    @Getter
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public static class SendData {
        private String chatId;
        private String text;

        public SendData(final String chatId, final String text) {
            this.chatId = chatId;
            this.text = text;
        }
    }

    @Getter
    @Builder
    public static class ExceptionMessage {
        private String id;
        private String[] profiles;
        private String method;
        private String path;
        private String query;
        private String token;
        private String exTrace;

        @Override
        public String toString() {
            return "id: " + id + "\n" +
                    "profiles: " + Arrays.toString(profiles) + "\n" +
                    "method: " + method + "\n" +
                    "path: " + path + "\n" +
                    "query: " + query + "\n" +
                    "token: " + token + "\n\n" + exTrace;
        }

        public String toLog() {
            return MoreObjects.toStringHelper(this)
                    .toString();
        }
    }
}
