package org.sonatype.teaser.echo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sonatype.teaser.AbstractTeaserServlet;

import com.google.inject.Singleton;

@Singleton
@SuppressWarnings( "serial" )
public class EchoServlet
    extends AbstractTeaserServlet
{
    @SuppressWarnings( "unchecked" )
    @Override
    protected void service( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        resp.setContentType( "text/plain" );
        resp.setStatus( 200 );

        if ( !"HEAD".equalsIgnoreCase( req.getMethod() ) )
        {
            final PrintWriter writer = resp.getWriter();

            writer.println( "req Received at " + new Date() );
            writer.println( " characterEncoding=" + req.getCharacterEncoding() );
            writer.println( "     contentLength=" + req.getContentLength() );
            writer.println( "       contentType=" + req.getContentType() );
            writer.println( "            locale=" + req.getLocale() );
            writer.print( "           locales=" );
            Enumeration<Locale> locales = (Enumeration<Locale>) req.getLocales();
            boolean first = true;
            while ( locales.hasMoreElements() )
            {
                Locale locale = locales.nextElement();
                if ( first )
                    first = false;
                else
                    writer.print( ", " );
                writer.print( locale.toString() );
            }
            writer.println();
            Enumeration<String> names = (Enumeration<String>) req.getParameterNames();
            while ( names.hasMoreElements() )
            {
                String name = names.nextElement();
                writer.print( "         parameter=" + name + "=" );
                String values[] = req.getParameterValues( name );
                for ( int i = 0; i < values.length; i++ )
                {
                    if ( i > 0 )
                        writer.print( ", " );
                    writer.print( values[i] );
                }
                writer.println();
            }
            writer.println( "          protocol=" + req.getProtocol() );
            writer.println( "        remoteAddr=" + req.getRemoteAddr() );
            writer.println( "        remoteHost=" + req.getRemoteHost() );
            writer.println( "            scheme=" + req.getScheme() );
            writer.println( "        serverName=" + req.getServerName() );
            writer.println( "        serverPort=" + req.getServerPort() );
            writer.println( "          isSecure=" + req.isSecure() );

            writer.println( "---------------------------------------------" );

            writer.println( "       contextPath=" + req.getContextPath() );
            Cookie cookies[] = req.getCookies();
            if ( cookies == null )
                cookies = new Cookie[0];
            for ( int i = 0; i < cookies.length; i++ )
            {
                writer.println( "            cookie=" + cookies[i].getName() + "=" + cookies[i].getValue() );
            }
            names = (Enumeration<String>) req.getHeaderNames();
            while ( names.hasMoreElements() )
            {
                String name = (String) names.nextElement();
                String value = req.getHeader( name );
                writer.println( "            header=" + name + "=" + value );
            }
            writer.println( "            method=" + req.getMethod() );
            writer.println( "          pathInfo=" + req.getPathInfo() );
            writer.println( "       queryString=" + req.getQueryString() );
            writer.println( "        remoteUser=" + req.getRemoteUser() );
            writer.println( "requestedSessionId=" + req.getRequestedSessionId() );
            writer.println( "        requestURI=" + req.getRequestURI() );
            writer.println( "       servletPath=" + req.getServletPath() );

            writer.println( "=============================================" );
        }
    }
}
