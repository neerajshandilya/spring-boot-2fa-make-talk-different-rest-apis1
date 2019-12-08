package com.sumutella.student.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author sumutella
 * @time 10:19 PM
 * @since 12/7/2019, Sat
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name = "userSeq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(generator = "userSeq", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "full_name")
    private String fullName;
    private String username;
    private String password;
    @Column(name="enabled")
    private boolean enabled;

}
