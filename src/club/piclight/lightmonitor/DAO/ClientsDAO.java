package club.piclight.lightmonitor.DAO;

import java.util.Calendar;

import club.piclight.lightmonitor.Bean.ClientBean;

import java.util.ArrayList;


public class ClientsDAO {
    private static int MIN_OFFSET = 1;
    private static ClientsDAO clientsDAO = new ClientsDAO();
    private ArrayList<ClientBean> clientBeans = new ArrayList<ClientBean>();

    private ClientsDAO() {
        ClientBean client = new ClientBean();
        client.setClientName("Adam's PC");
        client.setClientIP("1.1.1.1");
        client.setClientInfo("Windows 10");
        client.setPkgNum(1);
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, +MIN_OFFSET);
        client.setLastestOnline(now);
        registerClient(client);
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

    public ArrayList<ClientBean> getClientList() {
        return (ArrayList<ClientBean>) clientBeans.clone();
    }

    public ClientBean getClientById(int id) {
        return clientBeans.get(id);
    }

    public static ClientsDAO getClientsDao() {
        return clientsDAO;
    }
}
