/*В первой строке даны целое число 1≤n≤10^5 и массив A[1…n] из n различных натуральных чисел, 
не превышающих 10^9, в порядке возрастания, во второй — целое число 1≤k≤10^5 и k натуральных 
чисел b_1,. ... , b_k, не превышающих 10^9. Для каждого i от 1 до k необходимо вывести 
индекс 1≤j≤n, для которого A[j]=b_i, или -1, если такого j нет.
Sample Input:
5 1 5 8 12 13
5 8 1 23 1 11
Sample Output:
3 1 -1 1 -1
*/
import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(), l, r, m;
    int[] a = new int[n + 1], b;
    for (int i = 1; i <= n; i++) {
      a[i] = sc.nextInt();
    }
    b = new int[sc.nextInt() + 1];
    for (int i = 1; i < b.length; i++) {
      b[i] = sc.nextInt();
    }
    sc.close();
    for (int i = 1; i < b.length; i++) {
      l = 1;
      r = n;
      a[0] = 0; //flag
      while (l <= r) {
        m = l + (r - l) / 2; //java - auto round down (1/2 = 0) 
        if (a[m] == b[i]) {
          System.out.print(m + " ");
          a[0] = 1;
          break;
        } else {
          if (a[m] > b[i]) {
            r = --m;
          } else {
            l = ++m;
          }
        }
      }
      if (a[0] == 0) {
        System.out.print(-1 + " ");
      }
    }
  }
}