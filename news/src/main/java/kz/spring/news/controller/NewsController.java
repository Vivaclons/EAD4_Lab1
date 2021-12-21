package kz.spring.news.controller;

import kz.spring.news.model.News;
import kz.spring.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllNews() {
        return ResponseEntity.ok(newsService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNews(@RequestBody News news) {
        return ResponseEntity.ok(newsService.createNews(news));
    }

    @DeleteMapping("/delete/{newsId}")
    public void deleteNews(@PathVariable("newsId") Long newsId) {
        newsService.deleteNews(newsId);
    }

    @PatchMapping("/change")
    public ResponseEntity<?> updateNews(@RequestParam("newsId") Long newsId, @RequestParam("text") String text, @RequestParam("token") String token) {
        return ResponseEntity.ok(newsService.updateNews(newsId, text, token));
    }

    @GetMapping( "/{newsId}")
    public ResponseEntity<News> getPostById(@PathVariable Long newsId) {
        return ResponseEntity.ok().body(this.newsService.getNewsById(newsId));
    }

    @GetMapping("/searchByTitle/{title}")
    public ResponseEntity<List<News>> searchByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(newsService.searchLikeByTitle(title));
    }

    @GetMapping("/getByText/{text}")
    public ResponseEntity<List<News>> searchByUsername(@PathVariable("text") String text) {
        return ResponseEntity.ok(newsService.getNewsByText(text));
    }
}
