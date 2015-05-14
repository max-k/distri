
import java.util.*;
import java.math.BigDecimal;
import javax.swing.SwingUtilities;

/**
 * <b>Main entry point<b>
 * <p>
 * Here, you can instantiate a new distributor using
 * corresponding product and money.
 * </p>
 * @see GenericDistributor
 *
 * @author Thomas Sarboni "max-k@post.com"
 */
class Main
{

    /**
     * Command-line help method
     */
    public static void usage()
    {
        System.out.println("distri : Generic distributor simulator");
        System.out.println("usage :");
        System.out.println("java -jar distri.jar [-h]");
        System.out.println("options :");
        System.out.println("-h : Show this help message");
        System.exit(1);
    }

    /**
     * Main method
     * @param args Command-line arguments
     */
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

