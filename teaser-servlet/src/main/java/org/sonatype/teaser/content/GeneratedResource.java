package org.sonatype.teaser.content;

import java.io.IOException;
import java.io.OutputStream;

public class GeneratedResource
    extends AbstractResource
{
    public GeneratedResource( String name, int length, String mimeType, long modified )
    {
        super( name, length, mimeType, modified );
    }

    @Override
    public void write( OutputStream os )
        throws IOException
    {
        for ( int i = 0; i < getLength(); i++ )
        {
            os.write( i );
        }
    }
}
