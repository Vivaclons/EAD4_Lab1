package kz.spring.news.service;

import kz.spring.news.model.News;
import kz.spring.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AuthService authService;

    public News getNewsById(Long postId) {
        return this.newsRepository.getNewsById(postId);
    }

    public News createNews(News news) {
        return newsRepository.saveAndFlush(news);
    }

    public void deleteNews(Long newsId) {
        newsRepository.deleteById(newsId);
    }

    public News updateNews(Long newsId, String text, String token){
        boolean authorized = authService.authAdmin(token);
        if (!authorized) {
            return null;
        }

        News news = newsRepository.getNewsById(newsId);
        news.setId(newsId);
        return newsRepository.saveAndFlush(news);
    }

    public List<News> getAll() {
        return newsRepository.findAll();
    }

    public List<News> searchLikeByTitle(String title) {
        return newsRepository.getNewsByTitleLike(title + "%");
    }

    public List<News> getNewsByText(String text) {
        return newsRepository.getNewsByUsername(text);
    }
}
