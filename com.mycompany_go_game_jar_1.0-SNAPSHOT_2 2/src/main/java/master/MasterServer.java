// Yeni MasterServer.java
// Güncellenmiş MasterServer.java


// Güncellenmiş MasterServer.java (GoServer senkronize bağlantı sayımı ile)
package master;

import server.GoServer;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class MasterServer {

    private static final int MASTER_PORT = 9090;
    private static final Map<String, RoomInfo> roomMap = new HashMap<>();
    private static int nextPort = 12346;
    private static MasterServerUI ui;

    private static class RoomInfo {
        int port;
        GoServer server;

        RoomInfo(int port, GoServer server) {
            this.port = port;
            this.server = server;
        }
    }

    public static void main(String[] args) {
        ui = new MasterServerUI();
        ui.setVisible(true);
        ui.log("[MASTER] Master sunucu portu: " + MASTER_PORT);

        JButton listButton = new JButton("Aktif Odaları Listele");
        listButton.addActionListener(e -> showRoomList());
        ui.add(listButton, java.awt.BorderLayout.SOUTH);
        ui.revalidate();

        try (ServerSocket masterSocket = new ServerSocket(MASTER_PORT)) {
            while (true) {
                Socket client = masterSocket.accept();
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException e) {
            ui.log("[MASTER] Hata: " + e.getMessage());
        }
    }
    
    private static void handleClient(Socket client) {
    try (
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter writer = new PrintWriter(client.getOutputStream(), true)
    ) {
        String line = reader.readLine();
        ui.log("[MASTER] Alındı: " + line);

        if (line.startsWith("CREATE_ROOM#")) {
            String roomCode = line.split("#")[1];
            int roomPort = nextPort++;
            GoServer server = new GoServer();
            server.start(roomPort);
            roomMap.put(roomCode, new RoomInfo(roomPort, server));
            writer.println("ROOM_CREATED#" + roomPort);
            ui.log("[MASTER] Oda oluşturuldu: " + roomCode + " => Port " + roomPort);

        } else if (line.equals("JOIN_RANDOM")) {
            String selectedRoom = null;
            for (Map.Entry<String, RoomInfo> entry : roomMap.entrySet()) {
                GoServer server = entry.getValue().server;
                if (server != null && server.getConnectedPlayerCount() < 2) {
                    selectedRoom = entry.getKey();
                    break;
                }
            }

            if (selectedRoom != null) {
                RoomInfo info = roomMap.get(selectedRoom);
                writer.println("ROOM_FOUND#" + info.port);
                ui.log("[MASTER] Rastgele eşleşme ile bağlandı: " + selectedRoom);
            } else {
                String newRoomCode = "room_" + System.currentTimeMillis();
                int newPort = nextPort++;
                GoServer newServer = new GoServer();
                newServer.start(newPort);
                roomMap.put(newRoomCode, new RoomInfo(newPort, newServer));
                writer.println("ROOM_CREATED#" + newPort);
                ui.log("[MASTER] Tüm odalar doluydu. Yeni oda açıldı: " + newRoomCode + " => Port " + newPort);
            }

        } else if (line.startsWith("JOIN_ROOM#")) {
            String roomCode = line.split("#")[1];
            RoomInfo info = roomMap.get(roomCode);
            if (info != null) {
                writer.println("ROOM_FOUND#" + info.port);
                ui.log("[MASTER] Katılma isteği: " + roomCode + " => Port " + info.port);
            } else {
                writer.println("ERROR#Room not found.");
                ui.log("[MASTER] HATA: Oda bulunamadı: " + roomCode);
            }

        } else if (line.equals("LIST_ROOMS")) {
            sendRoomList(writer);

        } else {
            writer.println("ERROR#Unknown command.");
            ui.log("[MASTER] HATA: Bilinmeyen komut: " + line);
        }

    } catch (IOException e) {
        ui.log("[MASTER] Client işlenemedi: " + e.getMessage());
    }
}

   /* private static void handleClient(Socket client) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true)
        ) {
            String line = reader.readLine();
            ui.log("[MASTER] Alındı: " + line);

            if (line.startsWith("CREATE_ROOM#")) {
                String roomCode = line.split("#")[1];
                int roomPort = nextPort++;
                GoServer server = new GoServer();
                server.start(roomPort);
                roomMap.put(roomCode, new RoomInfo(roomPort, server));
                writer.println("ROOM_CREATED#" + roomPort);
                ui.log("[MASTER] Oda oluşturuldu: " + roomCode + " => Port " + roomPort);

            } else if (line.equals("JOIN_RANDOM")) {
                String selectedRoom = null;
                for (Map.Entry<String, RoomInfo> entry : roomMap.entrySet()) {
                    GoServer server = entry.getValue().server;
                    if (server != null && server.getConnectedPlayerCount() < 2) {
                        selectedRoom = entry.getKey();
                        break;
                    }
                }

                if (selectedRoom != null) {
                    RoomInfo info = roomMap.get(selectedRoom);
                    writer.println("ROOM_FOUND#" + info.port);
                    ui.log("[MASTER] Rastgele eşleşme ile bağlandı: " + selectedRoom);
                } else {
                    String newRoomCode = "room_" + System.currentTimeMillis();
                    int newPort = nextPort++;
                    GoServer newServer = new GoServer();
                    newServer.start(newPort);
                    roomMap.put(newRoomCode, new RoomInfo(newPort, newServer));
                    writer.println("ROOM_CREATED#" + newPort);
                    ui.log("[MASTER] Tüm odalar doluydu. Yeni oda açıldı: " + newRoomCode + " => Port " + newPort);
                }

            } else if (line.equals("LIST_ROOMS")) {
                sendRoomList(writer);

            } else {
                writer.println("ERROR#Unknown command.");
                ui.log("[MASTER] HATA: Bilinmeyen komut: " + line);
            }

        } catch (IOException e) {
            ui.log("[MASTER] Client işlenemedi: " + e.getMessage());
        }
    }*/

    private static void sendRoomList(PrintWriter writer) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, RoomInfo> entry : roomMap.entrySet()) {
            sb.append(entry.getKey()).append("\n");
        }
        writer.println("ROOM_LIST#" + sb.toString().trim());
        ui.log("[MASTER] Oda listesi gönderildi.");
    }

    private static void showRoomList() {
        StringBuilder sb = new StringBuilder();
        for (String room : roomMap.keySet()) {
            sb.append(room).append("\n");
        }
        String[] roomArray = sb.toString().split("\n");
        if (roomArray.length == 0 || roomArray[0].isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aktif oda yok.", "Oda Listesi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JList<String> list = new JList<>(roomArray);
            JOptionPane.showMessageDialog(null, new JScrollPane(list), "Aktif Odalar", JOptionPane.PLAIN_MESSAGE);
        }
    }
}



/*package master;

import server.GoServer;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class MasterServer {

    private static final int MASTER_PORT = 9090;
    private static final Map<String, RoomInfo> roomMap = new HashMap<>();
    private static int nextPort = 12346;
    private static MasterServerUI ui;

    private static class RoomInfo {
        int port;
        int playerCount;

        RoomInfo(int port) {
            this.port = port;
            this.playerCount = 0;
        }
    }

    public static void main(String[] args) {
        ui = new MasterServerUI();
        ui.setVisible(true);
        ui.log("[MASTER] Master sunucu portu: " + MASTER_PORT);

        JButton listButton = new JButton("Aktif Odaları Listele");
        listButton.addActionListener(e -> showRoomList());
        ui.add(listButton, java.awt.BorderLayout.SOUTH);
        ui.revalidate();

        try (ServerSocket masterSocket = new ServerSocket(MASTER_PORT)) {
            while (true) {
                Socket client = masterSocket.accept();
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException e) {
            ui.log("[MASTER] Hata: " + e.getMessage());
        }
    }

    private static void handleClient(Socket client) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true)
        ) {
            String line = reader.readLine();
            ui.log("[MASTER] Alındı: " + line);

            if (line.startsWith("CREATE_ROOM#")) {
                String roomCode = line.split("#")[1];
                int roomPort = nextPort++;
                RoomInfo info = new RoomInfo(roomPort);
                info.playerCount++;
                roomMap.put(roomCode, info);
                new Thread(() -> startRoomServer(roomPort)).start();
                writer.println("ROOM_CREATED#" + roomPort);
                ui.log("[MASTER] Oda oluşturuldu: " + roomCode + " => Port " + roomPort);

            } else if (line.equals("JOIN_RANDOM")) {
                String selectedRoom = null;
                for (Map.Entry<String, RoomInfo> entry : roomMap.entrySet()) {
                    if (entry.getValue().playerCount < 2) {
                        selectedRoom = entry.getKey();
                        break;
                    }
                }

                if (selectedRoom != null) {
                    RoomInfo info = roomMap.get(selectedRoom);
                    info.playerCount++;
                    writer.println("ROOM_FOUND#" + info.port);
                    ui.log("[MASTER] Rastgele eşleşme ile bağlandı: " + selectedRoom);
                } else {
                    String newRoomCode = "room_" + System.currentTimeMillis();
                    int newPort = nextPort++;
                    RoomInfo newInfo = new RoomInfo(newPort);
                    newInfo.playerCount++;
                    roomMap.put(newRoomCode, newInfo);
                    new Thread(() -> startRoomServer(newPort)).start();
                    writer.println("ROOM_CREATED#" + newPort);
                    ui.log("[MASTER] Tüm odalar doluydu. Yeni oda açıldı: " + newRoomCode + " => Port " + newPort);
                }

            } else if (line.equals("LIST_ROOMS")) {
                sendRoomList(writer);

            } else {
                writer.println("ERROR#Unknown command.");
                ui.log("[MASTER] HATA: Bilinmeyen komut: " + line);
            }

        } catch (IOException e) {
            ui.log("[MASTER] Client işlenemedi: " + e.getMessage());
        }
    }

    private static void sendRoomList(PrintWriter writer) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, RoomInfo> entry : roomMap.entrySet()) {
            sb.append(entry.getKey()).append("\n");
        }
        writer.println("ROOM_LIST#" + sb.toString().trim());
        ui.log("[MASTER] Oda listesi gönderildi.");
    }

    private static void showRoomList() {
        StringBuilder sb = new StringBuilder();
        for (String room : roomMap.keySet()) {
            sb.append(room).append("\n");
        }
        String[] roomArray = sb.toString().split("\n");
        if (roomArray.length == 0 || roomArray[0].isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aktif oda yok.", "Oda Listesi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JList<String> list = new JList<>(roomArray);
            JOptionPane.showMessageDialog(null, new JScrollPane(list), "Aktif Odalar", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private static void startRoomServer(int port) {
        new Thread(() -> GoServer.start(port)).start();
        ui.log("[MASTER] Oda sunucusu başlatıldı (thread), port: " + port);
    }
}*/



/*package master;

import server.GoServer;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class MasterServer {

    private static final int MASTER_PORT = 9090;
    private static final Map<String, Integer> roomMap = new HashMap<>();
    private static int nextPort = 12346; // Oda sunucuları 12346'dan başlasın

    private static MasterServerUI ui;

    public static void main(String[] args) {
        ui = new MasterServerUI();
        ui.setVisible(true);
        ui.log("[MASTER] Master sunucu portu: " + MASTER_PORT);

        JButton listButton = new JButton("Aktif Odaları Listele");
        listButton.addActionListener(e -> showRoomList());
        ui.add(listButton, java.awt.BorderLayout.SOUTH);
        ui.revalidate();

        try (ServerSocket masterSocket = new ServerSocket(MASTER_PORT)) {
            while (true) {
                Socket client = masterSocket.accept();
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException e) {
            ui.log("[MASTER] Hata: " + e.getMessage());
        }
    }

    private static void handleClient(Socket client) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true)
        ) {
            String line = reader.readLine();
            ui.log("[MASTER] Alındı: " + line);

            if (line.startsWith("CREATE_ROOM#")) {
                String roomCode = line.split("#")[1];
                if (!roomMap.containsKey(roomCode)) {
                    int roomPort = nextPort++;
                    roomMap.put(roomCode, roomPort);
                    new Thread(() -> startRoomServer(roomPort)).start();
                    writer.println("ROOM_CREATED#" + roomPort);
                    ui.log("[MASTER] Oda oluşturuldu: " + roomCode + " => Port " + roomPort);
                } else {
                    writer.println("ERROR#Room already exists.");
                    ui.log("[MASTER] HATA: Oda zaten mevcut: " + roomCode);
                }

            } else if (line.startsWith("JOIN_ROOM#")) {
                String roomCode = line.split("#")[1];
                if (roomMap.containsKey(roomCode)) {
                    int port = roomMap.get(roomCode);
                    writer.println("ROOM_FOUND#" + port);
                    ui.log("[MASTER] Katılma isteği: " + roomCode + " => Port " + port);
                } else {
                    writer.println("ERROR#Room not found.");
                    ui.log("[MASTER] HATA: Oda bulunamadı: " + roomCode);
                }

            } else if (line.equals("LIST_ROOMS")) {
                sendRoomList(writer);
            } else {
                writer.println("ERROR#Unknown command.");
                ui.log("[MASTER] HATA: Bilinmeyen komut: " + line);
            }

        } catch (IOException e) {
            ui.log("[MASTER] Client işlenemedi: " + e.getMessage());
        }
    }

    private static void sendRoomList(PrintWriter writer) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : roomMap.entrySet()) {
            sb.append(entry.getKey()).append("\n");
        }
        writer.println("ROOM_LIST#" + sb.toString().trim());
        ui.log("[MASTER] Oda listesi gönderildi.");
    }

    private static void showRoomList() {
        StringBuilder sb = new StringBuilder();
        for (String room : roomMap.keySet()) {
            sb.append(room).append("\n");
        }
        String[] roomArray = sb.toString().split("\n");
        if (roomArray.length == 0 || roomArray[0].isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aktif oda yok.", "Oda Listesi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JList<String> list = new JList<>(roomArray);
            JOptionPane.showMessageDialog(null, new JScrollPane(list), "Aktif Odalar", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private static void startRoomServer(int port) {
        new Thread(() -> GoServer.start(port)).start();
        ui.log("[MASTER] Oda sunucusu başlatıldı (thread), port: " + port);
    }
} */
