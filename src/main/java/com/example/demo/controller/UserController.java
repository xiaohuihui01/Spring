package com.example.demo.controller;

import com.example.demo.SqlSessionLoader;
import com.example.demo.UserLoginRequest;
import com.example.demo.UserRegisterRequest;
import com.example.demo.bean.ResponseBean;
import com.example.demo.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseBean register(@RequestBody UserRegisterRequest request) throws
            IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        User user = sqlSession.selectOne("example.UserMapper.findUserByUsername",
                request.getUsername());
        if (user != null) {
            sqlSession.close();
            return new ResponseBean(1, "The username is already used", "a");
        } else {
            sqlSession.insert("example.UserMapper.addUser", new
                    User(request.getUsername(), request.getPassword(), request.getEmail(),
                    request.getPhone()));
            sqlSession.commit();
            sqlSession.close();
            return new ResponseBean(0, "abc", "a"); // use your generated token here.

        }
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseBean login(@RequestBody UserLoginRequest request,HttpSession session) throws
            IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        User user = sqlSession.selectOne("example.UserMapper.findUserByUsername",
                request.getUsername());
        if (user.getPassword().equals(request.getPassword())) {
            session.setAttribute("username",request.getUsername());
            session.setAttribute("password",request.getPassword());
            sqlSession.close();
            return new ResponseBean(1, "Login successfully", request);
        }
        else {
            sqlSession.close();
            return new ResponseBean(0, "Login failed,username or password wrong", request); // use your generated token here.

        }
    }
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ResponseBean userList(HttpSession session) throws
            IOException {

        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<User> userList=sqlSession.selectList("example.UserMapper.findUserAll");
        for(User user:userList){
            user.setPassword("");
        }
        return new ResponseBean(2,"userlist returned from db",userList);
        }
    }
