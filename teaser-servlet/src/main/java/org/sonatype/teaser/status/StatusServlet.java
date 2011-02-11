package org.sonatype.teaser.status;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sonatype.teaser.AbstractTeaserServlet;

@Singleton
@SuppressWarnings( "serial" )
public class StatusServlet
    extends AbstractTeaserServlet
{
    protected void service( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        final int expectedStatusCode = asInt( getFirstPathElementFromRequest( req ), -1 );
        resp.setContentType( "text/html" );

        if ( expectedStatusCode > 0 )
        {
            resp.setStatus( expectedStatusCode );

            if ( !"HEAD".equalsIgnoreCase( req.getMethod() ) )
            {
                final PrintWriter writer = resp.getWriter();

                writer.println( "<html><body>" );
                writer.println( String.format( "You wanted status code %s, so here it is!", expectedStatusCode ) );
                writer.println( "<br/></body></html>" );
                writer.flush();
            }
        }
        else
        {
            resp.sendError( 400, "Unable to parse status code from path " + req.getRequestURI() );
        }
    }
}
