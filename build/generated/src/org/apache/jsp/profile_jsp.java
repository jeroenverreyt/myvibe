package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class profile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_redirect_url_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_redirect_url_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_c_redirect_url_nobody.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>MyVibe Music Store</title>\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("        <!-- Bootstrap -->\r\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">\r\n");
      out.write("        <link href=\"css/home.css\" rel=\"stylesheet\" media=\"screen\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("    ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <div class=\"span8\">\r\n");
      out.write("                <h1>MyVibe</h1>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"span4\">\r\n");
      out.write("                <h4>Welkom ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${currentSessionUser.firstName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h4>\r\n");
      out.write("                <a href=\"LogOut\" target=\"_parent\"><button class=\"btn btn-danger\">Log uit</button></a><a href=\"profile.jsp\" target=\"_parent\"><button class=\"btn\">Profiel</button></a>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"row\" id=\"menu\">\r\n");
      out.write("            <nav role=\"navigation\" class=\"navbar navbar-default\">\r\n");
      out.write("                <ul class=\"nav navbar-nav\">\r\n");
      out.write("                    <li><a href=\"home.jsp\">Home</a></li>\r\n");
      out.write("                    <li><a href=\"playlist.html\">Mijn afspeellijst</a></li>\r\n");
      out.write("                    <li><a href=\"OverzichtTracks\">Koop een nummer</a></li>\r\n");
      out.write("                    <li><a href=\"upload.jsp\">Upload een nummer</a></li>\r\n");
      out.write("                </ul>\r\n");
      out.write("            </nav>\r\n");
      out.write("            <div class=\"input-append\">\r\n");
      out.write("                <input class=\"span2\" id=\"appendedInputButtons\" type=\"text\">\r\n");
      out.write("                <button class=\"btn\" type=\"button\">Search</button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write(" <p class=\"alert alert-success\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${feedback.wijziging == null ? \"style='display:none;'\" : \"\"}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write('>');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${feedback.wijziging}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p> \r\n");
      out.write("        <form action=\"ChangeProfile\" method=\"post\" class=\"form-horizontal\"> \r\n");
      out.write("            \r\n");
      out.write("            <h6> Username: </h6>\r\n");
      out.write("            <p> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${currentSessionUser.login}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </p>\r\n");
      out.write("\r\n");
      out.write("            <h6> Password: </h6>\r\n");
      out.write("            <input type=\"text\" id =\"newPassword\" name=\"newPassword\"> </input>\r\n");
      out.write("\r\n");
      out.write("            <h6> Naam: </h6>\r\n");
      out.write("            <p> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${currentSessionUser.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </p>\r\n");
      out.write("\r\n");
      out.write("            <h6> Voornaam: </h6>\r\n");
      out.write("            <p> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${currentSessionUser.firstName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </p>\r\n");
      out.write("\r\n");
      out.write("            <h6> Geboortedatum: </h6>\r\n");
      out.write("            <p> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${currentSessionUser.birthDate}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </p>\r\n");
      out.write("\r\n");
      out.write("            <h6> Email: </h6>\r\n");
      out.write("            <p> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${currentSessionUser.email}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </p>\r\n");
      out.write("\r\n");
      out.write("            <h6> Phone: </h6>\r\n");
      out.write("            <input type=\"text\" id=\"newPhone\" name=\"newPhone\"> </input>\r\n");
      out.write("\r\n");
      out.write("            <h6> Credits </h6>\r\n");
      out.write("            <p> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${currentSessionUser.credits}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </p>  \r\n");
      out.write("            \r\n");
      out.write("            <button type=\"submit\" class=\"btn btn-primary\">Bevestig wijzigingen</button>\r\n");
      out.write("        </form>\r\n");
      out.write("        <form action=\"DeleteAccount\" method=\"post\" class=\"form-horizontal\"> \r\n");
      out.write("            <button type=\"submit\" class=\"btn btn-primary\">Verwijder account</button>\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empty(sessionScope.currentSessionUser)}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("        ");
        if (_jspx_meth_c_redirect_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_redirect_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:redirect
    org.apache.taglibs.standard.tag.rt.core.RedirectTag _jspx_th_c_redirect_0 = (org.apache.taglibs.standard.tag.rt.core.RedirectTag) _jspx_tagPool_c_redirect_url_nobody.get(org.apache.taglibs.standard.tag.rt.core.RedirectTag.class);
    _jspx_th_c_redirect_0.setPageContext(_jspx_page_context);
    _jspx_th_c_redirect_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_c_redirect_0.setUrl("/index.jsp");
    int _jspx_eval_c_redirect_0 = _jspx_th_c_redirect_0.doStartTag();
    if (_jspx_th_c_redirect_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_redirect_url_nobody.reuse(_jspx_th_c_redirect_0);
      return true;
    }
    _jspx_tagPool_c_redirect_url_nobody.reuse(_jspx_th_c_redirect_0);
    return false;
  }
}
