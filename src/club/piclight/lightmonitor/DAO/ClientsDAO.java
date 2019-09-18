package club.piclight.lightmonitor.DAO;

import club.piclight.lightmonitor.Bean.ClientBean;

import java.util.ArrayList;
import java.util.Date;

public class ClientsDAO {
    private static ClientsDAO clientsDAO = new ClientsDAO();
    private ArrayList<ClientBean> clientBeans = new ArrayList<ClientBean>();

    private ClientsDAO(){
        ClientBean client = new ClientBean();
        client.setClientName("Adam's PC");
        client.setClientIP("1.1.1.1");
        client.setClientInfo("Windows 10");
        client.setPkgNum(1);
        client.setLastestOnline(new Date());
        registerClient(client);
    }

    private void registerClient(ClientBean client) {
        client.setId(clientBeans.size() + 1);
        clientBeans.add(client);
    }

    public ArrayList<ClientBean> getClientList() {
        return (ArrayList<ClientBean>) clientBeans.clone();
    }

    public static ClientsDAO getClientsDao() {
        return clientsDAO;
    }
}
