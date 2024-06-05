package com.example.ms.card.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-user", url = "${ms.user.base.url}")
@Profile("!local")
public interface UserClient {
    @GetMapping("/{userId}")
    boolean getUser(@PathVariable Long userId);
}
