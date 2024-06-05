package com.example.ms.card.client;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class UserClientMock implements UserClient{
    @Override
    public boolean userExists(Long userId) {
        return true;
    }
}
