/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Monster
 */
public class ayrik_2 {
    
    public static boolean first(int arr [], int arr2 []){
       for(int i =0; i<=arr.length; i++){
           for(int j=0; j<= arr2.length; j++){
               if(arr[i]+arr2[j]<1){
                   
                   return false;
               }
               
           }
           
       }
       return true;
    }
    
    
 public static boolean sercond (int arr [], int arr2 []){
       for(int i =0; i<=arr.length; i++){
           for(int j=0; j<= arr2.length; j++){
               if(arr[i]== 2*arr2[j]){
                   
                   
                   
                   return true;
               }
               
           }
           
       }
       return false;
    }
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        int arr[]={2,4,6,8,10};
        int arr2[]={1,2,3,4,5};
        first(arr,arr2);
        sercond(arr,arr2);
        
        
    }
    
    
    
    
}
