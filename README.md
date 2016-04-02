# network-chat
Network chat application written in Java

I had to write this application for the network programming class at LSBU.
For detailed information read the report.

Author: Georgi Butev
Lecturer: Dr Perry Xiao
Class: Advanced Network Programming
Course: CSN 1024 FT
University: LSBU

To create a new SSL certificate, use the following procedure:
keytool -genkey -keystore chatCertificate -keyalg RSA

Use this command with its arguments in the root of the two applications.
For visual preview of generating an SSL certificate, please refer to chatCertificate.jpg.

To start the server, please use the following command:

java -Djavax.net.ssl.keyStore=chatServer -Djavax.net.ssl.keyStorePassword=onlinechat-butevg ChatServer

To start the client, please use the following command:
java -Djavax.net.ssl.trustStore=chatCertificate -Djavax.net.ssl.trustStorePassword=onlinechat-butevg ChatClient
