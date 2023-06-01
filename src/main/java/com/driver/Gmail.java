package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {
    ArrayList<Mail>Inbox=new ArrayList<>();
    ArrayList<Mail>Trash = new ArrayList<>();
    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;

    }

    public Gmail(String emailId) {
        super(emailId);
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(Inbox.size()==getInboxCapacity())
        {
            // Inbox.remove(Inbox.size()-1);
            Trash.add(Inbox.remove(Inbox.size()-1));
        }
        Inbox.add(0,new Mail(date,sender,message));
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i =0 ; i < Inbox.size();i++)
        {
            Mail mail = Inbox.get(i);

            if(mail.message.equals(message))
            {

                Trash.add(new Mail(mail.date,mail.sender,mail.message));
                Inbox.remove(mail);
            }
        }


    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.size()==0)
            return null;

        Mail mail = Inbox.get(0);
        return mail.message;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.size()==0)
            return null;
        Mail mail = Inbox.get(Inbox.size()-1);
        return mail.message;


    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int mailbetween=0;
        for(int i =0 ; i < Inbox.size();i++)
        {
            Mail mail= Inbox.get(i);
            if(mail.date.compareTo(start)>=0 && mail.date.compareTo(end)<=0)
            {
                mailbetween++;
            }

        }
        return mailbetween;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();


    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;


    }
}