package ru.netology.nmedia

import org.junit.Assert.assertEquals
import org.junit.Test

class ShortCountTest {
    @Test
    fun `less than thousand`() {
        assertEquals("0", 0.shortCount())
        assertEquals("999", 999.shortCount())
    }

    @Test
    fun `thousand range`() {
        assertEquals("1.0K", 1000.shortCount())
        assertEquals("1.1K", 1100.shortCount())
        assertEquals("9.9K", 9900.shortCount())
    }

    @Test
    fun `ten thousands`() {
        assertEquals("10K", 10000.shortCount())
        assertEquals("999K", 999000.shortCount())
    }

    @Test
    fun millions() {
        assertEquals("1.0M", 1_000_000.shortCount())
        assertEquals("1.2M", 1_200_000.shortCount())
        assertEquals("12M", 12_000_000.shortCount())
    }
}
