package com.example.spotserver.snsLogin;

import com.example.spotserver.domain.Member;
import com.example.spotserver.domain.MemberType;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class KakaoApi {

    private static final String getMemberInfoUrl = "https://kapi.kakao.com/v2/user/me";
    private static final String getTokenInfoUrl = "https://kapi.kakao.com/v1/user/access_token_info";

    public static Member getMemberInfo(String access_token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + access_token);
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        String propertyKeys = "[\"kakao_account.profile\"]";

        URI uri = UriComponentsBuilder.fromUriString(getMemberInfoUrl)
                    .queryParam("property_keys", propertyKeys)
                    .build()
                    .toUri();

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

        String responseBody = response.getBody();;

        JSONObject jsonObject = new JSONObject(responseBody);
        Long sns_id = jsonObject.getLong("id");

        JSONObject profile = jsonObject.getJSONObject("kakao_account").getJSONObject("profile");
        String name = profile.getString("nickname");
        String profile_image_url = profile.getString("thumbnail_image_url");

        System.out.println("sns_id = " + sns_id);
        System.out.println("name = " + name);
        System.out.println("profile_image_url = " + profile_image_url);

        Member member = new Member();
        member.setSnsId(sns_id);
        member.setName(name);
        member.setType(MemberType.KAKAO);
        return member;
    }

    public static Long getTokenInfo(String access_token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + access_token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        URI uri = UriComponentsBuilder.fromUriString(getTokenInfoUrl)
                .build()
                .toUri();

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

        String responseBody = response.getBody();;

        JSONObject jsonObject = new JSONObject(responseBody);
        Long sns_id = jsonObject.getLong("id");
        return sns_id;
    }
}
