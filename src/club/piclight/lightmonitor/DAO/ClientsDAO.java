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
        now.add(Calendar.MINUTE, +1);
        client.setLastestOnline(now);
        registerClient(client);
    }

    private void registerClient(ClientBean client) {
        client.setId(clientBeans.size() + MIN_OFFSET);
        clientBeans.add(client);
    }

    public ArrayList<ClientBean> getClientList() {
        return (ArrayList<ClientBean>) clientBeans.clone();
    }

    public static ClientsDAO getClientsDao() {
        return clientsDAO;
    }
}
