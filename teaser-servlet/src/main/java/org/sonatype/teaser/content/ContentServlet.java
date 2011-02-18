package org.sonatype.teaser.content;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sonatype.teaser.AbstractTeaserServlet;

import com.google.inject.Singleton;

@Singleton
@SuppressWarnings( "serial" )
public class ContentServlet
    extends AbstractTeaserServlet
{
    private final Random random = new Random( System.currentTimeMillis() );

    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        final String filename = getFilename( req );

        Resource resource;

        if ( filename.startsWith( "generated." ) )
        {
            resource = generateResource( req, resp, filename );
        }
        else
        {
            resource = staticResource( req, resp, filename );
        }

        if ( serveUpHeaders( req, resp, resource ) )
        {
            serveUpContent( req, resp, resource );
        }
    }

    protected Resource generateResource( final HttpServletRequest req, final HttpServletResponse resp,
                                         final String filename )
    {
        final int length = random.nextInt( 10000 ) + 500; // let the size be min 500 and max 10500;

        return new GeneratedResource( filename, length, "application/octet-stream", System.currentTimeMillis() );
    }

    protected Resource staticResource( final HttpServletRequest req, final HttpServletResponse resp,
                                       final String filename )
    {
        if ( STATIC_RESOURCES.containsKey( filename ) )
        {
            final Resource template = STATIC_RESOURCES.get( filename );

            final InputStream is = getServletContext().getResourceAsStream( "/static/" + template.getName() );

            if ( null != is )
            {
                return new StaticResource( template, is );
            }
        }

        return null;
    }

    protected boolean serveUpHeaders( final HttpServletRequest req, final HttpServletResponse resp,
                                      final Resource resource )
        throws IOException
    {
        if ( resource == null )
        {
            resp.sendError( 404, String.format( "Path %s not found here!", req.getRequestURI() ) );

            return false;
        }
        else
        {
            resp.setStatus( 200 );

            final long lastModified = getLastModified( req, resource.getModified() );
            if ( lastModified != -1 )
            {
                resp.setDateHeader( "Last-Modified", lastModified );
            }

            final int length = getContentLength( req, resource.getLength() );
            if ( length != -1 )
            {
                resp.setIntHeader( "Content-Length", length );
            }

            final String mimeType = getContentType( req, resource.getMimeType() );
            if ( mimeType != null )
            {
                resp.setHeader( "Content-Type", mimeType );
            }

            return true;
        }
    }

    protected void serveUpContent( final HttpServletRequest req, final HttpServletResponse resp, final Resource resource )
        throws IOException
    {
        resource.write( resp.getOutputStream() );
    }

    private static final HashMap<String, Resource> STATIC_RESOURCES = new HashMap<String, Resource>( 8 );

    static
    {
        final long now = System.currentTimeMillis();

        STATIC_RESOURCES.put( "archive.zip", new StaticResource( "archive.zip", 729683, "application/zip", now, null ) );
        STATIC_RESOURCES.put( "image.gif", new StaticResource( "image.gif", 74395, "image/gif", now, null ) );
        STATIC_RESOURCES.put( "image.jpg", new StaticResource( "image.jpg", 92019, "image/jpeg", now, null ) );
        STATIC_RESOURCES.put( "image.png", new StaticResource( "image.png", 130820, "image/png", now, null ) );
        STATIC_RESOURCES.put( "image.svg", new StaticResource( "image.svg", 101538, "image/svg+xml", now, null ) );
        STATIC_RESOURCES.put( "text.html", new StaticResource( "text.html", 512927, "text/html", now, null ) );
        STATIC_RESOURCES.put( "text.pdf", new StaticResource( "text.pdf", 550558, "application/pdf", now, null ) );
        STATIC_RESOURCES.put( "text.txt", new StaticResource( "text.txt", 422279, "text/plain", now, null ) );
    }

    // ==

    private int getContentLength( final HttpServletRequest req, final int defaultValue )
    {
        final String hdr = req.getHeader( "X-Content-Length" );

        if ( null != hdr )
        {
            try
            {
                return Integer.parseInt( hdr );
            }
            catch ( NumberFormatException e )
            {
                return defaultValue;
            }
        }
        else
        {
            return defaultValue;
        }
    }

    private String getContentType( final HttpServletRequest req, final String defaultValue )
    {
        final String hdr = req.getHeader( "X-Content-Type" );

        if ( null != hdr )
        {
            return hdr;
        }
        else
        {
            return defaultValue;
        }
    }

    private long getLastModified( final HttpServletRequest req, final long defaultValue )
    {
        // it tries to get it as formatted date
        // then, it falls back to simple "long" number (so you can pass in -1 for DoNotSet etc)
        try
        {
            final long lastModified = req.getDateHeader( "X-Last-Modified" );

            if ( lastModified > -1 )
            {
                return lastModified;
            }
            else
            {
                return defaultValue;
            }
        }
        catch ( IllegalArgumentException e )
        {
            final String lastModifiedStr = req.getHeader( "X-Last-Modified" );

            if ( lastModifiedStr != null )
            {
                try
                {
                    return Long.parseLong( lastModifiedStr );
                }
                catch ( NumberFormatException ex )
                {
                    return defaultValue;
                }
            }
            else
            {
                return defaultValue;
            }
        }
    }
}
