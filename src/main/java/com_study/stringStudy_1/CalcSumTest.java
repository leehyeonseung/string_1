package com_study.stringStudy_1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

// 3장 249p 리스트 3-30 실습
public class CalcSumTest {
	Calculator calculator;
	String numFilepath;

	@Before public void setUp() {
		this.calculator = new Calculator();
		this.numFilepath = getClass().getResource("numbers.txt").getPath();
	}
	
	
	@Test
	public void sumOfNumbers() throws IOException{
//		Calculator calculator = new Calculator();
//		int sum = calculator.calcSum(getClass().getResource("numbers.txt").getPath());
//		assertThat(sum, is(10));
		assertThat(calculator.calcSum(this.numFilepath), is(10));
	}
	@Test
	public void multiplyOfNumbers() throws IOException{
		assertThat(calculator.calcMultiply(this.numFilepath), is(24));
	}
	
	@Test
	public void concatenateStrings() throws IOException{
		assertThat(calculator.concatenate(this.numFilepath), is("1234"));
	}
}
