/*
декодирование Хаффмана
Восстановите строку по её коду и беспрефиксному коду символов. 
В первой строке входного файла заданы два целых числа k и l через пробел —
 количество различных букв, встречающихся в строке, и размер получившейся закодированной строки, 
соответственно. В следующих k строках записаны коды букв в формате "letter: code".
 Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке.
 В качестве букв могут встречаться лишь строчные буквы латинского алфавита; каждая из этих 
букв встречается в строке хотя бы один раз. Наконец, в последней строке записана закодированная строка. 
Исходная строка и коды всех букв непусты. Заданный код таков, что закодированная строка имеет 
минимальный возможный размер.
В первой строке выходного файла выведите строку s. Она должна состоять из строчных 
букв латинского алфавита. Гарантируется, что длина правильного ответа не превосходит 10^4 символов.
Sample Input 1:
1 1
a: 0
0
Sample Output 1:
a
Sample Input 2:
4 14
a: 0
b: 10
c: 110
d: 111
01001100100111
Sample Output 2:
abacabad
*/


import java.util.Scanner;
import java.util.ArrayList;
class Symbol
{
    public int frequency;
    public char letter;
    public String letter_code;
    public Symbol(int frequency, char letter, String letter_code)
    {
        this.frequency = frequency;
        this.letter = letter;
        this.letter_code = letter_code;
    }
}
public class Main
{
    public static void main(String args[])
    {
        int number_of_different_symbols = 0;
        Scanner sc = new Scanner(System.in);
        String temp9 = sc.nextLine();
        for(int i = 0; i < temp9.length(); i++)
        {
            if(temp9.charAt(i) == ' ')
            {
                number_of_different_symbols = Integer.parseInt(temp9.substring(0, i));
                break;
            }
        }
        ArrayList < Symbol > symbols = new ArrayList < Symbol > ();
        for(int i = 0; i < number_of_different_symbols; i++)
        {
            String temp7 = sc.nextLine();
            symbols.add(new Symbol(0, temp7.charAt(0), temp7.substring(3)));
        }
        String all_code = sc.nextLine();
        while(all_code.length() != 0)
        {
            for(int i = 0; i < number_of_different_symbols; i++)
            {
                if(all_code.length() >= symbols.get(i).letter_code.length())
                {
                    if(symbols.get(i).letter_code.equals(all_code.substring(0, symbols.get(i).letter_code.length())))
                    {
                        all_code = all_code.substring(symbols.get(i).letter_code.length());
                        System.out.print(symbols.get(i).letter);
                        break;
                    }
                }
            }
        }
    }
}