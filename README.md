simpleds-kickstart
==================

Demo application using Jersey, Guice and SimpleDS on Google AppEngine.

To get the project started, download gradle >= 1.0rc2 and execute <code>gradle eclipse</code>.

Import the project into your eclipse workspace and right-click->Run As->Web Application. 
The project compiles your classes directly on <code>war/WEB-INF/classes</code>, so you can just launch and open your browser (probably the 
first thing that you want to click is the "reset datastore" red button to populate the datastore with buckets of Lorem Ipsum).

For an introduction to the frameworks involved, see [this presentation at Codemotion 2012](http://www.slideshare.net/icoloma/codemotion-appengine)

A live demo is available [here](http://simpleds-kickstart.appspot.com/) 

**Be aware**: the demo application is running on free quotas, so warmup delays are likely. Be patient.