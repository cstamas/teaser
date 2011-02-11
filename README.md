Teaser
======

.. is not a molester.

But is based on it:
http://henrik-eclipse.blogspot.com/2009/05/test-data-molester-comes-to-town.html

Sources:
http://dev.eclipse.org/viewcvs/viewvc.cgi/org.eclipse.equinox/p2/bundles/org.eclipse.equinox.p2.testserver/?root=RT_Project

Many thanks for inspiration!


Intent
======

Teaser is conceived to have two parts: one WAR ("server") side, which is actually a Guiced Java Servlet with a bunch of "problematic" HttpServlets. This WAR can be dropped into any container, but the initial intent was to drop it into Google App Engine. Hosting stuff, like Google App Server is cheap (free?) today. Moreover, you can easily create a "pool" of same Apps ;)

The simplistic "client" part would be a very-very simple "factory" like code (one class maybe), that would help you to spread load across the pool of Teasers.

So, if you write a HttpClient, or anything that does some HTTP transport, and you want to test-drive it under rough conditions, have it!


TODO:
- add all resources/cases from "Chester the test-data molester"
- add resources/cases from AHC UTs
- seek other "problems" and implement them
- make it better ;)
- finish it ;)


Have fun,  
~t~
