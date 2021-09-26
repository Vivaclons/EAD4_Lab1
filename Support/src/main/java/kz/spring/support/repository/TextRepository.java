package kz.spring.support.repository;

import kz.spring.support.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
    List<Text> findByUserId(Long userId);
    List<Text> getTextsByIdIn(List<Long> ids);
}
