package com.tgd.service.identity;

import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class TokenBlacklistService {
    private final StringRedisTemplate redisTemplate;
    private static final String BLACKLIST_PREFIX = "jwt:blacklist:";

    public void blacklistToken(String jti, long remainingTimeInMs) {
        if (remainingTimeInMs > 0) {
            String key = BLACKLIST_PREFIX + jti;
            redisTemplate.opsForValue().set(key, "logout", remainingTimeInMs, TimeUnit.MILLISECONDS);
        }
    }

    public boolean isBlacklisted(String jti) {
        if (jti == null) return false;
        String key = BLACKLIST_PREFIX + jti;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

	public TokenBlacklistService(StringRedisTemplate redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}
}
