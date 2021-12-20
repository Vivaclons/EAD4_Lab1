package com.example.demo.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddUserCommentRequest {
    private String userId;
    private Comment comment;
}