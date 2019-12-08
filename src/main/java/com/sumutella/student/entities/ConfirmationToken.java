package com.sumutella.student.entities;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author sumutella
 * @time 10:27 PM
 * @since 12/7/2019, Sat
 */
@Entity
@Table(name = "confirmation_token")
@Setter
@Getter
public class ConfirmationToken {

    @Id
    @Column(name="token_id")
    @SequenceGenerator(name = "tokenSeq", sequenceName = "confirmation_token_seq", allocationSize = 1)
    @GeneratedValue(generator = "tokenSeq", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name="confirmation_token")
    private String confirmationToken;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public ConfirmationToken() {
    }

}
