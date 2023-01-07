package cp.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "menutbl")
@Entity
@NoArgsConstructor
public class MenuEntity {

    @Id
    @Column(name = "menuId", columnDefinition = "varchar")
    private String menuId;

    @Column(name = "menuName", columnDefinition = "varchar")
    private String menuName;

    @Column(name = "nationName", columnDefinition = "varchar")
    private String nationName;

    @Column(name = "calorie", columnDefinition = "varchar")
    private String calorie;

    @Column(name = "levelName", columnDefinition = "varchar")
    private String levelName;

    @Column(name = "recipe", columnDefinition = "TEXT")
    private String recipe;
}