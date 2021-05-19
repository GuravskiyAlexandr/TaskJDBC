package jm.task.core.jdbc.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "usr")
@AllArgsConstructor
@ToString(of = {"id", "name", "age"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
}
