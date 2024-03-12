package com.atguigu.service;

import com.atguigu.pojo.User;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestHeader;

/**
* @author fourzsh
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-03-03 11:26:40
*/
public interface UserService extends IService<User> {

    public Result login(User user);

    public Result userInfo(@RequestHeader String token);

    public Result checkUserName(String username);

    public Result regist(User user);



}
