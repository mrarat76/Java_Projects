/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.endoftermproject;

/**
 *
 * @author Monster
 */
public class LabMan extends Users {
   
   public LabMan(String name,String surname, String password,String username){
     super(name,surname,password,username);
     
     
     
     
    }
   
    
    int usernamecount=1;
  
   @Override
    public void setUsername(String username){
        String add="LabMan_";
        this.username=add+ usernamecount+username ;
        usernamecount++;
    }
    
    
   @Override
    public String getUsername() {
        return username;
    }

   @Override
   
   
   
   
   
   public void setPassword(String password){
        
        String add="LabMan";
        
        this.password= add+password;
        
    }
    
    /**
     *
     * @return
     */
    @Override
   public String getPassword() {
        return password;
    }

    
   
   public void labresultreport(Patients pat1){
        pat1.setLabresult("+");
        
    }
    
   @Override
      public void setLabReport (String username, String labpresult){
      
    for (Patients patient : PatientArray) {
    if (patient.getUsername ().equals(username)) {
        patient.setLabresult(labpresult);
        patient.requested=-1;
    }}
     

}
      
      public void displayRequests(){
          for(Patients patient: PatientArray){
              System.out.println( patient.getUsername()+ patient.getrequest());
              if(patient.getrequest()<1){
                  System.out.println("There is any request for"+ patient.getUsername());
              }
              
          }
      }
      
  
    
}
