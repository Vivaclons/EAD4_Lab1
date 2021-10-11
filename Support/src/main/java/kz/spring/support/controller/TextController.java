package kz.spring.support.controller;

import kz.spring.support.model.Text;
import kz.spring.support.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/text")
public class TextController {

    @Autowired
    private TextService textService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllText() {
        return ResponseEntity.ok(textService.getAllText());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Text>> getTextsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(this.textService.getTextsByUserId(userId));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchTextByTitle(@PathVariable String title) {
        System.out.println("search" + " " + title);
        return ResponseEntity.ok(textService.searchTextByTitle(title));
    }

    @DeleteMapping("/delete/{textId}")
    public void deleteForum(@PathVariable("textId") Long forumId, @RequestHeader("Authorization") String auth) {
        this.textService.deleteText(forumId, auth);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Text>>  getTextById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.textService.getTextById(id));
    }

    @PostMapping("/create")
    public Text createText(@RequestBody Text text, @RequestHeader("Authorization") String auth) {
        return this.textService.createText(text, auth);
    }

    @PostMapping("/update")
    public Text updateText(@RequestBody Text text, @RequestHeader("Authorization") String auth) {
        return textService.updateText(text, auth);
    }
}
