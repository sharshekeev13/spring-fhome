package kg.fhome.test.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "categories ")
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
