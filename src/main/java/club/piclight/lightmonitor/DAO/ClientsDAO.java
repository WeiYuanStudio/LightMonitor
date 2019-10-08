package club.piclight.lightmonitor.DAO;

import java.util.Calendar;

import club.piclight.lightmonitor.Bean.ClientBean;

import java.util.ArrayList;


public class ClientsDAO {
    private static int MIN_OFFSET = 1;
    private static ClientsDAO clientsDAO = new ClientsDAO();
    private ArrayList<ClientBean> clientBeans = new ArrayList<>();

    /**
     * Private constructor because Singleton Pattern
     */
    private ClientsDAO() {
    }

    public synchronized int registerClient(ClientBean client) {
        int clientId = clientBeans.size();
        client.setId(clientBeans.size());
        clientBeans.add(client);
        return clientId;
    }

    public synchronized void refreshClient(int id, String ip) {
        try {
            ClientBean client = clientBeans.get(id);
            client.setClientIP(ip);
            client.setPkgNum(client.getPkgNum() + 1);
            Calendar now = Calendar.getInstance();
            now.add(Calendar.MINUTE, +MIN_OFFSET);
            client.setLastestOnline(now);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void infoClient(int id, String info) {
        try {
            ClientBean client = clientBeans.get(id);
            client.setClientInfo(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ClientBean> getClientList() {
        return (ArrayList<ClientBean>) clientBeans.clone();
    }

    public ClientBean getClientById(int id) {
        return clientBeans.get(id);
    }

    /**
     * Get Singleton instance
     *
     * @return clientsDAO singleton
     */
    public static ClientsDAO getInstance() {
        if (clientsDAO == null) {
            clientsDAO = new ClientsDAO();
        }
        return clientsDAO;
    }
}
