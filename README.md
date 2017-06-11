# AsterX
A System based Password Manager Application to encrypt and save Credit/Debit Card and login-password details.

So far, the best tool for online verification we have is password. Since every website has its own set of password constraints, more the 
Internet life, more are the passwords to be remembered. With so many passwords around, most people try to rely on remembering their 
passwords, and many of them fail at it. This leads to two terrible things. The first is using the same password for numerous services, 
which, of course, is incredibly unsafe. The second predicament people end up in is having different passwords but stashing all of them in a
Microsoft Word document or a paper notebook.

In this project, our aim is to provide the user with a reliable, secured, simple yet internally complex password manager application using 
Database Management Systems, Encryption algorithms and involving fast and smooth UI.

Briefly, this application has the following functionality:

-> User authentication is done using a unique User ID and Master Password.
<br />-> After the user is logged in, passwords entered by the user is sent to the encryption algorithm which generates an unintelligible 
encrypted cipher.
<br />-> The cipher is then stored in the Database along with the corresponding domain name.
<br />-> Upon retrieval, the cipher is decrypted and displayed to the user.

This application, AsterX, thus fulfills the requirement of storing multiple passwords maintaining confidentiality and authentication 
through a single step password-protected gateway.

Installation
=====================================================================================================================================

Get the latest version of Java at https://www.java.com/en/download/

### Usage:

If you have java in your path and Oracle 11g installed, simply double on the provided AsterX.jar file.

* Install Java
	
	Double click on the jre-8u25-windows-x64[32].exe and follow the instructions to install the Java Runtime Environment.
	
* Install Oracle

	Install Oracle 11g Database by double clicking on Oracle 11g Express -> DISK1 -> setup.exe and follow the instructions.
	*NOTE: Provide 'system' as the admin and 'manager' as the password. Otherwise, our application will not be able to access the database.
* Good to go
	
	Now, you are ready to use AsterX. Simply double click on the AsterX.jar file and save all the 
	website passwords and credit/debit card details on to your local database securely.
	
