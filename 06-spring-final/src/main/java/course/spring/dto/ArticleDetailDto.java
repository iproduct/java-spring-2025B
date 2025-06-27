package course.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import course.spring.model.Category;
import course.spring.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ArticleDetailDto {
    private Long id;
    private String title;
    private String content;
    private AuthorEditorDto author;
    private AuthorEditorDto editor;
    private LocalDateTime publishDate;
    private Set<String> tags = new HashSet<>();
    private Set<Category> categories = new HashSet<>();
}
