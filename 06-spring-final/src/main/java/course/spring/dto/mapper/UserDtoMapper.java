package course.spring.dto.mapper;

import course.spring.dto.AuthorEditorDto;
import course.spring.model.Article;
import course.spring.model.User;
import org.springframework.beans.BeanUtils;

public class UserDtoMapper {
    public static AuthorEditorDto mapUserTouthorEditorDto(User source) {
        var result = new AuthorEditorDto();
        BeanUtils.copyProperties(source, result);
        return result;
    }

}
