package com_study.stringStudy_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

////3장 249p   리스트 3-31 실습
//public class Calculator {
//	public Integer calcSum(String filepath) throws IOException{
//		//한 줄씩 읽기 편하게 Buffered Reader로 파일을 가져온다
//		BufferedReader br = new BufferedReader(new FileReader(filepath));
//		Integer sum=0;
//		String line =null;
//		while((line = br.readLine()) != null) { // -> 마지막 라인까지 한 줄씩 읽어가면서 숫자를 더한다
//			sum += Integer.valueOf(line);
//		}
//		br.close(); // - > 한 번 연 파일은 반드시 닫아준다.
//		return sum;
//	}
//}

//3장 250p   리스트 3-32 실습

//public class Calculator {
//	public Integer calcSum(String filepath) throws IOException {
//		// 한 줄씩 읽기 편하게 Buffered Reader로 파일을 가져온다
//		BufferedReader br = null;
//		
//		try {
//			
//			//한 줄씩 읽기 편하게 Buffered Reader로 파일을 가져온다
//			br = new BufferedReader(new FileReader(filepath));
//			Integer sum=0;
//			String line =null;
//			while((line = br.readLine()) != null) { // -> 마지막 라인까지 한 줄씩 읽어가면서 숫자를 더한다
//				sum += Integer.valueOf(line);
//			}
//			br.close(); // - > 한 번 연 파일은 반드시 닫아준다.
//			return sum;
//		
//		}catch(IOException e) {
//			System.out.println(e.getMessage());
//			throw e;
//		}finally {
//			if(br != null) { // -> BufferedReader 오브젝트가 생성되기 전에 예외가 발생할 수도 있으므르 반드시 null 체크를 먼저해야 한다
//				try {
//					br.close();
//				}catch(IOException e) {
//					System.out.println(e.getMessage());
//				}
//			}
//		}
//	}

//3장 템플릿 253p 3-35 실습
public class Calculator {
	//3장 템플릿 253p 3-35 실습
//	public Integer calcSum(String filepath) throws IOException {
//		BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
//			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//				Integer sum = 0;
//				String line = null;
//				while ((line = br.readLine()) != null) { // -> 마지막 라인까지 한 줄씩 읽어가면서 숫자를 더한다
//					sum += Integer.valueOf(line);
//				}
//				return sum;
//			}
//		};
//		return fileReadTemplate(filepath, sumCallback);
//	}
	
	//3장 템플릿  리스트 3-40 실습
	public Integer calcSum(String filepath) throws IOException {
		LineCallback<Integer> sumCallback = new LineCallback<Integer>(){
			public Integer doSomethingWithLine(String line, Integer value){
				return value + Integer.valueOf(line);
			}
		};
		return lineReadTemplate(filepath, sumCallback, 0);
	}

	
	
	
	// 3장 템플릿 252p 리스트 3-34 실습

	public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
		// 한 줄씩 읽기 편하게 Buffered Reader로 파일을 가져온다
		BufferedReader br = null;

		try {

			// 한 줄씩 읽기 편하게 Buffered Reader로 파일을 가져온다
			br = new BufferedReader(new FileReader(filepath));

			// 콜백 오브젝트 호출, 템플릿에서 만든 컨텍스트 정보인 BufferedReader를 전달해주고 콜백의 작업결과를 받아둔다.
			int ret = callback.doSomethingWithReader(br);
			return ret;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (br != null) { // -> BufferedReader 오브젝트가 생성되기 전에 예외가 발생할 수도 있으므르 반드시 null 체크를 먼저해야 한다
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	// 3장 템플릿 254p 리스트 3-37 실습
//	public Integer calcMultiply(String filepath) throws IOException {
//		BufferedReaderCallback multiplyCallback = new BufferedReaderCallback() {
//			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//				Integer multiply = 1;
//				String line = null;
//				while ((line = br.readLine()) != null) { // -> 마지막 라인까지 한 줄씩 읽어가면서 숫자를 더한다
//					multiply *= Integer.valueOf(line);
//				}
//				return multiply;
//			}
//		};
//		return fileReadTemplate(filepath, multiplyCallback);
//	}
	
	
	//3장 템플릿 256p 리스트 3-40 실습
	
	public Integer calcMultiply(String filepath) throws IOException {
		LineCallback<Integer> multiplyCallback = new LineCallback<Integer>(){
			public Integer doSomethingWithLine(String line, Integer value){
				return value * Integer.valueOf(line);
			}
		};
		return lineReadTemplate(filepath, multiplyCallback, 1);
	}

	// 3장 템플릿 255p 리스트 3-39 실습
	// initVal -> 계산 결과를 저장할 변수의 초기값
	public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			T res = initVal;
			String line = null;
			while ((line = br.readLine()) != null) { // -> 파일의 각 라인을 루프를 돌면서 가져 오는것도 템플릿이 담당한다
				// line 각 라인의 내용을 가지고 계산하는 작업만 콜백에게 맡긴다.
				res = callback.doSomethingWithLine(line, res);
			}
			return res;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (br != null) { // -> BufferedReader 오브젝트가 생성되기 전에 예외가 발생할 수도 있으므르 반드시 null 체크를 먼저해야 한다
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

			}
		}
	}
	
	public String concatenate(String filepath) throws IOException{
		LineCallback<String> concatenateCallback = new LineCallback<String>() {
			
		
			public String doSomethingWithLine(String line, String value) {
		
				return value + line;
			}
		};
		// concatenateCallback -> 템플릿 메소드의 T는 모두 스트링이 된다.
		return lineReadTemplate(filepath, concatenateCallback, "");
	}
}
