/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp2labproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Monster
 */
public class FileApitest {
     private void removeItemFromFile(String filename, String idToRemove) {
        try {
            Path filePath = Path.of(filename);
            List<String> lines = Files.readAllLines(filePath);

            List<String> updatedLines = new ArrayList<>();
            
           
            for (String line : lines) {
                String[] splitElement = line.split(",");
                String id = splitElement[0].trim();

                if (!id.equals(idToRemove)) {
                    updatedLines.add(line);
                }
            }

            Files.write(filePath, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Satır başarıyla silindi.");
        } catch (IOException e) {
            e.printStackTrace();
            // Hata durumunda uygun işlemleri yapın
        }
    }

    public static void main(String[] args) {
        String dosyaYolu = "C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\test.txt";
        String silinecekId = "123";

        FileApitest dosyaIslemleri = new FileApitest();
        dosyaIslemleri.removeItemFromFile(dosyaYolu, silinecekId);
    }
}
    

