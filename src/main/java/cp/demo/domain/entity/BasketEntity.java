package cp.demo.domain.entity;

import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;

@Table(name = "baskettbl")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BasketEntity {

    @Id
    @Column(name = "id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @ManyToOne()
    @JoinColumn(name = "myMealId")
    private MyMealEntity myMealEntity;

    @Column(name = "ingredName", columnDefinition = "varchar")
    private String ingredName;

    @Column(name = "capacity", columnDefinition = "LONG")
    private Long capacity;

    @Column(name = "unit", columnDefinition = "varchar")
    private String unit;

}
