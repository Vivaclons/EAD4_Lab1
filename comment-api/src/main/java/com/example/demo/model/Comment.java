package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String ownerId;

    @ApiModelProperty(notes = "List of members in comment")
    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Member> membersIds;
}
