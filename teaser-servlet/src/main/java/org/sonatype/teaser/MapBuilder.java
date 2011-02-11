package org.sonatype.teaser;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder
{
    private final HashMap<String, String> params = new HashMap<String, String>();

    public MapBuilder put( final String key, final String value )
    {
        params.put( key, value );

        return this;
    }

    public MapBuilder remove( final String key )
    {
        params.remove( key );

        return this;
    }

    public Map<String, String> build()
    {
        return new HashMap<String, String>( params );
    }
}
