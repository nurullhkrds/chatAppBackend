package com.nurullah.questapp.bussiness.concretes;

import com.nurullah.questapp.DTOs.request.UserUpdateRequest;
import com.nurullah.questapp.DTOs.response.UserResponse;
import com.nurullah.questapp.bussiness.abstractt.IUserService;
import com.nurullah.questapp.core.utilities.*;
import com.nurullah.questapp.entities.Comment;
import com.nurullah.questapp.entities.Like;
import com.nurullah.questapp.entities.User;
import com.nurullah.questapp.repository.CommentRepository;
import com.nurullah.questapp.repository.LikeRepository;
import com.nurullah.questapp.repository.PostRepository;
import com.nurullah.questapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserManager implements IUserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public UserManager(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }


    @Override
    public DataResult<List<User>>  getAllUser() {

        return new SuccesDataResult<List<User>>(true,"Veriler Listelendi",userRepository.findAll());

    }

    @Override
    public DataResult<User> getUserById(int id) {
        return new
                SuccesDataResult<User>
                (true,"Veri getirildi",userRepository.findById(id).orElseThrow());
    }

    @Override
    public DataResult<UserResponse> getOneUserById(int id) {
        User userTest=userRepository.findById(id).orElseThrow();
        return new
                SuccesDataResult<UserResponse>
                (true,"Veri getirildi",new UserResponse(userTest));
    }

    public DataResult<User> getByUserName(String userName) {
        return new
                SuccesDataResult<User>
                (true,"Veri getirildi",userRepository.findByUserName(userName));
    }

    @Override
    public Result addUser(User user) {
        User userTest=userRepository.save(user);
        if (userTest==null){
            return new ErrorResult(false,"Kullanıcı Eklenemedi !");

        }
        return new SuccessResult(true,"Kullanıcı Başarıyla Eklendi");

    }



    @Override
    public Result deleteUser(int id) {

         userRepository.deleteById(id);
         return new SuccessResult(true,"Başarıyla Silindi");
    }

    @Override
    public List<Object> getUserActivity(int userId) {
        List<Integer> postIds = postRepository.findTopByUserId(userId);
        if(postIds.isEmpty())
            return null;
        List<Object> comments = commentRepository.findUserTopComments(postIds);
        List<Object> likes = likeRepository.findUserTopLikes(postIds);
        List<Object> result = new ArrayList<>();
        result.addAll(comments);
        result.addAll(likes);
        return result;



    }

    @Override
    public DataResult<User> updateUser(int userId, UserUpdateRequest userUpdateRequest) {
        Optional<User> userTest = userRepository.findById(userId);
        if (userTest.isPresent()) {
            User userToUpdate=userTest.get();
            userToUpdate.setAvatar(userUpdateRequest.getAvatar());
            userRepository.save(userToUpdate);
            return new SuccesDataResult<User>(true,"Güncellendi",userToUpdate);

        }
        return new ErrorDataResult<>(false,"Güncellenemedi...!",null);
    }



}
