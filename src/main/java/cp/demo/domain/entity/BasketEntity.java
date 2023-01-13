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
    @JoinColumn(name = "userId",columnDefinition = "varchar(256)")
    private UserEntity userEntity;

//    @ManyToOne()
//    @JoinColumn(name = "myMealId",columnDefinition = "INT")
//    private MyMealEntity myMealEntity;

    @Column(name = "ingredName", columnDefinition = "varchar(256)")
    private String ingredName;

    @Column(name = "capacity", columnDefinition = "INT")
    private int capacity;

    @Column(name = "unit", columnDefinition = "varchar(256)")
    private String unit;

}
