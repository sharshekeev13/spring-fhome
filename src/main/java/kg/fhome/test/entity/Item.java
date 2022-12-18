package kg.fhome.test.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String description;
    private String first_photo;
    private String second_photo;
    private String third_photo;
    @NotNull
    private String city;
    @NotNull
    private String address;
    private int price;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @NotNull
    private boolean confirmed;
    private Long userId;
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "categories_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
}
