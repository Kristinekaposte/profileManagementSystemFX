package com.example.profilemanagementsystemfx;

import java.time.LocalDateTime;
import java.util.Date;

public class Messages{
   private int id;
   private int to_user;
   private String from_user;
   private LocalDateTime localDateTime;
   private String message;

   public Messages(String message,String from_user) {
      this.message=message;
      this.from_user= from_user;
   }

   @Override
   public String toString() {
      return "message is: "+this.message+ " user name is : "+this.from_user  + "\n" ;
   }

   public  Messages(){

       }

//      public void   Messages(int id,int to_user,int from_user, LocalDateTime localDateTime, String message){
//         this.id=id;
//         this.to_user=to_user;
//         this.from_user=from_user;
//         this.localDateTime=localDateTime;
//         this.message=message;
//   }

   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }

   public int getTo_user() {
      return this.to_user;
   }

   public void setTo_user(int to_user) {
      this.to_user = to_user;
   }

   public String getFrom_user() {
      return this.from_user;
   }

   public void setFrom_user(String from_user) {
      this.from_user = from_user;
   }

   public LocalDateTime getLocalDateTime() {
      return this.localDateTime;
   }

   public void setLocalDateTime(LocalDateTime localDateTime) {
      this.localDateTime = localDateTime;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }



}
