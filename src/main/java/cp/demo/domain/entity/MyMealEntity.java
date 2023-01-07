package cp.demo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "mymealtbl")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyMealEntity {

    @Id
    @Column(name = "id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menuId")
    private MenuEntity menuEntity;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @Column(name = "date", columnDefinition = "DATE")
    private String date;

    @Column(name = "time", columnDefinition = "varchar(256)")
    private String time;

}
