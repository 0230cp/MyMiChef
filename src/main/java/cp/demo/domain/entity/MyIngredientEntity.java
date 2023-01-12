package cp.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import javax.persistence.*;

@Table(name = "myingredtbl")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyIngredientEntity {

    @Id
    @Column(name = "id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", columnDefinition = "varchar(256)")
    private UserEntity userEntity;

    @Column(name = "ingredName", columnDefinition = "varchar(256)")
    private String ingredName;

    @Column(name = "capacity", columnDefinition = "INT")
    private int capacity;

    @Column(name = "unit", columnDefinition = "varchar(256)")
    private String unit;



}
