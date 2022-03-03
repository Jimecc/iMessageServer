package com.jim.sprjfx.mapper;

import com.jim.sprjfx.entity.Friend;
import com.jim.sprjfx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FriendMapper {

    public int newFriend(Friend friend);
    public int allowFriend(Friend friend);

}
