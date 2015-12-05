package com.nishtahir.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nish on 12/5/15.
 */
public class StringUtilsTest {

    @Test
    public void testClean_withNullInput_ReturnsNull() {
        assertNull(StringUtils.clean(null));
    }

    @Test
    public void testClean_withInput_ReturnsCorrectOutput() {
        assertEquals("Test", StringUtils.clean("  \"Test\"  "));
        assertEquals("Test", StringUtils.clean("\"Test\""));
    }

}