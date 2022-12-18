package kg.fhome.test.dto.book;

import kg.fhome.test.entity.Item;
import lombok.Data;

@Data
public class BookCreateDto {
    private Long userId;
    private Long ownerId;
    private Long itemId;
    private String note;
}
