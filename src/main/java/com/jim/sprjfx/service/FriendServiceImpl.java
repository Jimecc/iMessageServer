package com.jim.sprjfx.service;


import com.jim.sprjfx.entity.Friend;
import com.jim.sprjfx.mapper.FriendMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FriendServiceImpl implements FriendService{

    @Autowired
    private FriendMapper friendMapper;
    @Override
    public boolean newFriend(String username, String fusername) {
        Friend friend = new Friend();
        friend.setUsername(username);
        friend.setFusername(fusername);
        log.info("NewFriend Service");
        return (friendMapper.newFriend(friend) > 0 && friendMapper.allowFriend(friend)>0) ? true : false ;
    }
}
