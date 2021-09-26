package kz.spring.support.service;

import kz.spring.support.model.Text;
import kz.spring.support.model.TextList;
import kz.spring.support.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextService {
    @Autowired
    private TextRepository textRepository;

    public List<Text> getTextsByUserId(Long userId) {
        return textRepository.findByUserId(userId);
    }

    public Text getTextById(Long textId) {
        return this.textRepository.getById(textId);
    }

    public void createText(Text post) {
        this.textRepository.save(post);
    }

    public TextList getTextByIds(List<Long> postIds) {
        return new TextList(textRepository.getTextsByIdIn(postIds));
    }
}
