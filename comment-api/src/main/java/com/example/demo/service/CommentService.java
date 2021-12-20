package com.example.demo.service;

import com.example.demo.model.*;

import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    AuthService authService;

    public Comment getCommentById(Long commentId) {
        return commentRepository.getCommentById(commentId);
    }

    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    public List<Comment> searchCommentByTitle(String title) {
        return commentRepository.getCommentsByTitleIsLike("%" + title + "%");
    }

    public Comment createComment(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment updateCommentOwnerByUsername(Long commentId, String username, String token) {
        boolean authorized = authService.authAdmin(token);
        if (!authorized) {
            return null;
        }

        Comment comment = commentRepository.getCommentById(commentId);
        comment.setOwnerId(username);
        return commentRepository.saveAndFlush(comment);

    }

    public Comment addMember(Long commentId, String username,  String token) {

        boolean authorized = authService.authAdmin(token);
        if (!authorized) {
            return null;
        }

        Comment comment = commentRepository.getCommentById(commentId);
        comment.getMembersIds().add(new Member(comment.getId(), username));
        return commentRepository.saveAndFlush(comment);




    }

    public Comment removeMember(Long commentId, Long memberId, String token) {

        boolean authorized = authService.authAdmin(token);
        if (!authorized) {
            return null;
        }

        Comment comment = commentRepository.getCommentById(commentId);

        System.out.println(comment.getMembersIds().size());
        if (comment.getMembersIds().get(i).getMemberId().equals(memberId)) {
            comment.getMembersIds().remove(i);
            has = true;
            System.out.println(comment.getMembersIds().size());
            break;
        }



        return null;
    }
}
