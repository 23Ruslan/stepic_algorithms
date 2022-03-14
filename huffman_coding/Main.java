/*кодирование Хаффмана
По данной непустой строке s длины не более 10^4, состоящей из строчных букв латинского алфавита, 
постройте оптимальный беспрефиксный код. В первой строке выведите количество различных букв k, 
встречающихся в строке, и размер получившейся закодированной строки. В следующих k строках запишите 
коды букв в формате "letter: code". В последней строке выведите закодированную строку.
Sample Input 1:
a
Sample Output 1:
1 1
a: 0
0
Sample Input 2:
abacabad
Sample Output 2:
4 14
a: 0
b: 10
c: 110
d: 111
01001100100111*/


import java.util.ArrayList;
import java.util.Scanner;
class Symbol
{
    public int frequency;
    public char letter;
    public Symbol(int frequency, char letter)
    {
        this.frequency = frequency;
        this.letter = letter;
    }
}
class huffNode
{
    int fr, parent, leftchild, rightchild;
    public huffNode()
    {
        fr = 0;
        parent = 0;
        leftchild = 0;
        rightchild = 0;
    }
}
class huffmanTree
{
    private huffNode Tree[];
    private String code[];
    private int[] Select(huffNode tree[], int end)
    {
        int two_values[] = new int[2], minn = Integer.MAX_VALUE;
        for(int i = 0; i < end; i++)
        {
            if(tree[i].parent != 0) continue;
            if(minn > tree[i].fr)
            {
                minn = tree[i].fr;
                two_values[0] = i;
            }
        }
        minn = Integer.MAX_VALUE;
        for(int i = 0; i < end; i++)
        {
            if(tree[i].parent != 0 || i == two_values[0]) continue;
            if(minn > tree[i].fr)
            {
                minn = tree[i].fr;
                two_values[1] = i;
            }
        }
        return two_values;
    }
    public void huffmanCoding(int[] w, int n)
    {
        int m = 2 * n - 1, s1, s2;
        Tree = new huffNode[m];
        for(int i = 0; i < n; i++)
        {
            Tree[i] = new huffNode();
            Tree[i].fr = w[i];
        }
        for(int i = n; i < m; i++)
        {
            Tree[i] = new huffNode();
        }
        for(int i = n; i < m; i++)
        {
            int ans[] = Select(Tree, i);
            s1 = ans[0];
            s2 = ans[1];
            Tree[s1].parent = i;
            Tree[s2].parent = i;
            Tree[i].leftchild = s1;
            Tree[i].rightchild = s2;
            Tree[i].fr = Tree[s1].fr + Tree[s2].fr;
        }
        code = new String[n];
        if(w.length == 1)
        {
            code[0] = "0";
        }
        else
        {
            for(int i = 0; i < n; i++)
            {
                StringBuilder sb = new StringBuilder();
                for(int c = i, f = Tree[i].parent; f != 0; c = f, f = Tree[f].parent)
                {
                    if(Tree[f].leftchild == c)
                    {
                        sb.append(0);
                    }
                    else
                    {
                        sb.append(1);
                    }
                }
                code[i] = sb.reverse().toString();
            }
        }
    }
    public void print(int w[], String s1, ArrayList < Symbol > symbols1)
    {
        int length_of_all_code = 0;
        for(int i = 0; i < w.length; i++)
        {
            length_of_all_code += w[i] * code[i].length();
        }
        System.out.println(length_of_all_code);
        for(int i = 0; i < w.length; i++)
        {
            System.out.println(symbols1.get(i).letter + ": " + code[i]);
        }
        for(int i = 0; i < s1.length(); i++)
        {
            for(int j = 0; j < symbols1.size(); j++)
            {
                if(s1.charAt(i) == symbols1.get(j).letter)
                {
                    System.out.print(code[j]);
                    break;
                }
            }
        }
        System.out.println();
    }
    public static void main(String args[])
    {
        String s = (new Scanner(System.in)).nextLine();
        ArrayList < Symbol > symbols = new ArrayList < Symbol > ();
        int temp3 = 0;
        symbols.add(new Symbol((byte) 1, s.charAt(0)));
        for(int i = 1; i < s.length(); i++)
        {
            temp3 = 1;
            for(int j = 0; j < symbols.size(); j++)
            {
                if(symbols.get(j).letter == s.charAt(i))
                {
                    symbols.get(j).frequency++;
                    temp3 = 0;
                    break;
                }
            }
            if(temp3 == 1)
            {
                symbols.add(new Symbol(1, s.charAt(i)));
            }
        }
        System.out.print(symbols.size() + " ");
        huffmanTree t = new huffmanTree();
        int[] w = new int[symbols.size()];
        for(int i = 0; i < symbols.size(); i++)
        {
            w[i] = symbols.get(i).frequency;
        }
        t.huffmanCoding(w, w.length);
        t.print(w, s, symbols);
    }
}
public class Main
{}