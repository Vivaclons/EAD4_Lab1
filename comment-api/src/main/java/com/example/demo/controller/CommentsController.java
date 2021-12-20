package com.example.demo.controller;

import io.swagger.annotations.ApiOperation;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Get all comments")
    @GetMapping("/all")
    public ResponseEntity<?> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComment());
    }

    @ApiOperation(value = "Get by comment id")
    @GetMapping()
    public ResponseEntity<?> getCommentById(@RequestParam("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @ApiOperation(value = "Search comments by title field")
    @GetMapping("/search")
    public ResponseEntity<?> searchCommentByTitle(@RequestParam("title") String title) {
        System.out.println("search" + " " + title);
        return ResponseEntity.ok(commentService.searchCommentByTitle(title));
    }

    @ApiOperation(value = "Create new comment")
    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @ApiOperation(value = "Delete by comment id")
    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
    }

    @ApiOperation(value = "Set comment owner by username")
    @PatchMapping("/change-owner")
    public ResponseEntity<?> updateCommentOwnerByUsername(@RequestParam("commentId") Long commentId, @RequestParam("username") String username) {
        return ResponseEntity.ok(commentService.updateCommentOwnerByUsername(commentId, username, token));
    }

    @ApiOperation(value = "Add member in comment")
    @PatchMapping("/add-member")
    public ResponseEntity<?> addMember(@RequestParam("commentId") Long commentId, @RequestParam("username") String username) {
        return ResponseEntity.ok(commentService.addMember(commentId, username));
    }

    @ApiOperation(value = "remove member in comment")
    @PatchMapping("/remove-member")
    public ResponseEntity<?> removeMember(@RequestParam("commentId") Long commentId, @RequestParam("memberId") Long memberId) {
        return ResponseEntity.ok(commentService.removeMember(commentId, memberId));
    }
}
