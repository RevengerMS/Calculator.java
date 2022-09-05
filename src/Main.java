
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CalcException {
        Scanner inp = new Scanner(System.in);
        String result, inpSting;
        do{
            System.out.print("Вычислить: ");
            inpSting = inp.nextLine().toUpperCase();
            result = calc(inpSting);
            if (!result.isEmpty()) {
                System.out.println(result);
            }
        }while (!result.isEmpty());
        inp.close();
    }

    public static String calc(String input) throws CalcException {
        char ch, operation = 0;
        String[] params = {"", ""};
        int res;
        byte parNum = 0;
        for(int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            switch (ch) {
                case '+':
                case '-':
                case '*':
                case '/':
                    if (operation == 0) {
                        operation = ch;
                        parNum++;
                        break;
                    }
                    else
                        throw new CalcException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                default:
                    params[parNum] = params[parNum]+ch;
            }
        }
        if (operation == 0)
            throw new CalcException("Строка не является математической операцией");
        CalcNumber op1 = new CalcNumber(params[0]);
        CalcNumber op2 = new CalcNumber(params[1]);
        if ((op1.getNumVal() == 0) || (op2.getNumVal() == 0))
            throw new CalcException("Неверный операнд");
        if (op1.getStrVal().isEmpty() != op2.getStrVal().isEmpty())
            throw new CalcException("Используются одновременно разные системы счисления");
        res = switch (operation) {
            case '+' -> op1.getNumVal() + op2.getNumVal();
            case '-' -> op1.getNumVal() - op2.getNumVal();
            case '*' -> op1.getNumVal() * op2.getNumVal();
            default -> op1.getNumVal() / op2.getNumVal();
        };
        if (!op1.getStrVal().isEmpty()) {
            if (res > 0)
                return CalcNumber.toRoman(res);
            else
                throw new CalcException("В римской системе нет отрицательных чисел и 0");
        }
        return Integer.toString(res);
    }
}

