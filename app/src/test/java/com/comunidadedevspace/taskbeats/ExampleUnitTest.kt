package com.comunidadedevspace.taskbeats

import org.junit.Test

import org.junit.Assert.assertEquals
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val mockNumbersProvider: MyNumbersProvider = mock()

    private val underTest = MyCountRepositoryImpl(
        numbersProvider = mockNumbersProvider
    )

    @Test
    fun addition_isCorrect() {
        //Give
        whenever(mockNumbersProvider.getNumber()).thenReturn(2)

        //when
        val result = underTest.sum()

        //then
        val expected = 4
        assertEquals(expected, result)
    }
}