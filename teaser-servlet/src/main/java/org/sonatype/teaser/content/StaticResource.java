package org.sonatype.teaser.content;

import java.io.IOException;
import java.io.OutputStream;

public class StaticResource
    extends AbstractResource
{
    public StaticResource( String name, int length, String mimeType, long modified )
    {
        super( name, length, mimeType, modified );
        // TODO Auto-generated constructor stub
    }

    @Override
    public void write( OutputStream os )
        throws IOException
    {
        // TODO Auto-generated method stub

    }
}
