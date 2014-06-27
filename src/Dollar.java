
import java.util.*;
import java.math.BigDecimal;

public class Dollar implements Money
{

    private TreeMap<BigDecimal, String> coins;
    private char symbol;

    protected Dollar()
    {
        this.symbol = '$';
        this.coins = new TreeMap<BigDecimal, String>();
        this.coins.put(new BigDecimal("0.01"), "1 penny");
        this.coins.put(new BigDecimal("0.05"), "1 nickel");
        this.coins.put(new BigDecimal("0.10"), "1 dime");
        this.coins.put(new BigDecimal("0.25"), "1 quarter");
        this.coins.put(new BigDecimal("1"), "1 dollar");
    }

    public char getSymbol()
    {
        return this.symbol;
    }

    public String getCoin(BigDecimal value)
    {
        return this.coins.get(value);
    }

    public Iterator<Map.Entry<BigDecimal, String>> getCoins()
    {
        return this.coins.descendingMap().entrySet().iterator();
    }

}

