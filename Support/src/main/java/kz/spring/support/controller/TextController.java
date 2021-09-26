package kz.spring.support.controller;

import kz.spring.support.model.Text;
import kz.spring.support.model.TextList;
import kz.spring.support.service.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/text")
public class TextController {

    @Autowired
    private TextService textService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Text>> getTextsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(this.textService.getTextsByUserId(userId));
    }

    @GetMapping("/{textId}")
    public ResponseEntity<Text> getTextById(@PathVariable Long textId) {
        return ResponseEntity.ok().body(this.textService.getTextById(textId));
    }

    @PostMapping("/getTextIds")
    public ResponseEntity<TextList> getTextByIds(@RequestBody List<Long> textIds) {
        return ResponseEntity.ok().body(this.textService.getTextByIds(textIds));
    }

    @PostMapping("")
    public void createText(@RequestBody Text text) {
        this.textService.createText(text);
    }
}
