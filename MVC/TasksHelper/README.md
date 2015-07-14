J2EE
Container: Tomcat 7
server.xml settings:

(start_tag) Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" maxActive="100" maxIdle="30" maxWait="10000" name="jdbc/tmsDB" password="root" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/tasks_db?characterEncoding=UTF-8" username="root"/ (end_tag)

DB: MySQL
