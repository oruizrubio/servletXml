package es.eprinsa.oruiz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.output.XMLOutputter;
import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;

/**
 * Servlet implementation class HelloWorld
 */
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String name = request.getParameter("name").trim();
		response.setContentType("text/xml"); 
	    PrintWriter out = response.getWriter();
	    
	   	Document doc;
		try {
			doc = generarXml();			
				
		       XMLOutputter xmlOutputter = new XMLOutputter();
		       xmlOutputter.setFormat(Format.getCompactFormat().setEncoding("ISO-8859-1"));
		       xmlOutputter.output(doc, out);	     
		       out.close();
		       
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}	    
	    
    	//out.print("<h2>Hello "+name+ "</h2>"); 
    	//out.close();
    	        
    	        
    	        
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

    }	
    
    static public Document generarXml() {
        Document doc = new Document();
        doc.setRootElement(new Element("company"));

        Element staff = new Element("staff");
        // add xml attribute
        staff.setAttribute("id", "1001");

        staff.addContent(new Element("name").setText("mkyong"));
        staff.addContent(new Element("role").setText("support"));
        staff.addContent(new Element("salary")
                .setAttribute("curreny", "USD").setText("5000"));

        // add xml comments
        staff.addContent(new Comment("for special characters like < &, need CDATA"));

        // add xml CDATA
        staff.addContent(new Element("bio")
                .setContent(new CDATA("HTML tag <code>testing</code>")));

        // append child to root
        doc.getRootElement().addContent(staff);

        Element staff2 = new Element("staff");
        staff2.setAttribute("id", "1002");
        staff2.addContent(new Element("name").setText("yflow"));
        staff2.addContent(new Element("role").setText("admin"));
        staff2.addContent(new Element("salary")
                .setAttribute("curreny", "EUD").setText("8000"));
        // add xml CDATA
        staff2.addContent(new Element("bio")
                .setContent(new CDATA("a & b")));

        // append child to root
        doc.getRootElement().addContent(staff2);

        return doc;
    }	

}
