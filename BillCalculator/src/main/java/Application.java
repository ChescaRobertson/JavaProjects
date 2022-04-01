import java.awt.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {


    private final String url = "jdbc:postgresql://localhost/Bill_Calculator";
    private final String user = "postgres";
    private final String password = "adminpass";

    public Connection connect()  {
        Connection conn = null;
        try {
            conn =  DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void adminLogin(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String username;
        String userpassword;
        ArrayList<Admin> adminusers = new ArrayList<>();

        System.out.println("Please enter admin username: ");
        username = sc.next();
        System.out.println("Please enter admin password: ");
        userpassword = sc.next();

        String SQL = "SELECT name, password FROM public.admin";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next()) {
            Admin i = new Admin((rs.getString("name")), (rs.getString("password")));
            adminusers.add(i);
        }

        for (Admin user: adminusers) {
            if (user.getName().equals(username) && user.getPassword().equals(userpassword)) {
                System.out.println("Access granted");
                adminChoice();
            } else {
                System.out.println("Incorrect username or password");
            }
        }


    }

    private static void adminChoice() throws SQLException {
        char choice;
        Connection conn;
        boolean loggedin = true;
        ArrayList<Menu_Item> menu;

        Application app = new Application();
        conn = app.connect();


        while(loggedin) {

            Scanner sc = new Scanner(System.in);

            System.out.println("Please select an option: \nDisplay Menu (d) \tAdd to Menu (a) \nUpdate Menu Item (u) \tRemove Menu Item (r) \nLog Out(l)" );
            choice = sc.next().charAt(0);
            switch (choice) {
                case ('d'):
                    displayMenu(conn);
                    break;
                case ('l'):
                    loggedin = false;
                    break;
                case ('a'):
                    Menu_Item newItem = newMenuItem();
                    addtoMenu(conn, newItem.getName(), (float) newItem.getPrice());
                    break;
                case ('u'):
                    menu = displayMenu(conn);
                    Menu_Item updateItem = checkMenuItem(menu);
                    updateMenu(conn, updateItem.getName(), (float) updateItem.getPrice());
                    break;
                case('r'):
                    menu = displayMenu(conn);
                    Menu_Item removeItem = checkMenuItem(menu);
                    removeMenu(conn, removeItem.getName());
                    break;
                default:
                    System.out.println("Invalid selection");
            }
        }
    }

    private static Menu_Item newMenuItem() {
        Scanner sc = new Scanner(System.in);
        String name;
        Float price;

        System.out.println("Please enter the menu item name: ");
        name = sc.next();
        System.out.println("Please enter the menu item price: ");
        price = sc.nextFloat();

        Menu_Item newItem = new Menu_Item(name, price);

        return newItem;

    }

    private static Menu_Item checkMenuItem(ArrayList menu) throws SQLException {
        ArrayList<String> items = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String name = "";
        Float price = 0.00f;

        boolean checkItem = true;

        for (Object itemName : menu) {
            items.add(((Menu_Item) itemName).getName());
        }

        while (checkItem) {
            System.out.println("Please enter the menu item name: ");
            name = sc.next();
            if (items.contains(name)) {
                checkItem = false;
                System.out.println("Please enter the menu item price: ");
                price = sc.nextFloat();

            } else {
                System.out.println("Item not in menu");
            }
        }
        Menu_Item updateItem = new Menu_Item(name, price);
        return updateItem;
    }

    private static void addtoMenu(Connection conn, String name, Float price) throws SQLException {

        String SQL = "INSERT INTO public.menu (id, name, price) VALUES (DEFAULT, ?, ?)";
        PreparedStatement myStmt = conn.prepareStatement(SQL);

        myStmt.setString(1, name);
        myStmt.setFloat(2, price);

        myStmt.executeUpdate();

    }

    private static void updateMenu(Connection conn, String name, Float price) throws SQLException {

        String SQL = "UPDATE public.menu SET price = ? WHERE name = ?";
        PreparedStatement myStmt = conn.prepareStatement(SQL);


        myStmt.setFloat(1, price);
        myStmt.setString(2, name);

        myStmt.executeUpdate();

    }

    private static void removeMenu(Connection conn, String name) throws SQLException {

        String SQL = "DELETE FROM public.menu WHERE name = ?";
        PreparedStatement myStmt = conn.prepareStatement(SQL);


        myStmt.setString(1, name);

        myStmt.executeUpdate();

    }

    private static char askForChoice(){
        char choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Please select an option: \nDisplay Menu (d) \tPlace an Order (p) \nView Contact Details (c) \tAdministration Login (l) \nQuit program (q)" );
        choice = sc.next().charAt(0);
        return choice;
    }

    private static void processChoice(char choice) throws SQLException {
        ArrayList<Menu_Item> menu;
        List<String> order;
        float totalBill;
        float finaltotal;
        float individualBill;
        int tip;
        int partysize;

        Connection conn;

        Application app = new Application();
        conn = app.connect();

        switch(choice){
            case('d'):
                displayMenu(conn);
                break;
            case('l'):
                adminLogin(conn);
                break;
            case('m'):
                Menu_Item newItem = newMenuItem();
                addtoMenu(conn, newItem.getName(), (float) newItem.getPrice());
            case('p'):
                menu = displayMenu(conn);
                order = takeOrder();
                totalBill = calculateTotalBill(menu, order);
                tip = calculateTipPercentage();
                finaltotal = calculateTip(totalBill, tip);
                partysize = calculatePartySize();
                individualBill = splitBill(finaltotal, partysize);
                System.out.println(returnTotal(finaltotal, individualBill, partysize));
                System.out.println("Thank you for ordering with Java Cafe!");
                break;
            case('c'):
                System.out.println("Contact details: \nJava Cafe \n1 Java Street \nJV1 CD3 \nTel: 01234 5678");
                break;
            default:
                System.out.println("Invalid selection");
        }
    }

    private static ArrayList<Menu_Item> displayMenu(Connection conn) throws SQLException {
        ArrayList<Menu_Item> menu = new ArrayList<>();
        ArrayList<Menu_Item> item = new ArrayList<>();

        String SQL = "SELECT name, price FROM public.menu";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next()) {
            Menu_Item i = new Menu_Item((rs.getString("Name")), (Double.parseDouble(rs.getString("Price"))));
            menu.add(i);
        }
        //System.out.println(menu);
        System.out.println("Menu: ");
        menu.forEach(element -> {
            System.out.println(element.getName() + "\t\t£" + element.getPrice());
        });

        return menu;
    }

    private static List takeOrder() {
        boolean invalid = true;
        boolean more = true;
        char answer;
        List<String> order = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while(invalid) {

            while (more){
                System.out.println("What would you like to order?: ");
                order.add(sc.next());
                System.out.println("Would you like to order more? y/n: ");
                answer = sc.next().charAt(0);
                if (answer == 'n') {
                    invalid = false;
                    more = false;
                } else if (answer == 'y') {
                    invalid = false;
                } else {
                    System.out.println("Invalid selection");
                }
            }
        }
        return order;
    }

    private static int calculateTipPercentage() {
        boolean invalid = true;
        char answer;
        Scanner sc = new Scanner(System.in);
        int tip = 0;

        while (invalid) {
            System.out.println("Would you like to add a tip?: y/n");
            answer = sc.next().charAt(0);
            if (answer == 'y') {
                invalid = false;
                System.out.println("Enter the tip % (10, 15, 20)");
                tip = sc.nextInt();
            } else if (answer == 'n') {
                invalid = false;
            }else {
                System.out.println("Invalid selection");
            }
        }
        return tip;
    }

    public static float calculateTip(float totalBill, int tip) {
        float finaltotal;
        float tiptoadd;

        tiptoadd = (totalBill * (tip/100.00f));
        finaltotal = totalBill + tiptoadd;

        return finaltotal;
    }

    private static int calculatePartySize() {
        boolean invalid = true;
        char answer;
        int partysize = 0;
        Scanner sc = new Scanner(System.in);

        while (invalid) {
            System.out.println("Would you like to split the bill?: y/n ");
            answer = sc.next().charAt(0);
            if (answer == 'y') {
                invalid = false;
                System.out.println("How many guests in the party?: ");
                partysize = sc.nextInt();
            } else if (answer == 'n') {
                invalid = false;
            } else {
                System.out.println("Invalid selection");
            }
        }
        return partysize;
    }

    public static float calculateTotalBill(ArrayList menu, List order) {
        float total = 0.00f;

        for(int i = 0; i < order.size(); i++) {
            for (Object item : menu) {
                if (((Menu_Item) item).getName().equals(order.get(i))) {
                    total += ((Menu_Item) item).getPrice();
                }
            }
        }
        return total;
    }

    public static float splitBill(float finalbill, int partysize) {
        float individualBill;

        individualBill = finalbill/partysize;
        return individualBill;
    }

    private static String returnTotal(float finaltotal, float individualBill, int partysize) {
        DecimalFormat df = new DecimalFormat("###.##");
        String output = "Your total bill is £" + df.format(finaltotal);

        if (partysize > 0) {
            output += "\nEach individual should pay £" + df.format(individualBill);
        }
        return output;
    }

    public static void main(String[] args) throws SQLException {

        char choice;
        boolean running = true;

        System.out.println("Welcome to Java Cafe.");

        while(running) {
            choice = askForChoice();
            //Code to clear screen in VS Code
            //System.out.print("\033[H\033[2J");
            //System.out.flush();
            if(choice != 'q') {
                processChoice(choice);
            } else {
                running = false;
                System.out.println("Program terminating, goodbye!");
            }
        }
    }

}

//TODO
// Check validity of order (is it on menu?)
// Add UI?