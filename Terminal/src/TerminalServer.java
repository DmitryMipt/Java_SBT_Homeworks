import ru.sbt.exceptions.AccountIsLockedException;
import ru.sbt.exceptions.IncorrectSumException;
import ru.sbt.exceptions.NotEnoughMoneyOnBalanceException;

import java.util.Scanner;

public class TerminalServer implements Terminal {
    private double balance;

    public TerminalServer(double balance) {
        this.balance = balance;
    }

    @Override
    public double checkBalance() throws AccountIsLockedException {
        return balance;
    }

    @Override
    public void pushMoney() throws AccountIsLockedException, IncorrectSumException {
        System.out.println("Введите сумму пополнения:");
        Scanner st = new Scanner(System.in);
        double summa = st.nextDouble();

        if (summa % 100 != 0) {
            throw new IncorrectSumException("Неверная сумма");
        }

        balance += summa;
        System.out.println("Ваш счет пополнен на " + summa);
    }


    @Override
    public void getMoney() throws AccountIsLockedException, IncorrectSumException, NotEnoughMoneyOnBalanceException {
        System.out.println("Введите сумму выдачи:");
        Scanner st = new Scanner(System.in);
        double summa = st.nextDouble();

        if (summa % 100 != 0) {
            throw new IncorrectSumException("Неверная сумма");
        }


        if (summa > balance) {
            throw new NotEnoughMoneyOnBalanceException();
        }

        balance -= summa;
        System.out.println("Выдано " + summa);
    }
}
