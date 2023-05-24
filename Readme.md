# jooq-database-project
This project is a sample project to demonstrate how to use jOOQ with a database project.

### How to run the project
1. Clone the project
2. Run the following command to build the project
```
mvn clean install
```
3. Run the following command to generate the jOOQ classes
```
mvn jooq-codegen:generate
```
4. Run the following command to run the tests
```
mvn test
```

5. Run a specific test
```
mvn test -Dtest="className#testName"

    a. Replace className with the name of the class that contains the test and testName with the name of the test method.
    b. The '#' is used to separate the class name from the test name.
    
        Example: 
            mvn test -Dtest="DBTest#verifyDataInsertion"
```
### How to use or implement jOOQ in your project

1. Create a new maven project
2. Add the following dependencies to the pom.xml file
```
<dependencies>

  <dependency>
       <groupId>org.jooq</groupId>
      <artifactId>jooq</artifactId>
      <version>3.18.4</version>
  </dependency>
  
  <dependency>
      <groupId>org.jooq</groupId>
      <artifactId>jooq-meta</artifactId>
      <version>3.18.4</version>
  </dependency>
        
  <dependency>
      <groupId>org.jooq</groupId>
      artifactId>jooq-codegen</artifactId>
      <version>3.18.4</version>
  </dependency>
        
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.33</version>
  </dependency>
        
    <!-- https://mvnrepository.com/artifact/org.testng/testng -->
  <dependency>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
      <version>7.8.0</version>
      <scope>test</scope>
  </dependency>
  
</dependencies>
```
3. Add the following plugin to the pom.xml file

```     
 <build>
        <plugins>
            <plugin>
                <groupId>org.jooq</groupId>
                <artifactId>jooq-codegen-maven</artifactId>
                <version>3.18.4</version>
                <dependencies>
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.27</version>
                    </dependency>
                </dependencies>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <jdbc>
                        <!-- Configure your database connection details here -->
                        <driver>com.mysql.cj.jdbc.Driver</driver>
                        <url>jdbc:mysql://localhost:3306/library</url>
                        <user>root</user>
                        <password>root</password>
                    </jdbc>
                    <generator>
                        <database>
                            <name>org.jooq.meta.mysql.MySQLDatabase</name>
                            <includes>.*</includes>
                            <!-- Specify the tables you want to generate classes for here -->
                            <includes>author</includes>
                        </database>
                        <target>
                            <packageName>com.example.generated</packageName>
                            <directory>src/main/java</directory>
                        </target>
                    </generator>
                </configuration>
            </plugin>
        </plugins>
    </build>
    
       <!--    Replace the table name by your table name, db name by your db name and credentials of the sql in pom.xml plugins and jdbc.-->
```
4. Create a schema in your database, add the table in the schema and add some data to the table.

5. Run the following command to generate the jOOQ classes
```
mvn jooq-codegen:generate
```
6. Create a new class, and you will be able to use the jOOQ classes to perform **CRUD** operations on the table.

---
**Title**: jOOQ Database Project  
**Author**: _Vasu Dev Sirvi_  
**Date**: 24-05-2023  
**Tags**: jOOQ, Database, Java
---