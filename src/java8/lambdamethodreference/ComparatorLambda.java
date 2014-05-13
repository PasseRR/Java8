package java8.lambdamethodreference;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Comparator��lambda���ʽ���ü���������
 * @version 1.0
 * @author xiehai
 * @date 2014��3��20�� ����10:51:59 
 */
public class ComparatorLambda {
	public static void main(String[] args) {
		Integer []arrays = {1, 3, 4, 5, 2, 8, 7, 6, 9};
		Collections.sort(Arrays.asList(arrays));
		System.out.println(Arrays.toString(arrays));
		normalSort(arrays);
		System.out.println(Arrays.toString(arrays));
		lambdaSort(arrays);
		System.out.println(Arrays.toString(arrays));
		methodReferenceSort(arrays);
		System.out.println(Arrays.toString(arrays));
	}
	
	/**
	 * һ�㷽����������
	 * @param arrays
	 */
	public static void normalSort(Integer []arrays){
		Collections.sort(Arrays.asList(arrays), new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 > o2 ? -1 : 1;
			}
		});
	}
	
	/**
	 * ʹ��lambda���ʽ����
	 * @param arrays
	 */
	public static void lambdaSort(Integer []arrays){
		Collections.sort(Arrays.asList(arrays), (o1, o2) -> {
			return o1 > o2 ? -1 : 1;
		});
	}
	
	/**
	 * ������������
	 * @param arrays
	 */
	public static void methodReferenceSort(Integer []arrays){
		Collections.sort(Arrays.asList(arrays), ComparatorLambda::compare);
	}
	
	public static int compare(int o1, int o2){
		return o1 > o2 ? -1 : 1;
	}
}
