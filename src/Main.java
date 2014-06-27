
import java.util.*;
import java.math.BigDecimal;
import javax.swing.SwingUtilities;

class Main {

    public static void usage()
    {
        System.out.println("distri : Generic distributor simulator");
        System.out.println("usage :");
        System.out.println("java -jar distri.jar [-h]");
        System.out.println("options :");
        System.out.println("-h : Show this help message");
        System.exit(1);
    }

    public static void main(String args[])
    {

        if(args.length > 0) usage();

        Euro euro = new Euro();
        Found<Euro> found = new Found<Euro>(euro);
        Container<Drink> container = new Container<Drink>();
        final GenericDistributor<Drink, Euro> distributor = new EuroDrinkDistributor(found, container);

        SwingUtilities.invokeLater(new Runnable()
        {   
            @Override
            public void run()
            {   
                GenericDistributorGui<Drink, Euro> window;
                window = new GenericDistributorGui<Drink, Euro>(distributor);
                window.setVisible(true);
            }   
        });

    }

}

