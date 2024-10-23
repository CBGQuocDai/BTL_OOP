package com.example.demo.Controller.User;

import com.example.demo.DAO.*;
import com.example.demo.Model.Comment;
import com.example.demo.Model.Post;

import com.example.demo.Model.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;


@Controller
@RequestMapping("/Post")
public class PostController1 {
    static int postId=0;
    static boolean missingTitle=false,missingTags=false,missingContent=false;
    private final PostDAO postDAO =new PostDAO();
    private final InteractionDAO interactionDAO = new InteractionDAO();
    private final CommentDAO commentDAO =new CommentDAO();
    private final UserDAO userDAO = new UserDAO();
    private final FollowDAO followDAO = new FollowDAO();
    private Post post=new Post();
    private Post pre_post=new Post();

    @GetMapping("/latest")
    public String allPost(ModelMap modelMap,HttpSession httpSession) throws SQLException {
        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));
        ArrayList<Post> posts= postDAO.selectAllPost();
        for(Post post:posts){
            post.setCountBookmark(interactionDAO.countBookmark(post.getPostId()));
            post.setCountView(interactionDAO.countView(post.getPostId()));
            post.setCountVote(interactionDAO.getNumVote(String.valueOf(post.getPostId())));
            post.setCountComment(commentDAO.countNumberComment(post.getPostId()));
        }
        Collections.reverse(posts);
        modelMap.addAttribute("avatarUser",user.getAvatar());
        modelMap.addAttribute("Posts",posts);
        return "listPost";
    }

    @GetMapping("/create")
    public String create(ModelMap modelMap, HttpSession httpSession) throws SQLException {
        post=pre_post;
        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));

//        System.out.println(user.getAvatar());
        modelMap.addAttribute("avatarUser", user.getAvatar());
        modelMap.addAttribute("Post",post);
        modelMap.addAttribute("missingTitle",missingTitle);
        modelMap.addAttribute("missingTags",missingTags);
        modelMap.addAttribute("missingContent",missingContent);
        return "create";
    }
    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("Post") Post postSubmit , BindingResult result, HttpSession httpSession){
        missingContent = result.hasFieldErrors("content");
        missingTags = result.hasFieldErrors("tags");
        missingTitle = result.hasFieldErrors("title");
        if(!result.hasErrors()) {
            postSubmit.setPostId(++postId);
            postSubmit.setUserId((int)httpSession.getAttribute("userId"));
            postSubmit.setNameAuthor((String)httpSession.getAttribute("username"));
            postDAO.addPost(postSubmit);
            post=new Post();
            pre_post = new Post();
            return "redirect:/Post/"+(postId);
        }
        else {
            pre_post= postSubmit;
            return "redirect:/Post/create";
        }
    }
    @GetMapping("/{id}")
    public String postDetail(ModelMap modelMap, @PathVariable String id,HttpSession httpSession) throws SQLException {
        post = postDAO.selectPostById(Integer.parseInt(id));
        post.setCountComment(commentDAO.countNumberComment(post.getPostId()));
        post.setCountView(interactionDAO.countView(post.getPostId()));
        post.setCountBookmark(interactionDAO.countBookmark(post.getPostId()));

        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));
        int vote= interactionDAO.getNumVote(id);
        int stateVote = interactionDAO.getStateVote(Integer.parseInt(id),user.getUserId());
        int stateBookmark= interactionDAO.getStateBookmark(Integer.parseInt(id),user.getUserId());

        int stateFollow = followDAO.getStateFollow(user.getUserId(),post.getUserId());
        User author = userDAO.getUserByUserId(post.getUserId());
        author.setCountPost(postDAO.countPost(author.getUserId()));
        author.setCountFollow(followDAO.countFollow(author.getUserId()));
        ArrayList<Comment> cmt = commentDAO.getAllCommentByPostId(id);

        for(Comment c:cmt){
            c.setCountVote(interactionDAO.getNumVoteComment(c.getCommentId()));
            c.setStateVote(interactionDAO.getStateVoteComment(c.getCommentId(),user.getUserId()));
        }
        modelMap.addAttribute("Comments",cmt);
        modelMap.addAttribute("avatarAuthor",author.getAvatar());
        modelMap.addAttribute("authorID",post.getUserId());
        modelMap.addAttribute("nameAuthor",author.getUsername());
        modelMap.addAttribute("avatarUser",user.getAvatar());
        modelMap.addAttribute("vote",vote);
        modelMap.addAttribute("userVote", stateVote);
        modelMap.addAttribute("stateFollow",stateFollow);
        modelMap.addAttribute("post",post);
        modelMap.addAttribute("userID",user.getUserId());
        modelMap.addAttribute("username",user.getUsername());
        modelMap.addAttribute("postID",id);
        modelMap.addAttribute("bookmark",stateBookmark);
        String[] tags= post.getTags().split(",");
        modelMap.addAttribute("tags",tags);
        return "postDetail";
    }

}