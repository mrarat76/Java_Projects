/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.endoftermproject;

import java.util.Scanner;

/**
 *
 * @author Monster
 */
public class EndOfTermProject {

    private static final Scanner sc = new Scanner(System.in);
    private static int DataFromUser = 0;

    public enum AccountType {
        PATIENT,
        DOCTOR,
        LABMAN,
        ADMIN,

    }
    public static AccountType accountType;
    private static Users userAccount;

    public static void SetUserAccount(Users user) {
        userAccount = user;
    }

    public static void main(String[] args) {

        System.out.println(Colors.ConsoleColors.RED_BOLD
                + "WELCOME TO LAB AUTOMATION BY MR.ARAT"
                + Colors.ConsoleColors.RESET);
        System.out.println("Be careful in any wrong press you will be kicked out from your account! ");
        System.out.println("Please press enter to run system.");
        
        Admin admin = new Admin("Admin", "Admin", "Admin", "Admin");
        admin.setName("Admin");
        admin.setUsername("admin");
        admin.setSurname("adm");
        admin.setPassword("admin");
        Users.AdminArray.add(admin);

        EndOfTermProject newob = new EndOfTermProject();

        while (true) {
            if (accountType == null) {
                LoginVerifyTest();

            } else {
                switch (accountType) {
                    case LABMAN -> {
                        LabManInterface();
                    }
                    case PATIENT -> {
                        PatientConsoleInterface();
                    }
                    case DOCTOR -> {
                        DoctorConsoleInterface();
                    }
                    case ADMIN -> {
                        AdminConsoleInterface();

                    }
                }
            }
        }

    }

    private static void LoginVerifyTest() {
        sc.nextLine(); // Error
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        LogIn.LogInCheck(username, password);

        if (accountType == null) {
            System.out.println("Account does not exist. Press enter for try again.");
        }
    }

    private static void LabManInterface() {
        System.out.println("Patients first!");

        try {
            LabMan LabManAc = (LabMan) userAccount;

            while (LabManAc != null) {
                System.out.println("Press 1 to add patient");
                System.out.println("Press 2 to display requests");
                System.out.println("Press 3 to log out.");

                DataFromUser = ValueFromUser(3);

                switch (DataFromUser) {

                    case 1 -> {
                        System.out.println("Enter username of patient:");
                        String username = sc.nextLine();
                        System.out.println("Enter labresult of patient");
                        String labpresult = sc.nextLine();

                        LabManAc.setLabReport(username, labpresult);
                        continue;
                    }

                    case 2 -> {
                        LabManAc.displayRequests();
                        continue;
                    }

                    case 3 -> {
                        userAccount = null;
                        LabManAc = null;
                        accountType = null;
                        LoginVerifyTest();
                        return;
                    }

                    default -> {
                        System.out.println("You entered wrong! Check again.");

                        return;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Oooops! It looks like there is a problem.");
            LoginVerifyTest();
        }
    }

    private static void PatientConsoleInterface() {
        System.out.println("Welcome sir");
        try {
            Patients patientAc = (Patients) userAccount;

            while (patientAc != null) {
                System.out.println(" Press 1 to reach your lab report");
                System.out.println("Press 2 for log out.");

                DataFromUser = ValueFromUser(2);

                switch (DataFromUser) {

                    case 1 -> {
                        if (patientAc.requested < 1) {
                            patientAc.getLabresult();
                        } else {
                            System.out.print("Your lab result is in request.");
                            continue;
                        }
                    }
                    case 2 -> {
                        userAccount = null;
                        patientAc = null;
                        accountType = null;
                        LoginVerifyTest();
                        return;
                    }
                    default -> {
                        System.out.println("You pressed write wrong button. May you press true one?");
                        return;
                    }

                }

            }

        } catch (Exception e) {
            System.out.println("Oooops! It looks like there is a problem.");
            LoginVerifyTest();
        }

    }

    public static void DoctorConsoleInterface() {
        System.out.println("Welcome doctor.");

        try {
            Doctors AccountDoctor = (Doctors) userAccount;

            while (AccountDoctor != null) {
                System.out.println("Press 1 to create Patient.");
                System.out.println("Press 2 to view Patients.");
                System.out.println("Press 3 to request laboratory test for Patient.");
                System.out.println("Press 4 to view Patient.");
                System.out.println("Press 5 to view lab report of Patient. ");
                System.out.println("Press 6 to exit from account.");

                DataFromUser = ValueFromUser(6);

                switch (DataFromUser) {

                    case 1 -> {
                        System.out.print("Please enter name of patient. ");
                        String patientname = sc.nextLine();
                        System.out.print("Please  enter surname of the patient. ");
                        String patientsurname = sc.nextLine();
                        System.out.print("Please set password for the patient."+ "Be careful system automatically adds"+ "Pat_"+ "at the beginning of the password");
                        String patientpassword = sc.nextLine();
                        System.out.print("Please set username for the patient."+ "Be careful system automatically adds"+ "Pat_"+ "at the beginning of the username, and adds number according to patients row number.");
                        String patientusername = sc.nextLine();

                        AccountDoctor.createPatient(patientname, patientsurname, patientpassword, patientusername);
                        continue;
                    }

                    case 2 -> {
                        AccountDoctor.displayPatients();

                        continue;
                    }

                    case 3 -> {
                        System.out.println("Please enter name of Patient");
                        String requestname = sc.nextLine();

                        AccountDoctor.requestLabTest(requestname);
                        continue;
                    }

                    case 4 -> {
                        System.out.println("Please enter username of Patient to reach all information about he/she.");
                        String requestusername = sc.nextLine();
                        AccountDoctor.displayapatient(requestusername);
                        continue;
                    }

                    case 5 -> {
                        System.out.println("Please enter username of Patient to reach lab result of him/her.");
                        String reachlabresultusername = sc.nextLine();
                        AccountDoctor.displayLabreportofapatient(reachlabresultusername);
                        continue;
                    }

                    case 6 -> {
                        userAccount = null;
                        AccountDoctor = null;
                        accountType = null;
                        LoginVerifyTest();
                        return;
                    }

                    default -> {
                        System.out.println("Ooops! Something went wrong.");
                        return;
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Oooops! It looks like there is a problem.");
            LoginVerifyTest();
        }

    }

    private static void AdminConsoleInterface() {
        System.out.println("THIS IS ADMIN INTERFACE.");
        System.out.println("PLEASE CHANGE INFORMATIONS CAREFULLY!");

        try {
            Admin ADMINAccount = (Admin) userAccount;

            while (ADMINAccount != null) {
                System.out.println("Please press 1 to add Doctor.");
                System.out.println("Please press 2 to add LabMan");
                System.out.println("Please press 3 display Doctors");
                System.out.println("Please press 4 to display LabMans");
                System.out.println("Please press 5 to remove Doctor by username");
                System.out.println("Please press 6 to remove LabMan by username");
                System.out.println("Please press 7 to exit from Admin Interface.");

                DataFromUser = ValueFromUser(7);
                switch (DataFromUser) {


                    case 1 -> {
                        System.out.println("Enter name for new doctor.");
                        String docname = sc.nextLine();
                        System.out.println("Enter surname for new doctor.");
                        String docsurname = sc.nextLine();
                        System.out.println("Enter password for new doctor."+"Be careful system automatically adds"+ "Doc_"+ "at the beginning of the password");
                        String docpassword = sc.nextLine();
                        System.out.println("Enter username for new doctor."+"Be careful system automatically adds"+ "Doc_"+ "at the beginning of the username, and adds number according to patients row number.");
                        String docusername = sc.nextLine();

                        ADMINAccount.createDoctor(docname, docsurname, docpassword, docusername);
                        continue;
                    }

                    case 2 -> {
                        System.out.println("Enter name for new LabMan.");
                        String LBname = sc.nextLine();
                        System.out.println("Enter surname for new LabMan.");
                        String LBsurname = sc.nextLine();
                        System.out.println("Enter password for new LabMan.");
                        String LBpassword = sc.nextLine();
                        System.out.println("Enter username for new LabMan.");
                        String LBusername = sc.nextLine();

                        ADMINAccount.createLabMan(LBname, LBsurname, LBpassword, LBusername);
                        continue;
                    }

                    case 3 -> {
                        ADMINAccount.displayDoctor();
                        continue;
                    }
                    case 4 -> {
                        ADMINAccount.displayLabman();
                        continue;
                    }

                    case 5 -> {
                        System.out.println("Enter username which belongs to doctor.");
                        String removedoc = sc.nextLine();
                        ADMINAccount.removedoctorbyusername(removedoc);
                        continue;
                    }

                    case 6 -> {
                        System.out.println("Enter username of LabMan for remove.");
                        String removeLbmn = sc.nextLine();
                        ADMINAccount.removeLabManbyusername(removeLbmn);
                        continue;
                    }
                    case 7 -> {
                        userAccount = null;
                        ADMINAccount = null;
                        accountType = null;
                        LoginVerifyTest();
                        return;
                    }

                    default -> {
                        System.out.println("Ooops! Something went wrong.");
                        return;
                    }

                }
            }

        } catch (Exception e) {
            System.out.println("Oooops! It looks like there is a problem.");
            LoginVerifyTest();

        }
    }

    private static int ValueFromUser(int limnumber) {
        int input;

        while (true) {
            input = sc.nextInt();
            if (input > 0 && input <= limnumber) {
                break;
            }
        }

        return input;
    }
}
