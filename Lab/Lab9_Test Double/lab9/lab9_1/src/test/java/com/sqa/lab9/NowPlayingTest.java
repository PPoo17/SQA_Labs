package com.sqa.lab9;
/*
นายปฏิภาณ มะนิลทิพย์ 673380589-2 Sec 2
*/
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NowPlayingTest {

    private NowPlaying nowPlaying;

    @BeforeEach
    void setUp() {
        MovieService movieServiceStub = new MovieServiceStub();
        nowPlaying = new NowPlaying(movieServiceStub);
    }

    @Test
    void testGetNowPlayingByCinemaType_VIP_ShouldReturnOnlyVipMovies() {
        // Act
        List<String> result = nowPlaying.getNowPlayingByCinemaType(
                "Central Plaza", "2026-08-23", "VIP");

        // Assert
        assertEquals(3, result.size());
        assertTrue(result.contains("Shadow of the Lake"));
        assertTrue(result.contains("Neon Horizon"));
        assertTrue(result.contains("Iron Compass"));

        // ต้องไม่มีวงเล็บ [VIP] ติดมาด้วย
        assertFalse(result.get(0).contains("[VIP]"));

        // ต้องไม่มีหนังประเภทอื่นปนมา
        assertFalse(result.contains("The Last Signal"));
        assertFalse(result.contains("Whispers in the Fog"));
    }

    @Test
    void testGetNowPlayingByCinemaType_NoMatch_ShouldReturnEmptyList() {
        List<String> result = nowPlaying.getNowPlayingByCinemaType(
                "Central Plaza", "2026-08-23", "4DX");

        assertTrue(result.isEmpty());
    }
}