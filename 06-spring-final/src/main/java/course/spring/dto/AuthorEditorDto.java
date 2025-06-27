package course.spring.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AuthorEditorDto {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
}
