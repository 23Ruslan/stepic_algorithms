/*непрерывный рюкзак
Первая строка содержит количество предметов 1≤n≤10^3
  и вместимость рюкзака 0≤W≤2⋅10^6. 
Каждая из следующих n строк задаёт стоимость 0≤c_i≤2⋅10^6
  и объём 0<w_i≤2⋅10^6 предмета (n, W, c_i, w_i — целые числа). 
Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить
 любую часть, стоимость и объём при этом пропорционально уменьшатся), 
помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
Sample Input:
3 50
60 20
100 50
120 30
Sample Output:
180.000*/


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;

class The_largest_cw implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return (int) ( Math.signum(o1.getCw() - o2.getCw()) );
    }
}

class Item implements Comparable<Item> {
    public int c;
    public int w;
    public double cw;
    public Item(int c, int w) {
        this.c = c;
        this.w = w;
        this.cw = ((double) c) /w;
    }
    public double getCw() {return cw;}
    @Override //переопределение метода
    public int compareTo(Item o) {
        return (int) ( Math.signum(this.getCw() - o.getCw()) );
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), w = input.nextInt();
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < n; i++) {
            items.add(new Item( input.nextInt(), input.nextInt() ));
        }
        Comparator myComparator = new The_largest_cw();
        Collections.sort(items, myComparator.reversed());//сортировка предметов по убыванию удельной стоимости
        //for (int i = 0; i < n; i++) {System.out.println(items.get(i).c +" "+ items.get(i).w + " " + items.get(i).cw);}
        double total_c = 0, total_w = 0;
        for (int i = 0; i < n; i++) {
            if ( (w - (total_w += items.get(i).w)) >= 0 ) {total_c += items.get(i).c;}
            else {total_c += ((w - total_w + items.get(i).w) * items.get(i).cw); break;}
        }                                                                   //надёжный шаг
        System.out.printf("%.3f \n", total_c);// вывод значения с 3 знаками после запятой
    }
}