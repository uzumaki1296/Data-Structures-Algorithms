/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oddeven;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int i ,n;
            try{
            int[] array=new int[6];
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Odd Even sort</title>");            
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");

            
            
            for(i=0;i<6;i++)
            {
             String num=request.getParameter("no"+i);
             n=Integer.parseInt(num);
             array[i]=n;   
            }    
         
            out.print("The value before sorting is:\n");
            for(i=0;i<array.length;i++)
            {
                out.print(array[i]+"\n");
            }
            
            odd_even_sort(array,array.length);
                
            out.println("The value after sorting is:\n");
            for(i=0;i<array.length;i++)
            {
                out.println(array[i]+"\n");
            }
            
            
            out.println("</body>");
            out.println("</html>");
           
        }
        finally{
                out.close();
            }
                
         
    }
    public static void odd_even_sort(int array[],int n)       
    {
        for(int i=0;i<n/2;i++)
        {
            for(int j=0;j+1<n;j+=2)
            {
                if(array[j]>array[j+1])
                {
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
            
             for(int j=1;j+1<array.length;j+=2)
            {
                if(array[j]>array[j+1])
                {
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
        
    }

    
    }

    
    

