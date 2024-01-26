/*

* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

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
public class Booths extends HttpServlet {

    final static int cnt = 8;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        Booths b = new Booths();
        int M[] = new int[cnt];
        int Q[] = new int[cnt];
        int A[] = new int[cnt];
        int M1[] = new int[cnt];
        int Q1=0;
        M = b.ToBinary(num1);
        M1 = b.ToBinary(-num1);
        Q = b.ToBinary(num2);
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Booths Algoritm</title>");
        out.print("</head>");
        out.print("<body>");
        out.println("(M)Binary of "+ num1 +" is: " +M[7]+M[6]+M[5]+M[4]+M[3]+M[2]+M[1]+M[0]+"<br>");
        out.println("(Q)Binary of "+ num2 +" is: " +Q[7]+Q[6]+Q[5]+Q[4]+Q[3]+Q[2]+Q[1]+Q[0]+"<br>");
        out.println("<br><br>");
        out.println("<table border=1.5px solid black>");
        
        out.println("<tr>"
                + "<th >A"
                + "<th >Q"
                + "<th >Q0"
                + "<th >Action"
                +"</tr>");
        
        
        out.println("<tr>"
                +"<td>"+A[7]+A[6]+A[5]+A[4]+A[3]+A[2]+A[1]+A[0]+"</td>"
                +"<td>"+Q[7]+Q[6]+Q[5]+Q[4]+Q[3]+Q[2]+Q[1]+Q[0]+"</td>"
                +"<td>"+Q1+"</td>"
                +"<td>Initial Value</td>"
                +"</tr>");
        int i=0;
        while(i<cnt)
        {
                if(Q[0]==0 && Q1==0)
                        ;
                else if(Q[0]==0 && Q1==1){
                        b.Add(A,M);
                        out.println("<tr>"
                            +"<td>"+A[7]+A[6]+A[5]+A[4]+A[3]+A[2]+A[1]+A[0]+"</td>"
                            +"<td>"+Q[7]+Q[6]+Q[5]+Q[4]+Q[3]+Q[2]+Q[1]+Q[0]+"</td>"
                            +"<td>"+Q1+"</td>"
                            +"<td>A=A+M</td>"
                            +"</tr>");
                }

                else if(Q[0]==1 && Q1==0){
                        b.Add(A,M1);
                        out.println("<tr>"
                            +"<td>"+A[7]+A[6]+A[5]+A[4]+A[3]+A[2]+A[1]+A[0]+"</td>"
                            +"<td>"+Q[7]+Q[6]+Q[5]+Q[4]+Q[3]+Q[2]+Q[1]+Q[0]+"</td>"
                            +"<td>"+Q1+"</td>"
                            +"<td>A=A-M</td>"
                            +"</tr>");
                }
                else
                        ;
                Q1=Q[0];
                b.ShiftRight(A,Q);
                
                out.println("<tr>"
                    +"<td>"+A[7]+A[6]+A[5]+A[4]+A[3]+A[2]+A[1]+A[0]+"</td>"
                    +"<td>"+Q[7]+Q[6]+Q[5]+Q[4]+Q[3]+Q[2]+Q[1]+Q[0]+"</td>"
                    +"<td>"+Q1+"</td>"
                    +"<td>Right Shift</td>"
                    +"</tr>");
                
                i++;
        }

        out.print("</table><br>");
        out.println("Result in Decimal: "+b.ToDecimal(b.concatinate(A, Q))+"<br>");

	out.print("</body>");
        out.print("</html>");
    }
    
    int [] concatinate(int a[], int q[])
	{
		int b[] = new int[cnt*2];
		int i=0;
		int j=0;
		while(i<cnt)
			b[j++]=q[i++];
		i=0;
		while(i<cnt)
			b[j++]=a[i++];
		return b;
	}
	void ShiftRight(int a[],int q[])
	{
		for(int i=0;i<cnt-1;i++)
			q[i]=q[i+1];
		q[cnt-1]=a[0];
		for(int i=0;i<cnt-1;i++)
			a[i]=a[i+1];
	}
	
	void Add(int a[],int m[])
	{
		int r = 0;
		int carry = 0;
		int i=0;
		while(i<cnt)
		{
			int n1 = a[i],n2 = m[i];
			if(n1 == 0 && n2 == 0 && carry == 0)
			{
				r=0;
				carry=0;
			}
			else if((n1 == 1 && n2 == 0 && carry == 0)||(n1 == 0 && n2 == 1 && carry == 0)||(n1 == 0 && n2 == 0 && carry == 1))
			{
				r=1;
				carry=0;
			}
			else if((n1 == 1 && n2 == 1 && carry == 0)||(n1 == 1 && n2 == 0 && carry == 1)||(n1 == 0 && n2 == 1 && carry == 1))
			{
				r=0;
				carry=1;
			}
			else
			{
				r=1;
				carry=1;
			}
			a[i]=r;
			i++;
		}
		
	}
	
	int ToDecimal(int a[])
	{
		boolean negative = false;
		int cnt = 2*this.cnt;
		if(a[cnt-1]==1)
		{
			negative = true;
			Booths.compliment(a,cnt);
			Booths.AddOne(a, cnt);
		}
		int num=0;
		for(int i=0;i<cnt;i++)
		{
			num+=(a[i]*Math.pow(2, i));
		}
		if(negative)
			return 0-num;
		return num;
	}
	
	static int [] compliment(int a[],int bits)
	{
		int i=0;
		while(i<bits)
		{
			if(a[i]==1)
				a[i]=0;
			else
				a[i]=1;
			i++;
		}
		return a;
	}
	
	static void AddOne(int res[],int bits)
	{
		int i=0;
		int carry = 1;
		while(i<bits)
		{
			int r = 0;
			int num = res[i];
			if(num == 0 && carry == 0)
			{
				r = 0;
				carry = 0;
			}
			else if((num == 0 && carry == 1) || (num == 1 && carry == 0))
			{
				r = 1;
				carry = 0;
			}
			else
			{
				r=0;
				carry = 1;
			}
			res[i] = r;
			i++;
		}
	}
	
	int [] ToBinary(int n){
		int res[] = new int[8];
		int i=0;
		boolean negative = n<0?true:false;
		if(negative)
			n=0-n;
		while(n>0)
		{
			res[i++]=n%2;
			n=n/2;
		}
		if(negative)
		{
			Booths.compliment(res,cnt);
			Booths.AddOne(res,cnt);
		}
		
		return res;
		
	}
        
}