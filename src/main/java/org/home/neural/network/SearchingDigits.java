package org.home.neural.network;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomUtils;

public class SearchingDigits {
	
	//Цифры (Обучающая выборка)
	int[] num0 = {1,1,1,1,0,1,1,0,1,1,0,1,1,1,1};
	int[] num1 = {0,0,1,0,0,1,0,0,1,0,0,1,0,0,1};
	int[] num2 = {1,1,1,0,0,1,1,1,1,1,0,0,1,1,1};
	int[] num3 = {1,1,1,0,0,1,1,1,1,0,0,1,1,1,1};
	int[] num4 = {1,0,1,1,0,1,1,1,1,0,0,1,0,0,1};
	int[] num5 = {1,1,1,1,0,0,1,1,1,0,0,1,1,1,1};
	int[] num6 = {1,1,1,1,0,0,1,1,1,1,0,1,1,1,1};
	int[] num7 = {1,1,1,0,0,1,0,0,1,0,0,1,0,0,1};
	int[] num8 = {1,1,1,1,0,1,1,1,1,1,0,1,1,1,1};
	int[] num9 = {1,1,1,1,0,1,1,1,1,0,0,1,1,1,1};
	
	//Виды цифры 5 (Тестовая выборка)
	int[] num51 = {1,1,1,1,0,0,1,1,1,0,0,0,1,1,1};
	int[] num52 = {1,1,1,1,0,0,0,1,0,0,0,1,1,1,1};
	int[] num53 = {1,1,1,1,0,0,0,1,1,0,0,1,1,1,1};
	int[] num54 = {1,1,0,1,0,0,1,1,1,0,0,1,1,1,1};
	int[] num55 = {1,1,0,1,0,0,1,1,1,0,0,1,0,1,1};
	int[] num56 = {1,1,1,1,0,0,1,0,1,0,0,1,1,1,1};
	
	//Список всех вышеуказанных цифр
	int[][] nums = {num0, num1, num2, num3, num4, num5, num6, num7, num8, num9};

	int[] weights;
	
	//Порог функции активации
	int bias = 7;
	
	public SearchingDigits() {
		//Инициализация весов сети
		this.weights = IntStream.range(0, 15).toArray();
	}

	public static void main(String[] args) {
		SearchingDigits searchingDigits = new SearchingDigits();
		searchingDigits.training();
		searchingDigits.print();
	}
	
	//Является ли данное число 5
	public boolean proceed(int[] number) {
		//Рассчитываем взвешенную сумму
		int net = 0;
		
		for(int i=0; i<15; i++) {
			net += number[i]*weights[i];
		}
		
		//Превышен ли порог? (Да - сеть думает, что это 5. Нет - сеть думает, что это другая цифра)
		return net >= bias;
	}
	
	//Уменьшение значений весов, если сеть ошиблась и выдала 1
	public void decrease(int[] number) {
		for(int i=0; i<15; i++) {
			//Возбужденный ли вход
	        if (number[i] == 1) {
	        	//Уменьшаем связанный с ним вес на единицу
	            weights[i] -= 1;	        	
	        }
		}
	}
	
	//Увеличение значений весов, если сеть ошиблась и выдала 0
	public void increase(int[] number) {
		for(int i=0; i<15; i++) {
			//Возбужденный ли вход
	        if (number[i] == 1) {
	        	//Уменьшаем связанный с ним вес на единицу
	            weights[i] += 1;	        	
	        }
		}
	}
	
	//Тренировка сети
	public void training() {
		for(int i=0; i<100000; i++) {
			//Генерируем случайное число от 0 до 9
			int option = RandomUtils.nextInt(0, 10);
			
			//Если получилось НЕ число 5
			if (option != 5) {
				//Если сеть выдала True/Да/1, на не правильное число, то наказываем ее
				if(proceed(nums[option])) {
					decrease(nums[option]);
				}
				//Если получилось число 5	
			} else {
				//Если сеть выдала False/Нет/0, на правильно число, то показываем, что эта цифра - то, что нам нужно
				if(!proceed(num5)) {
					increase(num5);
				}
			}
		}
		
	
	}
	
	public void print() {
		//Вывод значений весов
		System.out.println(Arrays.toString(weights));
		 
		//Прогон по обучающей выборке
		System.out.println("0 это 5? " + proceed(num0));
		System.out.println("1 это 5? " + proceed(num1));
		System.out.println("2 это 5? " + proceed(num2));
		System.out.println("3 это 5? " + proceed(num3));
		System.out.println("4 это 5? " + proceed(num4));
		System.out.println("6 это 5? " + proceed(num6));
		System.out.println("7 это 5? " + proceed(num7));
		System.out.println("8 это 5? " + proceed(num8));
		System.out.println("9 это 5? " + proceed(num9));
		 
		//Прогон по тестовой выборке
		System.out.println("Узнал 5? " + proceed(num5));
		System.out.println("Узнал 5 - 1? " + proceed(num51));
		System.out.println("Узнал 5 - 2? " + proceed(num52));
		System.out.println("Узнал 5 - 3? " + proceed(num53));
		System.out.println("Узнал 5 - 4? " + proceed(num54));
		System.out.println("Узнал 5 - 5? " + proceed(num55));
		System.out.println("Узнал 5 - 6? " + proceed(num56));	
	}
	
}
