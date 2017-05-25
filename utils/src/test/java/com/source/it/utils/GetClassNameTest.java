package com.source.it.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetClassNameTest {

    @Test
    public void testGetClassName() {
        //Given
        String expected = "com.source.it.utils.GetClassNameTest";

        //When
        String actual = GetClassUtil.getClassName();

        //Then
        assertEquals(actual, expected);
    }

}
