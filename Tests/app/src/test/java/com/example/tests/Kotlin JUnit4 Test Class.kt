package com.example.tests

import org.junit.Assert.*
import org.junit.Test

class CalculatorTest {

    @Test
    fun `calculator add should sum numbers`(){
        val calc = Calculator()

        val result = calc.add(2,3)
        assertEquals(5,result)
    }
}