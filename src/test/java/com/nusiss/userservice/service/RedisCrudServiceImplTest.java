package com.nusiss.userservice.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

class RedisCrudServiceImplTest {

    @InjectMocks
    private RedisCrudServiceImpl redisCrudService;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }


    @Test
    void testDelete() {
        // Arrange
        String key = "testKey";

        // Act
        redisCrudService.delete(key);

        // Assert
        verify(redisTemplate, times(1)).delete(key);
    }

    @Test
    void testExists() {
        // Arrange
        String key = "testKey";
        when(redisTemplate.hasKey(key)).thenReturn(true);

        // Act
        boolean result = redisCrudService.exists(key);

        // Assert
        assertTrue(result);
        verify(redisTemplate, times(1)).hasKey(key);
    }

    @Test
    void testExists_KeyNotPresent() {
        // Arrange
        String key = "testKey";
        when(redisTemplate.hasKey(key)).thenReturn(false);

        // Act
        boolean result = redisCrudService.exists(key);

        // Assert
        assertFalse(result);
        verify(redisTemplate, times(1)).hasKey(key);
    }
}