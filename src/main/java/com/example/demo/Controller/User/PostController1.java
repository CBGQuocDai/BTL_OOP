package com.example.demo.Controller.User;

import com.example.demo.DAO.*;
import com.example.demo.Model.Comment;
import com.example.demo.Model.Notification;
import com.example.demo.Model.Post;

import com.example.demo.Model.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;




@Controller
@RequestMapping("")
public class PostController1 {
    static int postId=26;
    static boolean missingTitle=false,missingTags=false,missingContent=false;
    private final PostDAO postDAO =new PostDAO();
    private final InteractionDAO interactionDAO = new InteractionDAO();
    private final CommentDAO commentDAO =new CommentDAO();
    private final UserDAO userDAO = new UserDAO();
    private final FollowDAO followDAO = new FollowDAO();
    private Post post=new Post();
    private Post pre_post=new Post();
    @Autowired
    private NotificationDAO notificationDAO;

    @GetMapping("/post/latest")
    public String allPost(ModelMap modelMap,HttpSession httpSession) throws SQLException {
        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));
        List<Post> posts= postDAO.getAllPost();
        for(Post post:posts){
            post.setCountBookmark(interactionDAO.countBookmark(post.getPostId()));
            post.setCountView(interactionDAO.countView(post.getPostId()));
            post.setCountVote(interactionDAO.getNumVote(String.valueOf(post.getPostId())));
            post.setCountComment(commentDAO.countNumberComment(post.getPostId()));
        }

        modelMap.addAttribute("type","post");
        modelMap.addAttribute("index","latest");
        modelMap.addAttribute("userId",user.getUserId());
        modelMap.addAttribute("avatarUser",user.getAvatar());
        modelMap.addAttribute("Posts",posts);
        return "listPost";
    }
    @GetMapping("/post/follow")
    public String followPost(ModelMap modelMap,HttpSession httpSession) throws SQLException {
        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));
        List<Post> posts= postDAO.getAllFollow(user.getUserId());
        for(Post post:posts){
            post.setCountBookmark(interactionDAO.countBookmark(post.getPostId()));
            post.setCountView(interactionDAO.countView(post.getPostId()));
            post.setCountVote(interactionDAO.getNumVote(String.valueOf(post.getPostId())));
            post.setCountComment(commentDAO.countNumberComment(post.getPostId()));
        }

        modelMap.addAttribute("userId",user.getUserId());
        modelMap.addAttribute("type","post");
        modelMap.addAttribute("index","follow");
        modelMap.addAttribute("avatarUser",user.getAvatar());
        modelMap.addAttribute("Posts",posts);
        return "listPost";
    }
    @GetMapping("/post/bookmark")
    public String bookmarkPost(ModelMap modelMap,HttpSession httpSession) throws SQLException {
        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));
        List<Post> posts= postDAO.getAllBookmark(user.getUserId());
        for(Post post:posts){
            post.setCountBookmark(interactionDAO.countBookmark(post.getPostId()));
            post.setCountView(interactionDAO.countView(post.getPostId()));
            post.setCountVote(interactionDAO.getNumVote(String.valueOf(post.getPostId())));
            post.setCountComment(commentDAO.countNumberComment(post.getPostId()));
        }

        modelMap.addAttribute("userId",user.getUserId());
        modelMap.addAttribute("type","post");
        modelMap.addAttribute("index","bookmark");
        modelMap.addAttribute("avatarUser",user.getAvatar());
        modelMap.addAttribute("Posts",posts);
        return "listPost";
    }

    @GetMapping("/question/latest")
    public String allQuestion(ModelMap modelMap,HttpSession httpSession) throws SQLException {
        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));
        List<Post> posts= postDAO.getAllPost();
        for(Post post:posts){
            post.setCountBookmark(interactionDAO.countBookmark(post.getPostId()));
            post.setCountView(interactionDAO.countView(post.getPostId()));
            post.setCountVote(interactionDAO.getNumVote(String.valueOf(post.getPostId())));
            post.setCountComment(commentDAO.countNumberComment(post.getPostId()));
        }

        modelMap.addAttribute("userId",user.getUserId());
        modelMap.addAttribute("type","question");
        modelMap.addAttribute("index","latest");
        modelMap.addAttribute("avatarUser",user.getAvatar());
        modelMap.addAttribute("Posts",posts);
        return "listPost";
    }
    @GetMapping("/question/follow")
    public String followQuestion(ModelMap modelMap,HttpSession httpSession) throws SQLException {
        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));
        List<Post> posts= postDAO.getAllFollow(user.getUserId());
        for(Post post:posts){
            post.setCountBookmark(interactionDAO.countBookmark(post.getPostId()));
            post.setCountView(interactionDAO.countView(post.getPostId()));
            post.setCountVote(interactionDAO.getNumVote(String.valueOf(post.getPostId())));
            post.setCountComment(commentDAO.countNumberComment(post.getPostId()));
        }

        modelMap.addAttribute("userId",user.getUserId());
        modelMap.addAttribute("type","question");
        modelMap.addAttribute("index","follow");
        modelMap.addAttribute("avatarUser",user.getAvatar());
        modelMap.addAttribute("Posts",posts);
        return "listPost";
    }
    @GetMapping("/question/bookmark")
    public String bookmarkQuestion(ModelMap modelMap,HttpSession httpSession) throws SQLException {
        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));
        List<Post> posts= postDAO.getAllBookmark(user.getUserId());
        for(Post post:posts){
            post.setCountBookmark(interactionDAO.countBookmark(post.getPostId()));
            post.setCountView(interactionDAO.countView(post.getPostId()));
            post.setCountVote(interactionDAO.getNumVote(String.valueOf(post.getPostId())));
            post.setCountComment(commentDAO.countNumberComment(post.getPostId()));
        }

        modelMap.addAttribute("userId",user.getUserId());
        modelMap.addAttribute("type","question");
        modelMap.addAttribute("index","bookmark");
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
        modelMap.addAttribute("userId", user.getUserId());
        modelMap.addAttribute("Post",post);
        modelMap.addAttribute("missingTitle",missingTitle);
        modelMap.addAttribute("missingTags",missingTags);
        modelMap.addAttribute("missingContent",missingContent);
        return "create";
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
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

            LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);


            ArrayList<Integer> follower= followDAO.getAllFollower(postSubmit.getUserId());
            for(int x:follower){
                Notification notice = new Notification();
                notice.setUserId(x);
                notice.setPostId(postId);
                notice.setTime(timestamp);
                notice.setMessage(String.format("%s vừa đăng một bài viết mới",postSubmit.getNameAuthor()));
                notificationDAO.save(notice);
                messagingTemplate.convertAndSend("/topic/notifications", notice);
            }
            post=new Post();
            pre_post = new Post();
            return "redirect:/"+(postId);
        }
        else {
            pre_post= postSubmit;
            return "redirect:/create";
        }
    }
    @GetMapping("/{id}")
    public String postDetail(ModelMap modelMap, @PathVariable int id, HttpSession httpSession) throws SQLException,NumberFormatException{
        post = postDAO.getPostById(id);
        post.setCountComment(commentDAO.countNumberComment(post.getPostId()));
        post.setCountView(interactionDAO.countView(post.getPostId()));
        post.setCountBookmark(interactionDAO.countBookmark(post.getPostId()));

        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));
        int vote= interactionDAO.getNumVote(String.valueOf(id));
        int stateVote = interactionDAO.getStateVote(id,user.getUserId());
        int stateBookmark= interactionDAO.getStateBookmark(id,user.getUserId());

        int stateFollow = followDAO.getStateFollow(user.getUserId(),post.getUserId());
        User author = userDAO.getUserByUserId(post.getUserId());
        author.setCountPost(postDAO.countPost(author.getUserId()));
        author.setCountFollow(followDAO.countFollow(author.getUserId()));
        ArrayList<Comment> cmt = commentDAO.getAllCommentByPostId(String.valueOf(id));

        for(Comment c:cmt){
            c.setCountVote(interactionDAO.getNumVoteComment(c.getCommentId()));
            c.setStateVote(interactionDAO.getStateVoteComment(c.getCommentId(),user.getUserId()));
        }
        modelMap.addAttribute("Comments",cmt);
        modelMap.addAttribute("author",author);
        modelMap.addAttribute("avatarAuthor",author.getAvatar());
        modelMap.addAttribute("authorID",post.getUserId());
        modelMap.addAttribute("nameAuthor",author.getUsername());
        modelMap.addAttribute("avatarUser",user.getAvatar());
        modelMap.addAttribute("vote",vote);
        modelMap.addAttribute("userVote", stateVote);
        modelMap.addAttribute("stateFollow",stateFollow);
        modelMap.addAttribute("post",post);
        modelMap.addAttribute("userId",user.getUserId());
        modelMap.addAttribute("username",user.getUsername());
        modelMap.addAttribute("postID",id);
        modelMap.addAttribute("bookmark",stateBookmark);
        String[] tags= post.getTags().split(",");
        modelMap.addAttribute("tags",tags);
        return "postDetail";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String key, ModelMap modelMap, HttpSession httpSession) throws SQLException {
        List<Post> posts= postDAO.search(key);
        User user = userDAO.getUserByUsername((String) httpSession.getAttribute("username"));
        modelMap.addAttribute("posts",posts);
        modelMap.addAttribute("userId",user.getUserId());
        modelMap.addAttribute("avatarUser",user.getAvatar());
        return "search";
    }
}