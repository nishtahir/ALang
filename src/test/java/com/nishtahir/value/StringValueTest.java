package com.nishtahir.value;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringValueTest {

    @Test
    public void testGetType() throws Exception {
        StringValue value = new StringValue("Hello World");
        assertEquals(Value.TYPE.STRING_VALUE, value.getType());
    }

    @Test
    public void testCompareTo_WithValidInput_ReturnsCharacterDifference() throws Exception {
        StringValue value = new StringValue("Hello World");

        assertEquals(-6, new StringValue("Hello").compareTo(value));
        assertEquals(0, new StringValue("Hello World").compareTo(value));
        assertEquals(14, new StringValue("Hello... It's me. I was wondering if after all these years you'd like to meet.").compareTo(value));
    }

    @Test
    public void testAdd_WithIntegerValueInput_ReturnsConcatenatedResult() throws Exception {
        StringValue value = new StringValue("Here is the Number ");
        IntegerValue intVal = new IntegerValue(42);
        StringValue result = (StringValue) value.add(intVal);
        assertEquals("Here is the Number 42", result.getValue());
    }

    @Test
    public void testSubtract_WithStringValueInput_RemovesMatchingCharacters() throws Exception {
        StringValue value = new StringValue("Hello World");
        assertEquals("Hello ", value.subtract(new StringValue("World")).getValue());

        StringValue value2 = new StringValue("World Hello");
        assertEquals(" Hello", value2.subtract(new StringValue("World")).getValue());
    }

    @Test
    public void testMultiply() throws Exception {

    }
}