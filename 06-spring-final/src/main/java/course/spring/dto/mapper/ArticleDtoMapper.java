package course.spring.dto.mapper;

import course.spring.dto.ArticleDetailDto;
import course.spring.dto.AuthorEditorDto;
import course.spring.model.Article;
import course.spring.model.User;
import org.springframework.beans.BeanUtils;

import static course.spring.dto.mapper.UserDtoMapper.mapUserTouthorEditorDto;

public class ArticleDtoMapper {
    public static ArticleDetailDto mapArticleToArticleDetailDto(Article source) {
        var result = new ArticleDetailDto();
        BeanUtils.copyProperties(source, result);
        result.setAuthor(mapUserTouthorEditorDto(source.getAuthor()));
        result.setEditor(mapUserTouthorEditorDto(source.getEditor()));
        return result;
    }

}
