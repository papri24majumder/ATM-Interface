package ATM_Interface;

import java.util.Scanner;
public class Atm_interface {
    int balance;
    String prevTransaction="";
    String customerName, CName;
    String customerId, CID;


    //ask() function asks the user whether they want to login or register
    void ask(){
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME");
        System.out.println("Enter 1 for Registering\nEnter 2 for Login");
        int n=sc.nextInt();

        //if the choice of the user is 1, register function will be called
        if(n==1){
            register();
        }
        //if the choice of the user is 2, login function will be called
        else if(n==2){
            login();
        }
        sc.close();
    }

    //New user has to register themselves for using different atm services
    void register(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer Name & ID for Registration: ");
        customerName=sc.next();
        customerId=sc.next();
        System.out.println("Successfully Registered");
        login();
        sc.close();
    }

    //After successful registration they need to login
    void login(){
        String cname,cID;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer Name: ");
        cname=sc.nextLine();
        System.out.println("Enter Customer Id: ");
        cID=sc.nextLine();

            //If Name & id does not match with existing username & id, it will prompt the user to register
            if(cname.equals(customerName) && cID.equals(customerId)){
                System.out.println("Login Successfully");
                showMenu();
            }
            else{
                System.out.println("User not found\nPlease Register");
                ask();
            }


        sc.close();
    }

    //Deposit function will deposit the amount to the user account
    void deposit(int amt){
        if(amt!=0){
            balance+=amt;
            String s=amt + " Deposited\n";
            prevTransaction=prevTransaction.concat(s);
        }
    }

    //withdraw function will withdaw money from the user account
    void withdraw(int amt){
        if(this.balance>amt){
            balance-=amt;
             String s=amt + " Withdrawn\n";
            prevTransaction=prevTransaction.concat(s);
        }
        else{
            System.out.println("Insufficient Balance");
        }
    }

    //It prints all the transaction that took place from user account
    void getprevTransaction(){
        if(prevTransaction.isEmpty()){
            System.out.println("No transaction");
        }
        else{
            System.out.println("\n" + prevTransaction);
        }
    }

    void transfer(int amt, String cn, String cid2){
        int b=10000;//Default amount

        //If the amount to be transfered is greater the the available amount, transaction will fail
        if(this.balance<amt){
            System.out.println("Transfer failed due to insufficient balance");
        }
        else if(amt>10000){
            System.out.println("Transfer limit is 10000");
        }
        else{
            this.balance-=amt;
            b+=amt;
             String s=amt + " Transfered\n";
            prevTransaction=prevTransaction.concat(s);
            System.out.println("Available balance of "+ this.customerName+ " is "+ this.balance);
            System.out.println("Available balance of "+ cn+ " is "+ b);
        }

    }

    //Provides user all the functionality of ATM machine
    void showMenu(){
        char ch;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome "+ customerName);
        System.out.println("You ID is "+customerId);
        do{

            System.out.println("A-->Check Balance\nB-->Deposit\nC-->Withdraw\nD-->Transaction History\nE-->Transfer Money\nF-->Exit");
            System.out.println("Enter your Choice: ");
            ch=sc.next().charAt(0);
            ch=Character.toUpperCase(ch);
            switch(ch){
                case 'A':
                System.out.println("Your Available Balane is: "+balance);
                break;
                case 'B':
                System.out.println("Enter amount to deposit: ");
                int amt=sc.nextInt();
                deposit(amt);
                break;
                case 'C':
                System.out.println("Enter amount to withdraw: ");
                int amt1=sc.nextInt();
                withdraw(amt1);
                break;
                case 'D':
                System.out.println("Transaction History");
                getprevTransaction();
                break;
                case 'E':
                System.out.println("Enter the Customer name and ID");
                String cn=sc.next();
                String cid=sc.next();
                System.out.println("Enter amount to be sent");
                int a=sc.nextInt();
                // Atm_interface ai = new Atm_interface(cn,cid);
                transfer(a,cn,cid);
                break;
                case 'F':
                System.out.println("Logging Out");
                System.exit(1);
                break;
                default:
                System.out.println("Invalid choice");
            }
        }
        while(ch!='F');
            System.out.println("ThankYou For using our services");

        sc.close();
    }
    public static void main(String args[]){
        Atm_interface ob =new Atm_interface();
        ob.ask();
    }
}

