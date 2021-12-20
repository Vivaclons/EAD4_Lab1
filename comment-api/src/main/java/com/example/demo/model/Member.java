package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "forum_id")
    private Long forumId;
    private String memberUsername;

    public Member(Long forumId, String username) {
        this.forumId = forumId;
        this.memberUsername = username;
    }
}
