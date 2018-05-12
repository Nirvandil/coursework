package cf.nirvandil.coursework.rest.dto.responses;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Response<T> extends EmptyResponse {
    private T data;
}
