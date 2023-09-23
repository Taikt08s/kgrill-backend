package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Homepage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Bird Service</title>\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n");
      out.write("            <div class=\"container px-5\">\n");
      out.write("                <a class=\"navbar-brand\" href=\"#!\">Bird Service</a>\n");
      out.write("                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\"\n");
      out.write("                        data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"\n");
      out.write("                        aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>\n");
      out.write("                <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n");
      out.write("                    <ul class=\"navbar-nav ms-auto mb-2 mb-lg-0 align-items-center\">\n");
      out.write("                        <li class=\" nav-item\"><a class=\"nav-link\" aria-current=\"page\" href=\"#!\">Login</a></li>\n");
      out.write("                        <li class=\"nav-item\"><a class=\"nav-link\" href=\"#!\">Register</a></li>\n");
      out.write("                        <li class=\"nav-item\"><a class=\"nav-link\" href=\"#!\">Contact</a></li>\n");
      out.write("                        <li class=\"nav-item\"><a class=\"nav-link\" href=\"#!\">Services</a></li>\n");
      out.write("                        <div class=\"nav-item\"><a href=\"#!\" class=\"nav-link\">\n");
      out.write("                                <nav class=\"navbar \">\n");
      out.write("                                    <div class=\"container-fluid\">\n");
      out.write("                                        <form class=\"d-flex\" role=\"search\">\n");
      out.write("                                            <input class=\"form-control me-2\" type=\"search\" placeholder=\"Search\"\n");
      out.write("                                                   aria-label=\"Search\">\n");
      out.write("                                            <button class=\"btn btn-outline-light \" type=\"submit\">Search</button>\n");
      out.write("                                        </form>\n");
      out.write("                                    </div>\n");
      out.write("                                </nav>\n");
      out.write("                            </a></div>\n");
      out.write("\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("        <!-- Page Content-->\n");
      out.write("        <div class=\"container px-4 px-lg-5\">\n");
      out.write("            <!-- Heading Row-->\n");
      out.write("            <div class=\"row gx-4 gx-lg-5 align-items-center my-5\">\n");
      out.write("                <div class=\"col-lg-7\"><img class=\"img-fluid rounded mb-4 mb-lg-0\"\n");
      out.write("                                           src=\"https://dummyimage.com/900x400/dee2e6/6c757d.jpg\" alt=\"...\" /></div>\n");
      out.write("                <div class=\"col-lg-5\">\n");
      out.write("                    <h1 class=\"font-weight-light\">Dịch vụ có số lượng người đặt cao nhất </h1>\n");
      out.write("                    <p>Chi tiết về dịch vụ</p>\n");
      out.write("                    <a class=\"btn btn-primary\" href=\"#!\">Booking Now!</a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <!-- Call to Action-->\n");
      out.write("            <div class=\"card text-white bg-secondary my-5 py-4 text-center\">\n");
      out.write("                <div class=\"card-body\">\n");
      out.write("                    <p class=\"text-white m-0\">This call to action card is a great place to showcase some important\n");
      out.write("                        information or display a clever tagline!</p>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <!-- Content Row-->\n");
      out.write("            <div class=\"row gx-4 gx-lg-5\">\n");
      out.write("                <div class=\"col-md-4 mb-5\">\n");
      out.write("                    <div class=\"card h-100\">\n");
      out.write("                        <div class=\"card-body\">\n");
      out.write("                            <h2 class=\"card-title\">Dịch vụ số 1</h2>\n");
      out.write("                            <p class=\"card-text\">Chi tiết</p>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"card-footer\"><a class=\"btn btn-primary btn-sm\" href=\"#!\">Add to cart</a></div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-4 mb-5\">\n");
      out.write("                    <div class=\"card h-100\">\n");
      out.write("                        <div class=\"card-body\">\n");
      out.write("                            <h2 class=\"card-title\">Dịch vụ số 2</h2>\n");
      out.write("                            <p class=\"card-text\">Chi tiết</p>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"card-footer\"><a class=\"btn btn-primary btn-sm\" href=\"#!\">Add to cart</a></div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-4 mb-5\">\n");
      out.write("                    <div class=\"card h-100\">\n");
      out.write("                        <div class=\"card-body\">\n");
      out.write("                            <h2 class=\"card-title\">Dịch vụ số 3</h2>\n");
      out.write("                            <p class=\"card-text\">Chi tiết</p>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"card-footer\"><a class=\"btn btn-primary btn-sm\" href=\"#!\">Add to cart</a></div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- Footer-->\n");
      out.write("        <footer class=\"py-5 bg-dark\">\n");
      out.write("            <div class=\"container px-4 px-lg-5\">\n");
      out.write("                <p class=\"m-0 text-center text-white\">Copyright &copy; Your Website 2023</p>\n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
