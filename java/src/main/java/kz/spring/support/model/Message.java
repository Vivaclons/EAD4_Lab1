package kz.spring.support.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_message")
    private String user_message;

    @Column(name = "user_id")
    private Long user_id;
}
