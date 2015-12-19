package com.nishtahir.alang.value;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class IntegerValueTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testGetType() {
        IntegerValue value = new IntegerValue(42);
        assertEquals(Value.TYPE.INTEGER_VALUE, value.getType());
    }

    @Test
    public void testCompareTo_WithNullInput_ReturnsCorrectOutput() throws Exception {
        IntegerValue value = new IntegerValue(42);
        assertEquals(1, value.compareTo(null));

    }

    @Test
    public void testCompareTo_WithValidInput_ReturnsCorrectOutput() throws Exception {
        IntegerValue value = new IntegerValue(42);
        assertEquals(1, new IntegerValue(99).compareTo(value));
        assertEquals(0, new IntegerValue(42).compareTo(value));
        assertEquals(-1, new IntegerValue(10).compareTo(value));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd_WithString_ThrowsException() throws Exception {
        new IntegerValue(42).add(new StringValue("Hello"));
//        new IntegerValue(42).add(new BooleanValue(true));
//        new IntegerValue(42).add(new ListValue());
    }

    @Test
    public void testSubtract() throws Exception {

    }

    @Test
    public void testMultiply() throws Exception {

    }
}