package model;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class UrMageren {

    private final File file = new File("/Users/deniz/Documents/GitHub/WatchCompany/src/resources/database.txt");
    private final PrintWriter printWriter;
    private final Scanner fileReader;

    public UrMageren() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        this.printWriter = new PrintWriter(fileOutputStream);
        this.fileReader = new Scanner(fileInputStream);
    }

    public void load(WatchManager watchManager) throws IOException {

        if (!fileReader.hasNext()){
            return;
        }
        int balance = fileReader.nextInt();
        watchManager.getBankAccount().setBalance(balance);

        while (fileReader.hasNext()){
            String[] arr = fileReader.nextLine().split(" ");
                int watchId = Integer.parseInt(arr[0]);
                String watchName = arr[1];
                int buyPrice = Integer.parseInt(arr[2]);
                int cargo = Integer.parseInt(arr[3]);
                int tariff = Integer.parseInt(arr[4]);

                Watch watch = new Watch(watchName, buyPrice, cargo, tariff);

                watch.setId(watchId);
                watchManager.addWatch(watch);
        }
        Files.write(Paths.get(file.getPath()), "".getBytes());
    }

    public void save(WatchManager watchManager ){
        for (Watch watch : watchManager.watches()){
            printWriter.println(watch.getId() + " " + watch.getName() + " " + watch.getBuyPrice() + " " + watch.getCargo() + " " + watch.getTariff());
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        UrMageren urMageren = new UrMageren();



        BankAccount bankAccount = new BankAccount(0);
        WatchManager watchManager = new WatchManager(bankAccount);
        urMageren.load(watchManager);


        while (true){
            System.out.println("Press 1 in order to order a watch");
            System.out.println("Press 2 in order to sell a watch ");
            System.out.println("Press 3 in order to watch your inventory");
            System.out.println("Press 4 in order to watch you balance!");
            System.out.println("Press 5 in order to withdraw from balance");
            System.out.println("Press 6 in other to deposit to balance");
            System.out.println("Press 7 in order to save your shop");

            int choice = scanner.nextInt();

            switch (choice){
                case 1:

                    System.out.println("Simply type the name of the watch: ");
                    String name = scanner.next();
                    System.out.println("Name is: " + name);

                    System.out.println("Simply type in the buy price");
                    int buyPrice = scanner.nextInt();

                    System.out.println("Simply type in the cargo price per watch");
                    int cargoPrice = scanner.nextInt();

                    System.out.println("Simply type in the tariff per watch");
                    int tariffPrice = scanner.nextInt();

                    Watch watch = new Watch(name, buyPrice, cargoPrice, tariffPrice);
                    if (!watchManager.buyWatch(watch)){
                        break;
                    }
                    watchManager.addWatch(watch);
                    System.out.println("You paid: " + watch.getBuyPriceTotal() + " for the watch");
                    break;

                case 2:
                    watchManager.printWatches();
                    System.out.println("Select the id of the watch you wish to sell by typing the id");
                    int id = scanner.nextInt();
                    System.out.println("Now type the sellprice");
                    int sellPrice = scanner.nextInt();

                    watchManager.sellWatch(watchManager.findWayById(id), sellPrice);
                    break;

                case 3:
                    System.out.println("here is an overview of your inventory");
                        watchManager.printWatches();
                    break;

                case 4:
                    System.out.println("Your balance is: " + bankAccount.getBalance());
                    break;
                case 5:
                    System.out.println("Type the amount you wish to withdraw");
                    int amount = scanner.nextInt();
                    bankAccount.withdraw(amount);
                    System.out.println("Your balance after withdrawal: " + bankAccount.getBalance());
                    break;
                case 6:
                    System.out.println("Type the amount you wish to deposit");
                    int deposit = scanner.nextInt();
                    bankAccount.setBalance(deposit);
                    System.out.println("Your balance after depositing: " + bankAccount.getBalance());
                    break;
                case 7:
                    urMageren.save(watchManager);
                    System.out.println("You successfully saved the shop!");
                    break;
            }
        }
    }

    public static void addComponent(Class<? extends JComponent> type, String labelName, JPanel panel){
        if (type == JTextField.class){
            JTextField textField = new JTextField();
            textField.setSize(new Dimension(20,20));
            panel.add(textField);

        }else if (type == JLabel.class) {
            JLabel label = new JLabel(labelName);
            panel.add(label);

        }

        /*JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(5,2));



       addComponent(JLabel.class, "Watch Name", panel);
        addComponent("TextField.class", null, panel);
        addComponent("JLabel.class", "Buy Price", panel);
        addComponent("TextField.class", null, panel);
        addComponent("JLabel.class", "Cargo per watch", panel);
        addComponent("TextField.class", null, panel);
        addComponent("JLabel.class", "Tariff per watch", panel);
        addComponent("TextField.class", null, panel);

        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);*/
    }
}
