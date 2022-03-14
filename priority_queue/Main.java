/*очередь с приоритетами
Первая строка входа содержит число операций 1≤n≤10^5. Каждая из последующих n
 строк задают операцию одного из следующих двух типов:

Insert {x}, где 0≤x≤10^9 — целое число;
ExtractMax.
Первая операция добавляет число x в очередь с приоритетами, вторая — 
извлекает максимальное число и выводит его.
Sample Input:
6
Insert 200
Insert 10
ExtractMax
Insert 5
Insert 500
ExtractMax
Sample Output:
200
500
*/


import java.util.ArrayList;
import java.util.Scanner;
class Heap
{
    private static ArrayList < Integer > tree = new ArrayList < Integer > (); //binary tree
    private static Scanner l = new Scanner(System.in);
    private static String s;
    private static void swap(int i, int j)
    {
        int l = tree.get(i);
        tree.set(i, tree.get(j));
        tree.set(j, l);
    }
    private static int leftChild(int i)
        {
            return(i - 1) * 2 + 2;
        } //for binary trees only   
    private static int rightChild(int i)
        {
            return(i - 1) * 2 + 3;
        } //for binary trees only  
    private static int parent(int i)
        {
            return(int) Math.ceil((i - 1.0) / 2);
        } //for binary trees only 
    private static void SiftUp(int i)
    {
        while(i > 1 && tree.get(i) > tree.get(parent(i)))
        {
            swap(parent(i), i);
            i = parent(i);
        }
    }
    private static void SiftDown(int i)
    { //for binary trees only 
        while(true)
        {
            if(leftChild(i) == tree.size() - 1 && tree.get(i) < tree.get(leftChild(i)))
            {
                swap(i, leftChild(i));
                break; //the end of this tree
            }
            if(rightChild(i) < tree.size())
            {
                if(tree.get(i) < tree.get(leftChild(i)) && tree.get(i) < tree.get(rightChild(i)))
                {
                    if(tree.get(leftChild(i)) > tree.get(rightChild(i)))
                    {
                        swap(leftChild(i), i);
                        i = leftChild(i);
                        continue;
                    }
                    else
                    {
                        swap(rightChild(i), i);
                        i = rightChild(i);
                        continue;
                    }
                }
                if(tree.get(i) < tree.get(rightChild(i)))
                {
                    swap(rightChild(i), i);
                    i = rightChild(i);
                    continue;
                }
                if(tree.get(i) < tree.get(leftChild(i)))
                {
                    swap(leftChild(i), i);
                    i = leftChild(i);
                    continue;
                }
            }
            break; //the end of this tree
        }
    }
    private static void insert(int i)
    {
        tree.add(i);
        SiftUp(tree.size() - 1);
    }
    private static void extractMax()
    {
        System.out.println(tree.get(1));
        swap(1, tree.size() - 1);
        tree.remove(tree.size() - 1);
        SiftDown(1);
    }
    public static void main(String[] args)
    {
        tree.add(Integer.parseInt(l.nextLine())); //number of operations
        //binary tree with dynamic array (>=1 elements)(numbering starts from one)   
        for(int i = 0; i < tree.get(0); i++)
        {
            s = l.nextLine();
            if(s.length() > 5 && s.substring(0, 6).equals("Insert"))
            {
                insert(Integer.parseInt(s.substring(7)));
            }
            if(s.length() > 9 && s.substring(0, 10).equals("ExtractMax"))
            {
                extractMax();
            }
        }
        l.close();
    }
}
class Main
{}