package com.pose.oauth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pose.oauth.entity.CustomOAuth2User;
import com.pose.oauth.entity.User;
import com.pose.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    /**
     * OAuth 설정
     * @param req the user request
     * @return
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest req) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(req);
        String oauthClientName = req.getClientRegistration().getClientName();

        try {
            System.out.println("new ObjectMapper = " + new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        User user = null;
        String userId = null;
        String email = "email";

        if (oauthClientName.equals("kakao")) {
            userId = "kakao_" + oAuth2User.getAttributes().get("id");
            user = new User(userId, email, "kakao");
        } else if (oauthClientName.equals("naver")) {
            Map<String, String> map = (Map<String, String>) oAuth2User.getAttributes().get("response");
            userId = "naver_" + map.get("id").substring(0, 14);
            email = map.get("email");
            user = new User(userId, email, "naver");
        }

        userRepository.save(user);

        return new CustomOAuth2User(userId);
    }
}
