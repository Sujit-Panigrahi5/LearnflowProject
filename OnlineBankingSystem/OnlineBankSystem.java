package OnlineBankingSystem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OnlineBankSystem {

    

    public class CreteAccount {
        private String name;
        private StringBuilder id;
        private String mode;
        private int balance = 0;
        private int transfer = 0;
        ArrayList<trnsaction> history = new ArrayList<>();

        // personal info 
        public void showPersonalInfo(){
            System.out.println("The name is : "+ this.name);
            System.out.println("The id is : " + this.id);
            System.out.println("Account type " + this.mode);
            System.out.println("Balance : " + this.balance);
        }

        // customize  your name method
        public void customizeYourName(String newname) {
            this.name = newname;
            for (int i = 0; i < this.history.size(); i++) {
                this.history.get(i).sender = newname;
            }
        }

        // deposite balance
        public void deposite(int amount) {
            this.balance += amount;
            System.out.println("Your new balance is :" + this.balance);

            trnsaction s = new trnsaction(this.name, this.id, amount, "deposite");
            this.history.add(s);

        }

        // withdraw balance
        public void withdraw(int amount) {
            if (this.balance == 0) {
                System.out.println("you cann't with draw because you have zero balance .");
                return;
            } else if (this.balance < amount) {
                System.out.println(this.balance + " you have not enough money ");
                return;

            }

            this.balance -= amount;
            System.out.println("Your new balance is :" + this.balance);
            trnsaction s = new trnsaction(this.name, this.id, amount, "Withdraw");
            this.history.add(s);
            return;

        }

        // check balance
        public void checkbalance() {
            System.out.println("Your balance is " + this.name + " :" + this.balance);
        }

        // fund trans

        int no[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        private StringBuilder createid() {
            StringBuilder id = new StringBuilder("");
            for (int i = 0; i < 10; i++) {
                int a = sendid();

                id.append(a);

            }
            return id;

        }

        private int sendid() {
            double random = Math.random();

            return (int) Math.floor(random * no.length);
        }

        public class trnsaction {
            String sender;
            StringBuilder senderid;
            String reciver;
            StringBuilder reciverid;
            String time;
            int amount;
            StringBuilder trnsactionid;
            String type;

            

            public trnsaction(String sender, StringBuilder senderid, String reciver, StringBuilder reciverid,
                    int amount, String type) {
                this.sender = sender;
                this.senderid = senderid;
                this.reciver = reciver;
                this.reciverid = reciverid;
                this.type = type;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                this.time = dtf.format(now);
                this.amount = amount;
                this.trnsactionid = createid();
            }

            public trnsaction(String holder, StringBuilder id, int amount, String type) {
                this.sender = holder;
                this.senderid = id;
                this.type = type;

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                this.time = dtf.format(now);
                this.amount = amount;
                this.trnsactionid = createid();
            }

        }

        // fund transaction method
        public void transfer(CreteAccount sender, CreteAccount reciver, int amount) {
            if (sender.balance < amount || sender.balance <= 0) {
                System.out.println(sender.name + "has not enough money for transfer");
                return;
            }
            sender.balance -= amount;
            reciver.balance += amount;

            String name = sender.name;
            StringBuilder id = sender.id;
            String reciv = reciver.name;
            StringBuilder id2 = reciver.id;

            trnsaction s = new trnsaction(name, id, reciv, id2, amount, "One to One transtion");
            sender.history.add(s);

            // trnsaction(sender.name,sender.id,reciver.name,reciver.id,amount);
        }

        // to check transaction history
        public void checkTransactionHistory() {
            for (int i = 0; i < this.history.size(); i++) {
                System.out.println("Transaction " + (i + 1));
                System.out.println("--------");
                System.out.println("Date : " + this.history.get(i).time);

                if (this.history.get(i).type == "Withdraw" || this.history.get(i).type == "deposite") {
                    if(this.history.get(i).type == "Withdraw"){
                        System.out.println("From " + this.history.get(i).sender + " And id is " + this.history.get(i).senderid);
                    } else{
                        System.out.println("To " + this.history.get(i).sender + " And id is " + this.history.get(i).senderid);
                    }
                    
                    System.out.println("Transcation Type :" + this.history.get(i).type);
                    System.out.println("Amount : " + this.history.get(i).amount);
                } else {
                    System.out.println("From " + this.history.get(i).sender + " And id is " + this.history.get(i).senderid);
                    System.out.println("To " + this.history.get(i).reciver + " And id is " + this.history.get(i).reciverid);
                    System.out.println("Transcation id is :" + this.history.get(i).trnsactionid);
                    System.out.println("Transcation Type :" + this.history.get(i).type);
                    System.out.println("Amount : " + this.history.get(i).amount);

                }
                System.out.println("____________________________________________");
                System.out.println();
                System.out.println();
            }
        }

        // cunstructor function
        public CreteAccount(String name, String mode) {
            this.name = name;
            this.mode = mode;
            this.id = createid();
        }

    }

    public static void main(String[] args) {
        
        OnlineBankSystem sss = new OnlineBankSystem();

        
        CreteAccount sujit = sss.new CreteAccount("sujit", "saving");
        CreteAccount sumit = sss.new CreteAccount("sumit", "Saving");
        sujit.deposite(344);
        sujit.withdraw(20);
        sujit.checkbalance();
        sujit.transfer(sujit, sumit, 200);
        sujit.checkbalance();
        sujit.checkbalance();
        sujit.checkTransactionHistory();
        System.out.println(sujit.balance);
        sujit.customizeYourName("Sujit Panigrahi");
        sujit.checkTransactionHistory();
        System.out.println("print the personal info of sujit ");
        sujit.showPersonalInfo();

    }
}