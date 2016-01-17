/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import si.mko.test.serialize.api.CTransferObjectDefaultSerialization;
import si.mko.test.serialize.api.CTransferObjects;
import si.mko.test.serialize.api.ITransferObject;
import si.mko.test.serialize.resource.CDataProvider;

/**
 *
 * @author matej
 */
@WebServlet(name = "KryoServlet", urlPatterns = {"/kryo"})
public class KryoServlet extends HttpServlet {

    /**
     * Kryo instances are not thread safe, each thread must have it's own
     * instance. However, to avoid creating new instances, we create a pool for
     * each thread.
     *
     * https://github.com/EsotericSoftware/kryo#pooling-kryo-instances
     */
    private ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            // configure kryo instance, customize settings
            return kryo;
        }

    };

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int n = Integer.parseInt(request.getParameter("n"));

        Collection<ITransferObject> result = CDataProvider.INSTANCE.get(n, CTransferObjectDefaultSerialization.class);

        OutputStream fout = response.getOutputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {

            Kryo k = kryos.get();
            k.register(CTransferObjects.class);
            k.register(CTransferObjectDefaultSerialization.class);
            k.register(ITransferObject.class);
            k.register(Collection.class);
            k.register(ArrayList.class);

            Output o = new Output(bos);
            k.writeObject(o, new CTransferObjects(result));
            o.flush();
            o.close();

            byte[] object = bos.toByteArray();
            System.out.println("object size: " + object.length);

            response.setContentLength(object.length);
            fout.write(object);

        } finally {
            fout.flush();
            fout.close();
            fout = null;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
