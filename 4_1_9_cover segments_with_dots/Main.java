/*По данным nn отрезкам необходимо найти множество точек минимального размера, для которого каждый 
из отрезков содержит хотя бы одну из точек.

В первой строке дано число 1≤n≤100 отрезков. Каждая из последующих n 
строк содержит по два числа 0≤l≤r≤10^9, задающих начало и конец отрезка. 
Выведите оптимальное число m точек и сами m точек. 
Если таких множеств точек несколько, выведите любое из них.
Sample Input 1:
3
1 3
2 5
3 6
Sample Output 1:
1
3 
Sample Input 2:
4
4 7
1 3
2 5
5 6
Sample Output 2:
2
3 6 
*/
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;

class The_largest_right_end implements Comparator < Intercept > {
  @Override
  public int compare(Intercept o1, Intercept o2) {
    return o1.getRight_end() - o2.getRight_end();
  }
}

class Intercept implements Comparable < Intercept > {
  public int left_end;
  public int right_end;
  public Intercept(int left_end, int right_end) {
    this.left_end = left_end;
    this.right_end = right_end;
  }
  public int getRight_end() {
    return right_end;
  }
  @Override //переопределение метода
  public int compareTo(Intercept o) {
    return this.getRight_end() - o.getRight_end();
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int number_of_intercepts = input.nextInt();
    ArrayList < Intercept > intercepts = new ArrayList < Intercept > ();
    for (int i = 0; i < number_of_intercepts; i++) {
      intercepts.add(new Intercept(input.nextInt(), input.nextInt()));
    }
    Comparator myComparator = new The_largest_right_end();
    Collections.sort(intercepts, myComparator); //сортировка отрезков по возрастанию правого конца
    //for (int i = 0; i < number_of_intercepts; i++) { System.out.println(intercepts.get(i).left_end +" "+ intercepts.get(i).right_end); }
    int number_of_points = 1, k;
    int[] points = new int[number_of_intercepts];
    points[0] = intercepts.get(0).right_end;
    for (int i = 1; i < number_of_intercepts; i++) {
      k = 1;
      for (int j = 0; j < number_of_points; j++) {
        if (points[j] >= intercepts.get(i).left_end) {
          k = 0;
          break;
        }
      }
      if (k == 1) {
        number_of_points++;
        points[number_of_points - 1] = intercepts.get(i).right_end;
      }
    } //надёжный шаг
    System.out.println(number_of_points);
    for (int i = 0; i < number_of_points; i++) {
      System.out.print(points[i] + " ");
    }
  }
}