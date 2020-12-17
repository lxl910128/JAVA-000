package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.demo.api.domain.User;
import io.kimmking.rpcfx.demo.api.server.UserService;

public class UserServiceImpl implements UserService {
    
    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
