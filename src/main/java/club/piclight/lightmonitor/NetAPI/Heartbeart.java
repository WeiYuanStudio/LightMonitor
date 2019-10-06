package club.piclight.lightmonitor.NetAPI;

import club.piclight.lightmonitor.Bean.ClientBean;
import club.piclight.lightmonitor.DAO.ClientsDAO;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Get HTTP heartbeat request from client, refresh latest online time
 */
@WebServlet(urlPatterns = "/heartbeart")
public class Heartbeart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Set HTTP Encoding method
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String userSession = null;
        int id;

        //Get parameter from request
        userSession = req.getParameter("usersession");

        if (userSession != null) {
            id = SessionGetUser(userSession); //Search client id by usersession
            if (id != -1) { //User found, set client info and response
                //Refresh client
                ClientsDAO.getClientsDao().refreshClient(id, req.getRemoteHost());

                //Set response head
                resp.setStatus(200);
                resp.setContentType("text/plain");

                //Output Response
                ServletOutputStream out = resp.getOutputStream();
                out.println("OK! Refresh LastestOnline Time.");
            } else { //User not found, response 400
                //Set response head
                resp.setStatus(400);
                resp.setContentType("text/plain");

                //Output Response
                ServletOutputStream out = resp.getOutputStream();
                out.println("Error! User No found.");
            }
        } else { //Parameter illegal, response 400
            //Set response head
            resp.setStatus(400);
            resp.setContentType("text/plain");

            //Output Response
            ServletOutputStream out = resp.getOutputStream();
            out.println("Error! Please Check Your Parameter");
        }
    }


    /**
     * Use Usersession to search user id TODO: Refactor this repeat method to DAO
     *
     * @param session
     * @return
     */
    private int SessionGetUser(String session) {
        ArrayList<ClientBean> clientBeans = ClientsDAO.getClientsDao().getClientList();
        for (ClientBean client : clientBeans) {
            if (session.equals(client.getUserSession()))
                return client.getId();
        }
        return -1; //Can't found user by this session
    }
}
