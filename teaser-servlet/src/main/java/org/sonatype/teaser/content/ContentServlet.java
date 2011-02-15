package org.sonatype.teaser.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sonatype.teaser.AbstractTeaserServlet;

@SuppressWarnings( "serial" )
public class ContentServlet
    extends AbstractTeaserServlet
{
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        final String filename = "";
        
    }
}
