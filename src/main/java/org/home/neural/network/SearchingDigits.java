package org.home.neural.network;

import java.util.Arrays;

import org.apache.commons.lang3.RandomUtils;

public class SearchingDigits {
	
	//����� (��������� �������)
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
	
	//���� ����� 5 (�������� �������)
	int[] num51 = {1,1,1,1,0,0,1,1,1,0,0,0,1,1,1};
	int[] num52 = {1,1,1,1,0,0,0,1,0,0,0,1,1,1,1};
	int[] num53 = {1,1,1,1,0,0,0,1,1,0,0,1,1,1,1};
	int[] num54 = {1,1,0,1,0,0,1,1,1,0,0,1,1,1,1};
	int[] num55 = {1,1,0,1,0,0,1,1,1,0,0,1,0,1,1};
	int[] num56 = {1,1,1,1,0,0,1,0,1,0,0,1,1,1,1};
	
	//������ ���� ������������� ����
	int[][] nums = {num0, num1, num2, num3, num4, num5, num6, num7, num8, num9};
	
	//������������� ����� ����
	//int[] weights = IntStream.range(0, 15).toArray();
	
	int[] weights= {-7, -4, -6, -1, 4, -8, 0, 4, 0, -7, 10, 4, 8, 9, 6};
	
	//����� ������� ���������
	int bias = 7;
	
	public SearchingDigits() {}

	public static void main(String[] args) {
		SearchingDigits searchingDigits = new SearchingDigits();
		//searchingDigits.training();
		searchingDigits.print();
	}
	
	//�������� �� ������ ����� 5
	public boolean proceed(int[] number) {
		//������������ ���������� �����
		int net = 0;
		
		for(int i=0; i<15; i++) {
			net += number[i]*weights[i];
		}
		
		//�������� �� �����? (�� - ���� ������, ��� ��� 5. ��� - ���� ������, ��� ��� ������ �����)
		return net >= bias;
	}
	
	//���������� �������� �����, ���� ���� �������� � ������ 1
	public void decrease(int[] number) {
		for(int i=0; i<15; i++) {
			//������������ �� ����
	        if (number[i] == 1) {
	            //��������� ��������� � ��� ��� �� �������
	            weights[i] -= 1;	        	
	        }
		}
	}
	
	//���������� �������� �����, ���� ���� �������� � ������ 0
	public void increase(int[] number) {
		for(int i=0; i<15; i++) {
			//������������ �� ����
	        if (number[i] == 1) {
	            //��������� ��������� � ��� ��� �� �������
	            weights[i] += 1;	        	
	        }
		}
	}
	
	//���������� ����
	public void training() {
		for(int i=0; i<100000; i++) {
			//���������� ��������� ����� �� 0 �� 9
			int option = RandomUtils.nextInt(0, 10);
			
			//���� ���������� �� ����� 5
			if (option != 5) {
				//���� ���� ������ True/��/1, �� ���������� ��
				if(proceed(nums[option])) {
					decrease(nums[option]);
				}
			//���� ���������� ����� 5	
			} else {
				//���� ���� ������ False/���/0, �� ����������, ��� ��� ����� - ��, ��� ��� �����
				if(!proceed(num5)) {
					increase(num5);
				}
			}
		}
		
	
	}
	
	public void print() {
		//����� �������� �����
		System.out.println(Arrays.toString(weights));
		 
		//������ �� ��������� �������
		System.out.println("0 ��� 5? " + proceed(num0));
		System.out.println("1 ��� 5? " + proceed(num1));
		System.out.println("2 ��� 5? " + proceed(num2));
		System.out.println("3 ��� 5? " + proceed(num3));
		System.out.println("4 ��� 5? " + proceed(num4));
		System.out.println("6 ��� 5? " + proceed(num6));
		System.out.println("7 ��� 5? " + proceed(num7));
		System.out.println("8 ��� 5? " + proceed(num8));
		System.out.println("9 ��� 5? " + proceed(num9));
		 
		//������ �� �������� �������
		System.out.println("����� 5? " + proceed(num5));
		System.out.println("����� 5 - 1? " + proceed(num51));
		System.out.println("����� 5 - 2? " + proceed(num52));
		System.out.println("����� 5 - 3? " + proceed(num53));
		System.out.println("����� 5 - 4? " + proceed(num54));
		System.out.println("����� 5 - 5? " + proceed(num55));
		System.out.println("����� 5 - 6? " + proceed(num56));	
	}
	
}
