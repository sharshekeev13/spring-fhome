package kg.fhome.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_favourites")
@NoArgsConstructor
@AllArgsConstructor
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "created_date")
    private Date createdDate;

    public Favourites(User user, Item item) {
        this.user = user;
        this.item = item;
        this.createdDate = new Date();
    }

}
