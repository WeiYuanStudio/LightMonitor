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
 * Get HTTP Post request from client, set Client info
 */
@WebServlet(urlPatterns = "/sendinfo")
public class SendInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Set HTTP Encoding method
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String userSession = null;
        String info = null;
        int id; //Client ID

        //Get parameter from request
        userSession = req.getParameter("usersession"); //Get usersession
        info = req.getParameter("info"); //Get info

        if (userSession != null && info != null) {
            id = sessionGetId(userSession); //Search client id by usersession
            if (id != -1) { //User found, set client info and response
                ClientsDAO.getInstance().infoClient(id, info); //Set client info

                //Set response head
                resp.setStatus(200);
                resp.setContentType("text/plain");

                //Output Response
                ServletOutputStream out = resp.getOutputStream();
                out.println("OK!Set client info");
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
     * Use usersession to search user id TODO: Refactor this repeat method to DAO
     *
     * @param session
     * @return User id, if user not found return -1
     */
    private int sessionGetId(String session) {
        ArrayList<ClientBean> clientBeans = ClientsDAO.getInstance().getClientList();
        for (ClientBean client : clientBeans) {
            if (session.equals(client.getUserSession()))
                return client.getId();
        }
        return -1; //Can't found user by this session
    }
}
