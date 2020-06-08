package kr.hs.dsm_scarfs.util;

import okhttp3.*;

import java.io.*;


public class EmailUtil {

    public static void sendMail(String email, String data) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"auth_code\":\""+data+"\",\n\t\"target\":\""+email+"\"\n}");
        Request request = new Request.Builder()
                .url("http://api.dsm-scarfs.hs.kr/t-bone/email")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
