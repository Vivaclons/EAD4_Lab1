package kz.spring.news.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class News {
    private Long id;
    private String text;
    private String description;
}
