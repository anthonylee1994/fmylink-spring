package com.spring.fmylinkspring.photos.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author anthonylee
 */
@Service
public class PhotoService {
    String url(MultipartFile image) throws IOException {
        var client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", image.getName(), RequestBody.create(image.getBytes()))
                .build();

        Request request = new Request.Builder()
                .url("https://api.na.cx/upload")
                .post(requestBody)
                .header("Accept", "application/json")
                .header("Origin", "https://lihkg.com")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            ResponseBody responseBody = response.body();

            if (responseBody != null) {
                return parseUrlFromJson(responseBody.string());
            } else {
                throw new IOException("Response body is null");
            }

        } catch (Exception e) {
            throw new BadRequestException(e);
        }
    }

    public String parseUrlFromJson(String jsonResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        return jsonNode.get("url").asText();
    }
}
