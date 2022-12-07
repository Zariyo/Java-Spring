package pl.edu.ug.lab.wpluzek.lab6.Servlet;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet-test")
public class MyServlet extends HttpServlet {

    public void init(ServletConfig config) {
        System.out.println("Servlet is being initialized");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter writer = response.getWriter();
        writer.println(
                "<html>\n" +
                "<head>\n" +
                "    <title>Quick Servlet Demo</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <a href=\"/QuickServlet\">Click here to send GET request</a>\n" +
                "     \n" +
                "    <br/><br/>\n" +
                "     \n" +
                "    <form action=\"QuickServlet\" method=\"post\">\n" +
                "        Width: <input type=\"text\" size=\"5\" name=\"width\"/>\n" +
                "        &nbsp;&nbsp;\n" +
                "        Height <input type=\"text\" size=\"5\" name=\"height\"/>\n" +
                "        &nbsp;&nbsp;\n" +
                "        <input type=\"submit\" value=\"Calculate\" />\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>");
        writer.flush();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String paramWidth = request.getParameter("width");
        int width = Integer.parseInt(paramWidth);

        String paramHeight = request.getParameter("height");
        int height = Integer.parseInt(paramHeight);

        int area = width * height;

        PrintWriter writer = response.getWriter();
        writer.println(
                "<html>\n" +
                        "<head>\n" +
                        "    <title>Servlet test</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <a href=\"/servlet-test\">Click here to send GET request</a>\n" +
                        "     \n" +
                        "    <br/><br/>\n" +
                        "     \n" +
                        "    <form action=\"servlet-test\" method=\"post\">\n" +
                        "        Width: <input type=\"text\" size=\"5\" name=\"width\"/>\n" +
                        "        &nbsp;&nbsp;\n" +
                        "        Height <input type=\"text\" size=\"5\" name=\"height\"/>\n" +
                        "        &nbsp;&nbsp;\n" +
                        "        <input type=\"submit\" value=\"Calculate\" />\n" +
                        "    </form>\n" +
                        "   <div>Area of the rectangle is: \" + area</div>" +
                        "</body>\n" +
                        "</html>");
        writer.flush();
    }
}
