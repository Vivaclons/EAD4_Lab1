package kz.spring.support.service;

import kz.spring.support.model.Text;
import kz.spring.support.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TextService {
    @Autowired
    private TextRepository textRepository;
    @Autowired
    private RestTemplate restTemplate;

    private String authHost = "http://auth-service/";

    public List<Text> getTextsByUserId(Long userId) {
        return textRepository.findByUserId(userId);
    }

    public List<Text> getTextById(Long id) {
        return this.textRepository.findTextById(id);
    }

    public List<Text> getAllText() {
        return this.textRepository.findAll();
    }

    public List<Text> searchTextByTitle(String title) {
        return textRepository.getTextByTitleIsLike("%" + title + "%");
    }

    public Text createText(Text text, String authToken) {

        String url = authHost + "user/auth/user";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            this.textRepository.save(text);
            return text;
        } else {
            return null;
        }
    }

    public Text updateText(Text text, String authToken) {
        String url = authHost + "user/auth/admin";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            this.textRepository.save(text);
            return text;
        } else {
            return null;
        }
    }

    public void deleteText(Long forumId, String authToken) {

        String url = authHost + "user/auth/admin";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            this.textRepository.deleteById(forumId);
        }
        return;
    }
}
