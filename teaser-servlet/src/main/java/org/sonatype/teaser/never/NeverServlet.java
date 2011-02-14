package org.sonatype.teaser.never;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sonatype.teaser.AbstractTeaserServlet;

@Singleton
@SuppressWarnings( "serial" )
public class NeverServlet
    extends AbstractTeaserServlet
{
    @Override
    protected void service( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        resp.setContentType( "text/html" );
        resp.addHeader( "WWW-Authenticate", "BASIC realm=\"Never\"" );
        resp.sendError( 401, "You need to authenticate (but will never succeed ;) )!" );
    }
}
