package cp.demo.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "usertbl")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "userId" , columnDefinition = "varchar(256)")
    private String userId;

    @Column(name = "password", columnDefinition = "varchar(256)")
    private String password;

    @Column(name = "name", columnDefinition = "varchar(256)")
    private String name;

    @Column(name = "height", columnDefinition = "INT")
    private int height;

    @Column(name = "weight", columnDefinition = "INT")
    private int weight;

    @Column(name = "calorie", columnDefinition = "INT")
    private int calorie;
}
