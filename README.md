# BookRec - Web Applications Project

This repository contains the source code of the proect BookRec for the webapp course in the academic year 2023/2024.

*BookRec* is a webapp where user can create and delete their account and search for informations about thei favourite books, authors or to search other users of the website.
The database is made with PostgreSQL, the backend is managed by Java Servlets (thanks to the web container Tomcat) and it features a dynamic frontend thanks to the use of JSP pages, CSS and some JavaScript code.

In order to test the website correctly, follow these steps:
1. Install everything needed (Tomcat, Java, Maven and PostgreSQL)
2. Create an account in postgreSQL according to the credentials written in context.xml (in the folder BookRec/src/main/webapp/META-INF)
3. After cloning this repo, import the DB saved as .sql file in BookRec/src/main/database in PostgreSQL
4. Compile the project using Maven (mvn clean package javadoc:javadoc)
5. Deploy the .war file in Tomcat
6. The homepage of the website is located at /jsp/home.jsp


This project has been developed by [Stefano Bortolatto](mailto:stefano.bortolatto@studenti.unipd.it "Contact him in ITA/ENG via e-mail") and [Vinayak Kumar](mailto:vinayak.kumar@studenti.unipd.it "Contact him in ENG via e-mail").

*Web Applications* is a course of the 

* [Master Degree in Computer Engineering](https://lauree.dei.unipd.it/lauree-magistrali/computer-engineering/)
* [Master Degree in ICT for Internet and Multimedia](https://lauree.dei.unipd.it/lauree-magistrali/ict-for-internet-multimedia-mime/)
* [Master Degree in Cybersecurity](https://cybersecurity.math.unipd.it/)

of the  [Department of Information Engineering](https://www.dei.unipd.it/en/), [University of Padua](https://www.unipd.it/en/), Italy. *Web Applications* is part of the teaching activities of the [Intelligent Interactive Information Access (IIIA) Hub](http://iiia.dei.unipd.it/).
