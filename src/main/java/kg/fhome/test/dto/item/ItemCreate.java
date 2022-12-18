package kg.fhome.test.dto.item;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kg.fhome.test.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ItemCreate {
    private String title;
    private String description;
    private String city;
    private String address;
    private String price;
    private List<String> categories;
    private Long userId;
}
