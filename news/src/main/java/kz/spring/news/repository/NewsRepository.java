package kz.spring.news.repository;

import kz.spring.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByNewsId(Long userId);
    List<News> getNewsByIdIn(List<Long> ids);
    News getNewsById(Long id);
    List<News> getNewsByTitleLike(String title);
    List<News> getNewsByUsername(String username);
}
