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

    /**
     * Register new client
     *
     * @param client Client instance ready to register
     * @return clientID
     */
    public synchronized int registerClient(ClientBean client) {
        int clientId = clientBeans.size(); //Get ArrayList size as client id
        client.setId(clientBeans.size()); //Set client id
        clientBeans.add(client); //Add client to ArrayList
        return clientId;
    }

    /**
     * Refresh client latest online time
     *
     * @param id client id
     * @param ip client request ip address
     */
    public synchronized void refreshClient(int id, String ip) {
        try {
            ClientBean client = clientBeans.get(id); //ArrayList get by id
            client.setClientIP(ip); //Set client ip address
            client.setPkgNum(client.getPkgNum() + 1); //PkgNum add one
            Calendar now = Calendar.getInstance(); //Get now time
            now.add(Calendar.MINUTE, +MIN_OFFSET);// Calendar calculate Plus one minute
            client.setLastestOnline(now); //Set the calculate result as client latest online time
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set client info
     *
     * @param id   client id
     * @param info String info
     */
    public synchronized void infoClient(int id, String info) {
        try {
            ClientBean client = clientBeans.get(id); //ArrayList get by id
            client.setClientInfo(info); //Set client info
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get All client with ArrayList
     *
     * @return ArrayList<ClientBean>
     */
    public ArrayList<ClientBean> getClientList() {
        return (ArrayList<ClientBean>) clientBeans.clone();
    }

    /**
     * Get client by id
     *
     * @param id client id
     * @return client
     */
    public ClientBean getClientById(int id) {
        return clientBeans.get(id);
    }

    /**
     * Get Singleton instance
     *
     * @return clientsDAO Singleton instance
     */
    public static ClientsDAO getInstance() {
        if (clientsDAO == null) {
            clientsDAO = new ClientsDAO();
        }
        return clientsDAO;
    }
}
