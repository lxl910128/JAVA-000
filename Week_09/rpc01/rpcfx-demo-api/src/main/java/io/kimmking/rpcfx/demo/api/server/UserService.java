package io.kimmking.rpcfx.demo.api.server;

import io.kimmking.rpcfx.demo.api.domain.User;

public interface UserService {
    
    User findById(int id);
    
}
