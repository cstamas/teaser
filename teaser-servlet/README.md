Teaser Servlet
==============

The teaser servlet is the "server side" of the Teaser. If is basically implemented reusing ideas from here:

http://henrik-eclipse.blogspot.com/2009/05/test-data-molester-comes-to-town.html

and also this:

https://github.com/sonatype/http-testing-harness

To be added a new path /behavior where you could "drive" which ones you want to activate, kinda the URI of request would parametrize the Behaviour applied.


Use:

* /echo - always returns, just dumps out the whole request
* /status/* - returns the status passed in, ie. /status/500 ends with HTTP 500, etc
* /redirect/* - redirects as many times as wanted and final redirect goes to /echo TBD: append URI where to go as final redirect.
* /timeout/* - sleeps as many seconds as given on path, and redirects you to /echo
* /never - a HTTP Basic protected path never letting you in.

Now, as for content delivery:

Teaser "knows" several static filenames (the actual payload is bundled within WAR):

* text.txt -- a static text doco
* text.html -- a static HTML doco
* text.pdf -- a static PDF document
* image.jpg -- a static JPEG picture
* image.png -- a static PNG picture
* image.gif -- a static GIF picture
* image.svg -- a static SVG picture
* archive.zip -- a static ZIP file

You can access these as "static" files (served by separate server when on GAE, or by container itself) using path:

    GET /static/text.txt

You can access these unmolested, but over ContentServlet using path:

    GET /content/text.txt or
    GET /content/foo/text.txt or
    ....

Yes, it is only the last element of the request URI that does matters.

There is also one "special" filename, the "generated." one (it's just the name that matters, extension not). Naturally, it can be only accessed over /content URI

    GET /content/generated.foo


Note: the /content Servlet does obeys some tricky request headers:

* X-Content-Length: when found, will replace actual content length of static resource, or -1 to not put it into response.
* X-Last-Modified: when found, will replace actual last modified in response. You can use RFC formatted string, or plain long or -1 to not have it in response.
* X-Content-Type: when found will replace actual content type.


TBD:
====

* Other teasers (see TeaserModule)
* Integrate Behaviour (will need either copy+paste+cleanup or something else, since those are Jetty bound)

How to make WGET go nuts
========================

Try this:

wget http://localhost:8080/teaser-servlet-1.0.0-SNAPSHOT/content/generated.png -S -d --header=X-Content-Length:100000
